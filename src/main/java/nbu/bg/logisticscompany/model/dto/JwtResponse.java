package nbu.bg.logisticscompany.model.dto;

import lombok.*;

import java.util.List;

/**
 * The type Jwt response.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponse {
    private String accessToken;
    private String username;
    private List<String> roles;
}
