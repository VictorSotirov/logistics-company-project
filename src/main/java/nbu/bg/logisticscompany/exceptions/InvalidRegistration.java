package nbu.bg.logisticscompany.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class InvalidRegistration extends RuntimeException {

    public InvalidRegistration(String message) {
        super(message);
    }
}
