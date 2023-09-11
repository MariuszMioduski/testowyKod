package p.lodz.it.spjava.e13.ges.web.ctrl.accounts;

import jakarta.enterprise.context.Conversation;
import jakarta.enterprise.context.ConversationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.WebApplicationException;
import p.lodz.it.spjava.e13.ges.dto.auth.ChangePasswordDto;
import p.lodz.it.spjava.e13.ges.web.restclient.AccountRestClient;
import p.lodz.it.spjava.e13.ges.web.utils.I18nUtils;

import java.io.Serializable;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.logging.Logger;

@ConversationScoped
@Named
public class ChangeAccountPasswordController implements Serializable {

    private static final Logger LOG = Logger.getLogger(ChangeAccountPasswordController.class.getName());
    @Inject
    private AccountRestClient accountRestClient;

    @Inject
    private Conversation conversation;

    @Inject
    private FacesContext facesContext;

    private ChangePasswordDto changePasswordDto = new ChangePasswordDto();

    @NotNull
    private String passwordRepeat;

    private String updateAccountLogin; // to be displayed on page and button


    private UUID updateAccountId; //This is not displayed; if it's null, it means the conversation is not properly initialized or it is self password change

    public String getUpdateAccountLogin() {
        return updateAccountLogin;
    }
    public ChangePasswordDto getChangePasswordDto() {
        return changePasswordDto;
    }
    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }

    // THIS is used ONLY in case we change password on GIVEN account
    // If user changes SELF password, we do not use conversation
    public void setAccountData(UUID id, String login) {
        if (!conversation.isTransient()) conversation.end(); // We don't want to continue any opened conversations
        conversation.begin();
        conversation.setTimeout(1000 * 60 * 10);// In milliseconds, should be parametrized
        updateAccountId = id;
        updateAccountLogin = login;
    }

    public String changeAccountPasword() {
        if (null == updateAccountId) {
            LOG.warning("changeAccountPassword form not properly initialized");
            return "main";
        }
        conversation.end();
        return changePassword(passwordDto -> accountRestClient.changePassword(updateAccountId, passwordDto),"listAccounts");
    }

    public String changeSelfPasword() {
        if (!conversation.isTransient()) conversation.end();
        return changePassword(passwordDto -> accountRestClient.changeSelfPassword(passwordDto),"success");
    }

    // This is actual password change routine
    private String changePassword(Consumer<ChangePasswordDto> restClientInvocation, String returnNavigationCase) {
        // password && passwordRepeat validation
        // It could be achieved through building JSF complex validator. But this is simpler.
        if (changePasswordDto.getNewPassword().equals(passwordRepeat)) {
            try {
                restClientInvocation.accept(changePasswordDto);
                return returnNavigationCase;
            } catch (WebApplicationException wae) {
                Logger.getAnonymousLogger().severe(wae.toString());
                if (wae.getResponse().getStatus() >= 500) return "error";
                if (I18nUtils.isInternationalizationKeyExist(wae.getMessage())) {
                    I18nUtils.emitInternationalizedMessage(null, wae.getMessage());//wyjątek powinien dysponować kluczem internacjonalizacji
                    return null; //pozostań na bieżącej stronie
                } else return "error"; //w przypadku braku klucza komunikatu błędu również przekieruj na stronę błędu
            }
        } else {
            facesContext.validationFailed();
            facesContext.addMessage("changePassword:passwordRepeat", new FacesMessage(I18nUtils.getMessage("error.passwordRepeatNotMatch")));
            return "";
        }

    }
}

