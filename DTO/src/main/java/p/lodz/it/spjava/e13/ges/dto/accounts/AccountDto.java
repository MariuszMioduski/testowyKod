package p.lodz.it.spjava.e13.ges.dto.accounts;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import p.lodz.it.spjava.e13.ges.dto.AbstractDto;
import p.lodz.it.spjava.e13.ges.dto.ValidationMessages;

import java.util.Date;
import java.util.UUID;

public class AccountDto extends AbstractDto {

    @Getter @Setter
    private String role;

    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9]+([A-Za-z0-9]*|[._-]?[A-Za-z0-9]+)*$", message = ValidationMessages.Account.LOGIN_FORMAT)
    @Size(min = 4, message = ValidationMessages.Account.LOGIN_LENGTH)
    @Getter @Setter
    private String login;

    // OF COURSE we do NOT include a password here :)


    @NotNull
    @Email(message = ValidationMessages.Account.EMAIL_FORMAT)
    @Getter @Setter
    private String email;

    @NotNull
    @Size(min = 2,message = ValidationMessages.Account.FIRSTNAME_LENGTH)
    @Getter @Setter
    private String firstName;

    @NotNull
    @Size(min = 2,message = ValidationMessages.Account.LASTNAME_LENGTH)
    @Getter @Setter
    private String lastName;

    @Size(min = 9, max = 11, message = ValidationMessages.Account.PHONENUMBER_LENGTH)
    @Getter @Setter
    private String phoneNumber;


    @Getter @Setter
    private boolean active;

    @Getter @Setter
    private boolean confirmed;

    @JsonbCreator
    public AccountDto(@JsonbProperty("id") UUID id,
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
                      @JsonbProperty("confirmed") boolean confirmed) {
        super(id, version, creationDate, lastModificationDate);
        this.role = role;
        this.login = login;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.active = active;
        this.confirmed = confirmed;
    }

    public AccountDto() {
        super();

    }

    @Override
    public String toString() {
        return super.toString() +"AccountDto{" +
                "role='" + role + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", active=" + active +
                ", confirmed=" + confirmed +
                "} ";
    }
}

