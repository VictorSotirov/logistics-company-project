package nbu.bg.logisticscompany.model.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CompanyDto {
    private Long id;

    @NotBlank(message = "Name cannot be blank.")
    @Size(max = 50, message = "Name can be maximum of 50 characters.")
    private String name;

    @NotBlank(message = "Address cannot be blank.")
    @Size(max = 50, message = "Address can be maximum of 50 characters.")
    private String address;

    @Override
    public String toString() {
        return "CompanyDto{" +
                "companyName='" + name + '\'' +
                ", companyAddress='" + address + '\'' +
                '}';
    }
}
