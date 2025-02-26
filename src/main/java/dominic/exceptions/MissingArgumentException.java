package dominic.exceptions;

/**
 * Exception thrown when argument(s) are missing in the user input.
 *
 * @author Jordon Chang
 * @version v1.1
 */
public class MissingArgumentException extends Exception {
    /**
     * Constructor from a string.
     *
     * @param message String value that will be incorporated in message for this exception.
     */
    public MissingArgumentException(String message) {
        super(message);
    }
}
