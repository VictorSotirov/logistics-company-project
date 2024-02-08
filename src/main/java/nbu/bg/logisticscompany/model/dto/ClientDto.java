package nbu.bg.logisticscompany.model.dto;

import lombok.*;

/**
 * The type Client dto.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDto {
    private Long id;
    private String username;
    private String role;
}
