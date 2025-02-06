package dominic.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A utility class that provide methods for the LocalDate class.
 *
 * @author Jordon Chang
 * @version v1.0.0-alpha
 */
public final class DateFormatter {
    private DateFormatter() {
    }

    /**
     * Converts the input date to a string in the yyyy-MM-dd format.
     *
     * @param date a date to be converted to a string
     * @return the string representation of the date in the yyyy-MM-dd format
     */
    public static String dateToFileString(LocalDate date) {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(outputFormat);
    }

    /**
     * Converts the input date to a string in the MMM dd yyyy format.
     *
     * @param date a date to be converted to a string
     * @return the string representation of the date in the MMM dd yyyy format
     */
    public static String dateToString(LocalDate date) {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return date.format(outputFormat);
    }

    /**
     * Returns true if, and only if, {@code toLocalDate(string)} does not throw {@code DateTimeParseException}.
     *
     * @param string string whose format is to be tested
     * @return true if string is in a valid date-time format, otherwise false
     */
    public static boolean isLocalDate(String string) {
        try {
            DateFormatter.toLocalDate(string);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns LocalDate object representing the date in given string.
     *
     * @param string string to be converted to LocalDate object
     * @return LocalDate object of given string
     * @throws DateTimeParseException If given string is not in a valid format
     */
    public static LocalDate toLocalDate(String string) throws DateTimeParseException {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(string, inputFormat);
    }
}
