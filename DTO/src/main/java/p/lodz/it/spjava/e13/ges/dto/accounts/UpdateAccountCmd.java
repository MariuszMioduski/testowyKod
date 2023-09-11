package p.lodz.it.spjava.e13.ges.dto.accounts;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import p.lodz.it.spjava.e13.ges.dto.ValidationMessages;

public class UpdateAccountCmd {

    @Getter
    private long originalVersion;

    @Getter @Setter
    @Email(message = ValidationMessages.Account.EMAIL_FORMAT)
    private String email;

    @Getter @Setter
    @Size(min = 2,message = ValidationMessages.Account.FIRSTNAME_LENGTH)
    private String firstName;

    @Getter @Setter
    @Size(min = 2,message = ValidationMessages.Account.LASTNAME_LENGTH)
    private String lastName;

    @Getter @Setter
    @Size(min=9, max = 11, message = ValidationMessages.Account.PHONENUMBER_LENGTH)
    private String phoneNumber;



    @JsonbCreator
    public UpdateAccountCmd(@JsonbProperty("originalVersion") long originalVersion,
                            @JsonbProperty("email") String email,
                            @JsonbProperty("firstName") String firstName,
                            @JsonbProperty("lastName") String lastName,
                            @JsonbProperty("phoneNumber")String phoneNumber) {
        this.originalVersion = originalVersion;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }




    @Override
    public String toString() {
        return "UpdateAccountCmd{" +
                "originalVersion='" + originalVersion + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}

