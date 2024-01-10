package nbu.bg.logisticscompany.model.dto;

import lombok.*;
import nbu.bg.logisticscompany.model.entity.OrderStatus;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    private Long id;
    @NotBlank
    private String senderEmail;

    @NotBlank
    private String receiverEmail;

    @NotBlank
    private String deliveryAddress;

    @NotNull
    private Boolean isOfficeDelivery;

    @DecimalMin(value = "0.0", inclusive = true, message = "Weight must be greater than or equal to 0.0")
    private Double weight;

    @DecimalMin(value = "0.0", inclusive = true, message = "Price must be greater than or equal to 0.0")
    private Double totalPrice;

    private OrderStatus orderStatus;

    @Override
    public String toString() {
        return "OrderDto{" +
                "senderEmail='" + senderEmail + '\'' +
                ", receiverEmail='" + receiverEmail + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", isOfficeDelivery=" + isOfficeDelivery +
                ", weight=" + weight +
                ", totalPrice=" + totalPrice +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
