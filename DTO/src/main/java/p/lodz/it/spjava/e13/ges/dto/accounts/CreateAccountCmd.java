package p.lodz.it.spjava.e13.ges.dto.accounts;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import p.lodz.it.spjava.e13.ges.dto.ValidationMessages;

import java.util.Date;
import java.util.UUID;

public class CreateAccountCmd extends AccountDto {

    @NotNull
    @Size(min=4, message= ValidationMessages.Account.PASSWORD_LENGTH)
    @Getter @Setter
    private String password;

//    @Getter @Setter
//    private String workAlarmPhone;

    @JsonbCreator
    public CreateAccountCmd(@JsonbProperty("id") UUID id,
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
                            @JsonbProperty("password") String password){
//                            @JsonbProperty("workAlarmPhone") String workAlarmPhone){

        super(id, version, creationDate, lastModificationDate, role, login, email, firstName, lastName, phoneNumber, active, confirmed);
        this.password = password;
//        this.workAlarmPhone=workAlarmPhone;
    }

    public CreateAccountCmd() {
        super();
    }

    @Override
    public String toString() {
        return  super.toString() + "CreateAccountCmd{" +
                "} ";
    }
}
