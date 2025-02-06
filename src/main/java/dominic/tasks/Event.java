package dominic.tasks;

import java.time.LocalDate;

import dominic.exceptions.InvalidKeywordException;
import dominic.exceptions.InvalidKeywordOrderException;
import dominic.exceptions.MissingArgumentException;
import dominic.utils.DateFormatter;

public class Event extends Task {
    private final String STRING_FROM;
    private final LocalDate DATE_FROM;
    private final boolean IS_DATE_FROM;
    private final String STRING_TO;
    private final LocalDate DATE_TO;
    private final boolean IS_DATE_TO;

    public Event(String task, String from, String to) {
        super(task);
        this.STRING_FROM = from;
        this.DATE_FROM = null;
        this.IS_DATE_FROM = false;
        this.STRING_TO = to;
        this.DATE_TO = null;
        this.IS_DATE_TO = false;
    }

    public Event(String task, LocalDate from, String to) {
        super(task);
        this.STRING_FROM = null;
        this.DATE_FROM = from;
        this.IS_DATE_FROM = true;
        this.STRING_TO = to;
        this.DATE_TO = null;
        this.IS_DATE_TO = false;
    }

    public Event(String task, String from, LocalDate to) {
        super(task);
        this.STRING_FROM = from;
        this.DATE_FROM = null;
        this.IS_DATE_FROM = false;
        this.STRING_TO = null;
        this.DATE_TO = to;
        this.IS_DATE_TO = true;
    }

    public Event(String task, LocalDate from, LocalDate to) {
        super(task);
        this.STRING_FROM = null;
        this.DATE_FROM = from;
        this.IS_DATE_FROM = true;
        this.STRING_TO = null;
        this.DATE_TO = to;
        this.IS_DATE_TO = true;
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

    public static String getValidTask(String input) throws MissingArgumentException, InvalidKeywordOrderException,
            InvalidKeywordException {
        Event.checkValidTask(input);
        return input.substring(0, input.indexOf(" /from "));
    }

    public static String getValidFrom(String input) throws MissingArgumentException, InvalidKeywordOrderException,
            InvalidKeywordException {
        Event.checkValidTask(input);
        return input.substring(input.indexOf(" /from ") + 7, input.indexOf(" /to "));
    }

    public static String getValidTo(String input) throws MissingArgumentException, InvalidKeywordOrderException,
            InvalidKeywordException {
        Event.checkValidTask(input);
        return input.substring(input.indexOf(" /to ") + 5);
    }

    public LocalDate getDateFrom() {
        return this.DATE_FROM;
    }

    public LocalDate getDateTo() {
        return this.DATE_TO;
    }

    public boolean isDateFrom() {
        return this.IS_DATE_FROM;
    }

    public boolean isDateTo() {
        return this.IS_DATE_TO;
    }

    @Override
    public String toFileString() {
        String base = "[E]\n" + (super.isMarked() ? "[x]" : "[ ]") + "\n" + super.getTask() + " /from ";
        if (DATE_FROM != null) {
            base += DateFormatter.dateToFileString(DATE_FROM);
        } else {
            base += this.STRING_FROM;
        }
        if (DATE_TO != null) {
            return base + " /to " + DateFormatter.dateToFileString(DATE_TO) + "\n";
        } else {
            return base + " /to " + this.STRING_TO + "\n";
        }
    }

    @Override
    public String toString() {
        String base = "[E] " + (super.isMarked() ? "[x] " : "[ ] ") + super.getTask() + " (from: ";
        if (DATE_FROM != null) {
            base += DateFormatter.dateToString(DATE_FROM);
        } else {
            base += this.STRING_FROM;
        }
        if (DATE_TO != null) {
            return base + " to: " + DateFormatter.dateToString(DATE_TO) + ")";
        } else {
            return base + " to: " + this.STRING_TO + ")";
        }
    }
}
