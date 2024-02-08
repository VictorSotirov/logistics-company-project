package nbu.bg.logisticscompany.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * The type User login dto.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
