package p.lodz.it.spjava.e13.ges.rest.config;

import jakarta.annotation.security.DeclareRoles;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.auth.LoginConfig;

@ApplicationPath("api")
//@LoginConfig(authMethod = "MP-JWT", realmName = "MP-JWT")
@DeclareRoles({"ADMIN","CLIENT","EMPLOYEE"})
public class ApplicationConfig extends Application {
}
