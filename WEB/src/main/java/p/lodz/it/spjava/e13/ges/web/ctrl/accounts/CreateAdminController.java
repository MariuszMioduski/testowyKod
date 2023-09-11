package p.lodz.it.spjava.e13.ges.web.ctrl.accounts;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.WebApplicationException;
import lombok.Getter;
import lombok.Setter;
import p.lodz.it.spjava.e13.ges.dto.accounts.CreateAccountCmd;
import p.lodz.it.spjava.e13.ges.dto.accounts.CreateAdminCmd;
import p.lodz.it.spjava.e13.ges.web.restclient.AdminRestClient;
import p.lodz.it.spjava.e13.ges.web.utils.I18nUtils;

import java.io.Serializable;
import java.util.function.Consumer;
import java.util.logging.Logger;

@RequestScoped
@Named
public class CreateAdminController implements Serializable {

    @Inject
    private AdminRestClient adminRestClient;

//    @Inject
//    private EmployeeRestClient employeeRestClient;

    @Inject
    private FacesContext facesContext;

    private CreateAdminCmd newAdmin = new CreateAdminCmd();


    @NotNull
    @Getter @Setter
    private String passwordRepeat;


    public CreateAdminCmd getNewAdmin() {
        return newAdmin;
    }
    public String createAdmin(){

        if (null != newAdmin && null != newAdmin.getPassword() && newAdmin.getPassword().equals(passwordRepeat)) {
            try {
                adminRestClient.createAdmin(newAdmin);
            } catch (WebApplicationException wae) {
                Logger.getAnonymousLogger().severe(wae.toString());
                if (wae.getResponse().getStatus() >= 500) return "error";
                if (I18nUtils.isInternationalizationKeyExist(wae.getMessage())) {
                    I18nUtils.emitInternationalizedMessage(null, wae.getMessage());//wyjątek powinien dysponować kluczem internacjonalizacji
                    return null; //pozostań na bieżącej stronie
                } else return "error"; //w przypadku braku klucza komunikatu błędu również przekieruj na stronę błędu
            }
            return "success";
        } else {
            facesContext.validationFailed();
            facesContext.addMessage("registerClient:passwordRepeat", new FacesMessage(I18nUtils.getMessage("error.passwordRepeatNotMatch")));
            return "";
        }

    }




//    public String createAsAdmin() {
//        try {
//            return createAccount(account -> adminRestClient.createAdmin(account));
//        } catch (WebApplicationException wae) {
//            Logger.getAnonymousLogger().severe(wae.toString());
//            if (wae.getResponse().getStatus() >= 500) return "error";
//            if (I18nUtils.isInternationalizationKeyExist(wae.getMessage())) {
//                I18nUtils.emitInternationalizedMessage(null, wae.getMessage());//wyjątek powinien dysponować kluczem internacjonalizacji
//                return null; //pozostań na bieżącej stronie
//            } else return "error"; //w przypadku braku klucza komunikatu błędu również przekieruj na stronę błędu
//        }
//
//
//    }

//    public String createAsEmployee() {
//        try {
//            return createAccount(account -> employeeRestClient.createEmployee(account));
//        } catch (WebApplicationException wae) {
//            Logger.getAnonymousLogger().severe(wae.toString());
//            if (wae.getResponse().getStatus() >= 500) return "error";
//            if (I18nUtils.isInternationalizationKeyExist(wae.getMessage())) {
//                I18nUtils.emitInternationalizedMessage(null, wae.getMessage());//wyjątek powinien dysponować kluczem internacjonalizacji
//                return null; //pozostań na bieżącej stronie
//            } else return "error"; //w przypadku braku klucza komunikatu błędu również przekieruj na stronę błędu
//        }
//    }

//    private String createAccount(Consumer<CreateAccountCmd> restClientInvocation) {
//        // password && passwordRepeat validation
//        // It could be achieved through building JSF complex validator. But this is simpler.
//        if (null != newAccount && null != newAccount.getPassword() && newAccount.getPassword().equals(passwordRepeat)) {
//            restClientInvocation.accept(newAccount);
//            return "success";
//        } else {
//            facesContext.validationFailed();
////            facesContext.addMessage("registerClient:passwordRepeat", new FacesMessage(I18nUtils.getMessage("error.passwordRepeatNotMatch")));
//            return "";
//        }
//
//    }



}
