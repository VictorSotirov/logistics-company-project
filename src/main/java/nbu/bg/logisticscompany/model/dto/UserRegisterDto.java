package nbu.bg.logisticscompany.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nbu.bg.logisticscompany.annotation.PasswordMatches;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@PasswordMatches
public class UserRegisterDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String matchingPassword;

}