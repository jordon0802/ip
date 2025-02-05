package dominic.tasks;

import dominic.exceptions.MissingKeywordException;
import dominic.exceptions.InvalidKeywordOrderException;
import dominic.exceptions.MissingArgumentException;
import dominic.utils.DateFormatter;

import java.time.LocalDate;

/**
 * Represents an Event.
 *
 * @author Jordon Chang
 * @version v1.0.0-alpha
 */
public class Event extends Task {
    private final String STRING_FROM;
    private final LocalDate DATE_FROM;
    private final String STRING_TO;
    private final LocalDate DATE_TO;

    /**
     * Constructs an Event when from and to is a String.
     *
     * @param task description of the task
     * @param from when the task starts
     * @param to when the task ends
     */
    public Event(String task, String from, String to) {
        super(task);
        this.STRING_FROM = from;
        this.DATE_FROM = null;
        this.STRING_TO = to;
        this.DATE_TO = null;
    }

    /**
     * Constructs an Event when from is a LocalDate and to is a String.
     *
     * @param task description of the task
     * @param from when the task starts
     * @param to when the task ends
     */
    public Event(String task, LocalDate from, String to) {
        super(task);
        this.STRING_FROM = null;
        this.DATE_FROM = from;
        this.STRING_TO = to;
        this.DATE_TO = null;
    }

    /**
     * Constructs an Event when from is a String and to is a LocalDate.
     *
     * @param task description of the task
     * @param from when the task starts
     * @param to when the task ends
     */
    public Event(String task, String from, LocalDate to) {
        super(task);
        this.STRING_FROM = from;
        this.DATE_FROM = null;
        this.STRING_TO = null;
        this.DATE_TO = to;
    }

    /**
     * Constructs an Event when from and to is a LocalDate.
     *
     * @param task description of the task
     * @param from when the task starts
     * @param to when the task ends
     */
    public Event(String task, LocalDate from, LocalDate to) {
        super(task);
        this.STRING_FROM = null;
        this.DATE_FROM = from;
        this.STRING_TO = null;
        this.DATE_TO = to;
    }

    /**
     * Returns if input event string is valid.
     *
     * @param input input string of user
     * @throws MissingArgumentException If no valid task description or deadline description.
     * @throws MissingKeywordException If /from or /to keyword is missing.
     */
    private static void checkValidTask(String input) throws MissingArgumentException, InvalidKeywordOrderException,
            MissingKeywordException {
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

    /**
     * Returns a LocalDate that represents when the event is from.
     *
     * @return a LocalDate that represents when the event is from
     */
    public LocalDate getDateFrom() {
        return this.DATE_FROM;
    }

    /**
     * Returns a LocalDate that represents when the event is to.
     *
     * @return a LocalDate that represents when the event is to
     */
    public LocalDate getDateTo() {
        return this.DATE_TO;
    }

    /**
     * Returns a valid task string.
     *
     * @param input input string to be tested
     * @return the task string
     * @throws MissingArgumentException If input is empty.
     * @throws MissingKeywordException If /from or /to keyword is missing.
     */
    public static String getValidTask(String input) throws MissingArgumentException, InvalidKeywordOrderException,
            MissingKeywordException {
        Event.checkValidTask(input);
        return input.substring(0, input.indexOf(" /from "));
    }

    /**
     * Returns a valid string describing when the event is from.
     *
     * @param input input string to be tested
     * @return the string describing when the event is from
     * @throws MissingArgumentException If input is empty.
     * @throws MissingKeywordException If /from or /to keyword is missing.
     */
    public static String getValidFrom(String input) throws MissingArgumentException, InvalidKeywordOrderException,
            MissingKeywordException {
        Event.checkValidTask(input);
        return input.substring(input.indexOf(" /from ") + 7, input.indexOf(" /to "));
    }

    /**
     * Returns a valid string describing when the event is to.
     *
     * @param input input string to be tested
     * @return the string describing when the event is to
     * @throws MissingArgumentException If input is empty.
     * @throws MissingKeywordException If /from or /to keyword is missing.
     */
    public static String getValidTo(String input) throws MissingArgumentException, InvalidKeywordOrderException,
            MissingKeywordException {
        Event.checkValidTask(input);
        return input.substring(input.indexOf(" /to ") + 5);
    }

    /**
     * Returns true if, and only if, DATE_FROM is not null.
     *
     * @return true if DATE_FROM is not null, otherwise false
     */
    public boolean isDateFrom() {
        return !(this.DATE_FROM == null);
    }

    /**
     * Returns true if, and only if, DATE_TO is not null.
     *
     * @return true if DATE_TO is not null, otherwise false
     */
    public boolean isDateTo() {
        return !(this.DATE_TO == null);
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
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
