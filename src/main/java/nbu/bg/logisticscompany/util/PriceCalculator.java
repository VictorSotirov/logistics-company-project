package nbu.bg.logisticscompany.util;

import nbu.bg.logisticscompany.model.dto.OrderDto;
import org.springframework.stereotype.Service;

//TODO move this to JS
@Service("PriceCalculator")
public class PriceCalculator {

    public static final double ECONOMY_PRICE = 4.40;
    public static final double ECONOMY_ADD_ON = 1.48;
    public static final double PREMIUM_ECONOMY_PRICE = 6.13;
    public static final double PREMIUM_ECONOMY_ADD_ON = 4.25;
    public static final double PRIORITY_PRICE = 14.49;
    public static final double PRIORITY_ADD_ON = 13.98;
    public static final double PRIORITY_EXTRA_KG_ADD_ON = 0.56;

    public double calculateTotalPrice(OrderDto order) {
        double inputWeight = order.getWeight();
        if (inputWeight <= 3) {
            if (order.getIsOfficeDelivery()) {
                return ECONOMY_PRICE;
            }
            return ECONOMY_PRICE + ECONOMY_ADD_ON;
        } else if (inputWeight <= 10) {
            if (order.getIsOfficeDelivery()) {
                return PREMIUM_ECONOMY_PRICE;
            }
            return PREMIUM_ECONOMY_PRICE + PREMIUM_ECONOMY_ADD_ON;
        }
        int remainingKg = (int) Math.floor(inputWeight - 10);
        double remainingKgPrice = remainingKg * PRIORITY_EXTRA_KG_ADD_ON;
        if (order.getIsOfficeDelivery()) {
            return PRIORITY_PRICE + remainingKgPrice;
        }
        return PRIORITY_PRICE + PRIORITY_ADD_ON + remainingKgPrice;
    }
}
