package dominic.tasks;

import java.time.LocalDate;

import dominic.exceptions.InvalidDateOrderException;
import dominic.exceptions.InvalidKeywordOrderException;
import dominic.exceptions.MissingArgumentException;
import dominic.exceptions.MissingKeywordException;
import dominic.utils.DateFormatter;

/**
 * Represents an Event.
 *
 * @author Jordon Chang
 * @version v1.0.0-alpha
 */
public class Event extends Task {
    private final String stringFrom;
    private final LocalDate dateFrom;
    private final String stringTo;
    private final LocalDate dateTo;

    /**
     * Constructs an Event when from and to is a String.
     *
     * @param task description of the task
     * @param from when the task starts
     * @param to when the task ends
     */
    public Event(String task, String from, String to) {
        super(task);
        this.stringFrom = from;
        this.dateFrom = null;
        this.stringTo = to;
        this.dateTo = null;
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
        this.stringFrom = null;
        this.dateFrom = from;
        this.stringTo = to;
        this.dateTo = null;
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
        this.stringFrom = from;
        this.dateFrom = null;
        this.stringTo = null;
        this.dateTo = to;
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
        this.stringFrom = null;
        this.dateFrom = from;
        this.stringTo = null;
        this.dateTo = to;
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
        return this.dateFrom;
    }

    /**
     * Returns a LocalDate that represents when the event is to.
     *
     * @return a LocalDate that represents when the event is to
     */
    public LocalDate getDateTo() {
        return this.dateTo;
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
        return input.substring(0, input.indexOf(" /from ")).trim();
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
        return input.substring(input.indexOf(" /from ") + 7, input.indexOf(" /to ")).trim();
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
        return input.substring(input.indexOf(" /to ") + 5).trim();
    }

    /**
     * Returns true if, and only if, dateFrom is not null.
     *
     * @return true if dateFrom is not null, otherwise false
     */
    public boolean isDateFrom() {
        return !(this.dateFrom == null);
    }

    /**
     * Returns true if, and only if, dateTo is not null.
     *
     * @return true if dateTo is not null, otherwise false
     */
    public boolean isDateTo() {
        return !(this.dateTo == null);
    }

    /**
     * Returns the Event as specified by the input task string.
     *
     * @param taskGiven task as given in a command format excluding the command keyword.
     * @return the Event specified in {@param taskGiven}
     * @throws InvalidDateOrderException If /to date is earlier than /from date.
     * @throws MissingArgumentException If input is empty.
     * @throws InvalidKeywordOrderException If /to keyword comes before /from keyword.
     * @throws MissingKeywordException If /from or /to keyword is missing.
     */
    public static Event taskStringToEvent(String taskGiven) throws InvalidDateOrderException,
            MissingArgumentException, InvalidKeywordOrderException, MissingKeywordException {
        String task = Event.getValidTask(taskGiven);
        String from = Event.getValidFrom(taskGiven);
        String to = Event.getValidTo(taskGiven);
        LocalDate dateFrom;
        LocalDate dateTo;
        if (DateFormatter.isLocalDate(from) && DateFormatter.isLocalDate(to)) {
            dateFrom = DateFormatter.toLocalDate(from);
            dateTo = DateFormatter.toLocalDate(to);
            if (dateFrom.isAfter(dateTo)) {
                throw new InvalidDateOrderException("");
            }
            return new Event(task, dateFrom, dateTo);
        } else if (DateFormatter.isLocalDate(from)) {
            dateFrom = DateFormatter.toLocalDate(from);
            return new Event(task, dateFrom, to);
        } else if (DateFormatter.isLocalDate(to)) {
            dateTo = DateFormatter.toLocalDate(to);
            return new Event(task, from, dateTo);
        } else {
            return new Event(task, from, to);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String toFileString() {
        String base = "[E]\n" + (super.isMarked() ? "[x]" : "[ ]") + "\n" + super.getTask() + " /from ";
        if (dateFrom != null) {
            base += DateFormatter.dateToFileString(dateFrom);
        } else {
            base += this.stringFrom;
        }
        if (dateTo != null) {
            return base + " /to " + DateFormatter.dateToFileString(dateTo) + "\n";
        } else {
            return base + " /to " + this.stringTo + "\n";
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String base = "[E] " + (super.isMarked() ? "[x] " : "[ ] ") + super.getTask() + " (from: ";
        if (dateFrom != null) {
            base += DateFormatter.dateToString(dateFrom);
        } else {
            base += this.stringFrom;
        }
        if (dateTo != null) {
            return base + " to: " + DateFormatter.dateToString(dateTo) + ")";
        } else {
            return base + " to: " + this.stringTo + ")";
        }
    }
}
