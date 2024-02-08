package nbu.bg.logisticscompany.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

/**
 * The type Global exception handler.
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * Constraint violation handler string.
     *
     * @param req       the req
     * @param model     the model
     * @param exception the exception
     * @return the string
     */
    @ExceptionHandler(value = ValidationException.class)
    public String constraintViolationHandler(HttpServletRequest req,
                                             Model model,
                                             ConstraintViolationException exception) {

        StringBuilder errorMsg = new StringBuilder();
        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();

        if (constraintViolations == null) {
            log.error("PasswordMatchesValidator throw this exception !");
            errorMsg.append("Password mismatch!");
        } else {
            for (ConstraintViolation cv : constraintViolations) {
                errorMsg.append(cv.getMessageTemplate() + " ");
            }
        }
        log.error(errorMsg.toString());
        model.addAttribute("errorMessage", errorMsg);
        return "register";
    }

    /**
     * Handle invalid registration string.
     *
     * @param exception the exception
     * @return the string
     */
    @ExceptionHandler(InvalidRegistration.class)
    public String handleInvalidRegistration(final InvalidRegistration exception) {
        log.error(exception.getMessage());
        return "redirect:/register";
    }

    /**
     * Handle company not found string.
     *
     * @param exception the exception
     * @return the string
     */
//Added exception handling for the Company
    @ExceptionHandler(CompanyNotFoundException.class)
    public String handleCompanyNotFound(final CompanyNotFoundException exception) {
        log.error(exception.getMessage());
        return "redirect:/company";
    }

    /**
     * Handle company already exists string.
     *
     * @param exception the exception
     * @return the string
     */
    @ExceptionHandler(CompanyAlreadyExistsException.class)
    public String handleCompanyAlreadyExists(final CompanyAlreadyExistsException exception) {
        log.error(exception.getMessage());
        return "redirect:/company/create";
    }

    /**
     * Generic exception handler string.
     *
     * @param exception the exception
     * @param model     the model
     * @return the string
     */
    @ExceptionHandler(Exception.class)
    public String genericExceptionHandler(Exception exception, Model model) {
        log.error(exception.getMessage());
        model.addAttribute("errorMessage", "Something went wrong!");
        return "error";
    }
}
