package p.lodz.it.spjava.e13.ges.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

public class ChangePasswordDto {

    @Getter @Setter
    private String oldPassword;
    @NotBlank
    @Getter @Setter
    private String newPassword;


    public ChangePasswordDto() {
        super();

    }
}
