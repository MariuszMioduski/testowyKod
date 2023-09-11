package p.lodz.it.spjava.e13.ges.web.ctrl.accounts;

import jakarta.enterprise.context.Conversation;
import jakarta.enterprise.context.ConversationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.WebApplicationException;
import p.lodz.it.spjava.e13.ges.dto.accounts.AccountDto;
import p.lodz.it.spjava.e13.ges.dto.accounts.UpdateAccountCmd;
import p.lodz.it.spjava.e13.ges.web.restclient.AccountRestClient;
import p.lodz.it.spjava.e13.ges.web.utils.I18nUtils;

import java.io.Serializable;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.logging.Logger;

@ConversationScoped
@Named
public class EditAccountController implements Serializable {

    private static final Logger LOG = Logger.getLogger(EditAccountController.class.getName());
    @Inject
    private AccountRestClient accountRestClient;

    @Inject
    private Conversation conversation;

    private UpdateAccountCmd updateAccountCmd = new UpdateAccountCmd(0, "-", "-", "-","-"); //To avoid form error when the conversation is not properly initialized;

    private UUID updateAccountId; //This is not displayed; if it's null, it means the conversation is not properly initialized

    private String updateAccountDescription = "-"; // Just to display something on form

    public void fetchAccountDataById(UUID id) {
        if (!conversation.isTransient()) conversation.end(); // We don't want to continue any opened conversations
        fetchAccountData(() -> accountRestClient.find(id));
    }

    public String fetchSelfAccountData() {
        if (!conversation.isTransient()) conversation.end(); // We don't want to continue any opened conversations
        fetchAccountData(() -> accountRestClient.findSelf());
        return "editSelfAccount"; // Because it's called directly from menu link
    }

    private void fetchAccountData(Supplier<AccountDto> restClientInvocation) {
        AccountDto viewUpdatedAccount = restClientInvocation.get();
        conversation.begin();
        conversation.setTimeout(1000 * 60 * 10);// In milliseconds, should be parametrized
        updateAccountId = viewUpdatedAccount.getId();
        updateAccountCmd = new UpdateAccountCmd(
                viewUpdatedAccount.getVersion(),
                viewUpdatedAccount.getEmail(),
                viewUpdatedAccount.getFirstName(),
                viewUpdatedAccount.getLastName(),
                viewUpdatedAccount.getPhoneNumber());
        //updateAccountDescription = I18nUtils.getMessage(viewUpdatedAccount.getRole()) + ": " + viewUpdatedAccount.getLogin();
    }

    public UpdateAccountCmd getUpdateAccountCmd() {
        return updateAccountCmd;
    }

    public String getUpdateAccountDescription() {
        return updateAccountDescription;
    }

    public String updateAccountById() {
        return updateAccount((updateAccountCmd) -> accountRestClient.edit(updateAccountId, updateAccountCmd),
                () -> accountRestClient.find(updateAccountId),
                "listAccounts");
    }

    public String updateSelfAccount() {
        return updateAccount((updateAccountCmd) -> accountRestClient.editSelf(updateAccountCmd),
                () -> accountRestClient.findSelf(),
                "success");
    }

    private String updateAccount(Consumer<UpdateAccountCmd> restClientInvocationUpdate, Supplier<AccountDto> restClientInvocationFetch, String returnNavigationCase) {
        if (null == updateAccountId) {
            LOG.warning("editAccount form not properly initialized");
            return "main";
        }
        conversation.end();
        try {
            restClientInvocationUpdate.accept(updateAccountCmd);
            return returnNavigationCase;
        } catch (WebApplicationException wae) {
            Logger.getAnonymousLogger().severe(wae.toString());
            if (wae.getResponse().getStatus() >= 500) return "error";
            if (I18nUtils.isInternationalizationKeyExist(wae.getMessage())) {
                I18nUtils.emitInternationalizedMessage(null, wae.getMessage());//wyjątek powinien dysponować kluczem internacjonalizacji
                fetchAccountData(restClientInvocationFetch); // ponowne wczytanie danych zmodyfikowanych w innym przebiegu
                return null; //pozostań na bieżącej stronie
            } else return "error"; //w przypadku braku klucza komunikatu błędu również przekieruj na stronę błędu
        }

    }
}