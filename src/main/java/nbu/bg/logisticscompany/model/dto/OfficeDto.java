package nbu.bg.logisticscompany.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * The type Office dto.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OfficeDto {
    private Long id;
    @NotBlank(message = "Address cannot be blank")
    private String address;
    @NotNull(message = "Company Id cannot be null")
    private Long companyId;

    @Override
    public String toString() {
        return "OfficeDto{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", companyId=" + companyId +
                '}';
    }

}
