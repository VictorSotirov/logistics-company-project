package nbu.bg.logisticscompany.model.dto;

import lombok.*;

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
