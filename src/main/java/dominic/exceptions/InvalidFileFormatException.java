package dominic.exceptions;

/**
 * Exception thrown when storage file contains invalid content.
 *
 * @author Jordon Chang
 * @version v1.0
 */
public class InvalidFileFormatException extends Exception {
    /**
     * Constructor from a string.
     *
     * @param message String value that will be incorporated in message for this exception.
     */
    public InvalidFileFormatException(String message) {
        super(message);
    }
}
