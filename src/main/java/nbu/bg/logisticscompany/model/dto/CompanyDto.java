package nbu.bg.logisticscompany.model.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CompanyDto
{
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @Override
    public String toString() {
        return "CompanyDto{" +
                "companyName='" + name + '\'' +
                ", companyAddress='" + address + '\'' +
                '}';
    }
}
