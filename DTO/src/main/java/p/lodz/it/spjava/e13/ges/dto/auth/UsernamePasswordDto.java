package p.lodz.it.spjava.e13.ges.dto.auth;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import p.lodz.it.spjava.e13.ges.dto.ValidationMessages;

public class UsernamePasswordDto {

    @NotBlank(message = ValidationMessages.Auth.LOGIN_BLANK)
    @Getter
    private String username;
    @NotBlank(message = ValidationMessages.Auth.PASSWORD_BLANK)
    @Getter
    private String password;

    @JsonbCreator
    public UsernamePasswordDto(@JsonbProperty("username") String username,
                               @JsonbProperty("password") String password) {
        this.username = username;
        this.password = password;
    }
}
