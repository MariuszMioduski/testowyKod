package p.lodz.it.spjava.e13.ges.dto.accounts;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

public class ServicemanDto extends AccountDto{

    @Getter @Setter
    private String department;

    @Getter @Setter
    private String additionalWorkingPermissions;

    @Getter @Setter
    private String servicemanType;

    @JsonbCreator
    public ServicemanDto(@JsonbProperty("id") UUID id,
                         @JsonbProperty("version") long version,
                         @JsonbProperty("creationDate") Date creationDate,
                         @JsonbProperty("lastModificationDate") Date lastModificationDate,
                         @JsonbProperty("role") String role,
                         @JsonbProperty("login") String login,
                         @JsonbProperty("email") String email,
                         @JsonbProperty("firstName") String firstName,
                         @JsonbProperty("lastName") String lastName,
                         @JsonbProperty("phoneNumber") String phoneNumber,
                         @JsonbProperty("active") boolean active,
                         @JsonbProperty("confirmed") boolean confirmed,
                         @JsonbProperty("department") String department,
                         @JsonbProperty("additionalWorkingPermissions") String additionalWorkingPermissions,
                         @JsonbProperty("servicemanType") String servicemanType) {
        super(id, version, creationDate, lastModificationDate, role, login, email, firstName, lastName, phoneNumber, active, confirmed);
        this.department = department;
        this.additionalWorkingPermissions=additionalWorkingPermissions;
        this.servicemanType=servicemanType;
    }

    @Override
    public String toString() {
        return super.toString() + "ServicemanDto{" +
                "department='" + department + '\'' +
                ", additionalWorkingPermissions='" + additionalWorkingPermissions + '\'' +
                ", servicemanType='" + servicemanType + '\'' +
                "} " ;
    }
}
