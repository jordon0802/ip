import exceptions.InvalidKeywordException;
import exceptions.InvalidKeywordOrderException;
import exceptions.MissingArgumentException;

import java.time.LocalDate;

public class Events extends Task {
    private final String stringFrom;
    private final LocalDate dateFrom;
    private final boolean isDateFrom;
    private final String stringTo;
    private final LocalDate dateTo;
    private final boolean isDateTo;

    public Events(String task, String from, String to) {
        super(task);
        this.stringFrom = from;
        this.dateFrom = null;
        this.isDateFrom = false;
        this.stringTo = to;
        this.dateTo = null;
        this.isDateTo = false;
    }

    public Events(String task, LocalDate from, String to) {
        super(task);
        this.stringFrom = null;
        this.dateFrom = from;
        this.isDateFrom = true;
        this.stringTo = to;
        this.dateTo = null;
        this.isDateTo = false;
    }

    public Events(String task, String from, LocalDate to) {
        super(task);
        this.stringFrom = from;
        this.dateFrom = null;
        this.isDateFrom = false;
        this.stringTo = null;
        this.dateTo = to;
        this.isDateTo = true;
    }

    public Events(String task, LocalDate from, LocalDate to) {
        super(task);
        this.stringFrom = null;
        this.dateFrom = from;
        this.isDateFrom = true;
        this.stringTo = null;
        this.dateTo = to;
        this.isDateTo = true;
    }

    private static void checkValidTask(String input) throws MissingArgumentException, InvalidKeywordOrderException,
            InvalidKeywordException {
        if (input.isEmpty()) {
            throw new MissingArgumentException("");
        }
        Task.checkKeyword(input, " /from ");
        Task.checkKeyword(input, " /to ");
        if (input.indexOf(" /to ") < input.lastIndexOf(" /from ")) {
            throw new InvalidKeywordOrderException("");
        }
        if (input.substring(0, input.indexOf(" /from ")).trim().isEmpty() || input.indexOf(" /from ") == 0) {
            throw new MissingArgumentException("");
        }
    }

    public LocalDate getDateFrom() {
        return this.dateFrom;
    }

    public LocalDate getDateTo() {
        return this.dateTo;
    }

    public static String getValidTask(String input) throws MissingArgumentException, InvalidKeywordOrderException,
            InvalidKeywordException {
        Events.checkValidTask(input);
        return input.substring(0, input.indexOf(" /from "));
    }

    public static String getValidFrom(String input) throws MissingArgumentException, InvalidKeywordOrderException,
            InvalidKeywordException {
        Events.checkValidTask(input);
        return input.substring(input.indexOf(" /from ") + 7, input.indexOf(" /to "));
    }

    public static String getValidTo(String input) throws MissingArgumentException, InvalidKeywordOrderException,
            InvalidKeywordException {
        Events.checkValidTask(input);
        return input.substring(input.indexOf(" /to ") + 5);
    }

    public boolean isDateFrom() {
        return this.isDateFrom;
    }

    public boolean isDateTo() {
        return this.isDateTo;
    }

    @Override
    public String toFileString() {
        String base = "[E]\n" + (super.isMarked() ? "[x]" : "[ ]") + "\n" + super.getTask() + " /from ";
        if (dateFrom != null) {
            base += Task.dateToFileString(dateFrom);
        } else {
            base += this.stringFrom;
        }
        if (dateTo != null) {
            return base + " /to " + Task.dateToFileString(dateTo) + "\n";
        } else {
            return base + " /to " + this.stringTo + "\n";
        }
    }

    @Override
    public String toString() {
        String base = "[E] " + (super.isMarked() ? "[x] " : "[ ] ") + super.getTask() + " (from: ";
        if (dateFrom != null) {
            base += Task.dateToString(dateFrom);
        } else {
            base += this.stringFrom;
        }
        if (dateTo != null) {
            return base + " to: " + Task.dateToString(dateTo) + ")";
        } else {
            return base + " to: " + this.stringTo + ")";
        }
    }
}
