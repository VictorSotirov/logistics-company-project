package nbu.bg.logisticscompany.model.dto;

import lombok.*;

/**
 * The type User update dto.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateDto {
    private String username;
    private String newPassword;
}
