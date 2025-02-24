package dominic.exceptions;

/**
 * Exception thrown when keyword(s) are used in the wrong order.
 *
 * @author Jordon Chang
 * @version v1.0
 */
public class InvalidKeywordOrderException extends Exception {
    /**
     * Constructor from a string.
     *
     * @param message String value that will be incorporated in message for this exception.
     */
    public InvalidKeywordOrderException(String message) {
        super(message);
    }
}
