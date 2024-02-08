package nbu.bg.logisticscompany.model.dto;

import lombok.*;

/**
 * The type Staff dto.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StaffDto {
    private Long id;
    private String username;
    private String role;
}
