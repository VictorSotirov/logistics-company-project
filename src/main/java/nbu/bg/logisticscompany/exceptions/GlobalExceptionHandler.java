package nbu.bg.logisticscompany.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidRegistration.class)
    public ResponseEntity<Object> handleInvalidRegistration(final Exception exception) {
        exception.printStackTrace();

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleAll(final Exception exception) {
        exception.printStackTrace();

        return "redirect:/";
    }
}
