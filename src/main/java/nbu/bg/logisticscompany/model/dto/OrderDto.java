package nbu.bg.logisticscompany.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    @NotBlank
    private String senderEmail;

    @NotBlank
    private String receiverEmail;

    @NotBlank
    private String deliveryAddress;
    @NotBlank
    private boolean isOfficeDelivery;

    @DecimalMin(value = "0.0", inclusive = true, message = "Weight must be greater than or equal to 0.0")
    private Double weight;

    @DecimalMin(value = "0.0", inclusive = true, message = "Price must be greater than or equal to 0.0")
    private Double totalPrice;

    // https://fluca1978.github.io/2020/09/02/JavaPropertyNotReadable.html
    public boolean getIsOfficeDelivery() {
        return isOfficeDelivery;
    }

    public void setIsOfficeDelivery(boolean value) {
        this.isOfficeDelivery = value;
    }

}
