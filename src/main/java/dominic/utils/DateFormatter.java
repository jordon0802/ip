package dominic.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class DateFormatter {
    private DateFormatter() {
    }

    public static String dateToFileString(LocalDate date) {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(outputFormat);
    }

    public static String dateToString(LocalDate date) {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return date.format(outputFormat);
    }

    public static boolean isLocalDate(String string) {
        try {
            DateFormatter.toLocalDate(string);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static LocalDate toLocalDate(String string) throws DateTimeParseException {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(string, inputFormat);
    }
}
