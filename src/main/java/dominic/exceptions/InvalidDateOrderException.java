package dominic.exceptions;

/**
 * Exception thrown when dates are used in the wrong order.
 *
 * @author Jordon Chang
 * @version v1.0
 */
public class InvalidDateOrderException extends Exception {
    /**
     * Constructor from a string.
     *
     * @param message String value that will be incorporated in message for this exception.
     */
    public InvalidDateOrderException(String message) {
        super(message);
    }
}
