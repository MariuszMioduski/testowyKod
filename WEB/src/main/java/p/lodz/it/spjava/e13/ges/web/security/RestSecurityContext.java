package p.lodz.it.spjava.e13.ges.web.security;

import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

public class RestSecurityContext implements Serializable {
    @Inject
    private FacesContext facesContext;

    @Getter @Setter
    private String userName;

    @Getter @Setter
    private List<String> groups;

    @Getter @Setter
    private String jwt;

    public boolean isAuthenticated() {
        return jwt != null;
    }

    public String logout() {
        jwt = null;
        facesContext.getExternalContext().invalidateSession();
        return "main";
    }

}
