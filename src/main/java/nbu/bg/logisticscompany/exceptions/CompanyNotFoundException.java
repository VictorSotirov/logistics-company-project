package nbu.bg.logisticscompany.exceptions;

/**
 * The type Company not found exception.
 */
public class CompanyNotFoundException extends RuntimeException {
    /**
     * Instantiates a new Company not found exception.
     *
     * @param message the message
     */
    public CompanyNotFoundException(String message) {
        super(message);
    }
}
