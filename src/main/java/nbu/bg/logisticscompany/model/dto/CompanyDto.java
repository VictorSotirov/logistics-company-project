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

    @NotBlank(message = "Name cannot be blank.")
    private String name;

    @NotBlank(message = "Address cannot be blank.")
    private String address;

    @Override
    public String toString() {
        return "CompanyDto{" +
                "companyName='" + name + '\'' +
                ", companyAddress='" + address + '\'' +
                '}';
    }
}
