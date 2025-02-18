package dominic.tasks;

import java.time.LocalDate;

import dominic.exceptions.MissingArgumentException;
import dominic.exceptions.MissingKeywordException;
import dominic.utils.DateFormatter;

/**
 * Represents a Deadline.
 *
 * @author Jordon Chang
 * @version v1.0.0-alpha
 */
public class Deadline extends Task {
    private final String stringDeadline;
    private final LocalDate dateDeadline;

    /**
     * Constructs a Deadline when deadline is a String
     *
     * @param task description of the task
     * @param deadline deadline of the task
     */
    public Deadline(String task, String deadline) {
        super(task);
        this.stringDeadline = deadline;
        this.dateDeadline = null;
    }

    /**
     * Constructs a Deadline when deadline is a LocalDate
     *
     * @param task description of the task
     * @param deadline deadline of the task
     */
    public Deadline(String task, LocalDate deadline) {
        super(task);
        this.stringDeadline = null;
        this.dateDeadline = deadline;
    }

    /**
     * Returns if input deadline string is valid.
     *
     * @param input input string of user
     * @throws MissingArgumentException If no valid task description or deadline description.
     * @throws MissingKeywordException If /by keyword is missing.
     */
    private static void checkValidTask(String input) throws MissingArgumentException, MissingKeywordException {
        if (input.isEmpty()) {
            throw new MissingArgumentException("");
        }
        Task.checkKeyword(input, " /by ");
        if (input.substring(0, input.indexOf(" /by ")).trim().isEmpty() || input.indexOf(" /by ") == 0) {
            throw new MissingArgumentException("");
        }
    }

    /**
     * Returns a LocalDate that represents the deadline.
     *
     * @return a LocalDate that represents the deadline
     */
    public LocalDate getDateDeadline() {
        return this.dateDeadline;
    }

    /**
     * Returns a valid task string.
     *
     * @param input input string to be tested
     * @return the task string
     * @throws MissingArgumentException If input is empty.
     * @throws MissingKeywordException If /by keyword is missing.
     */
    public static String getValidTask(String input) throws MissingArgumentException, MissingKeywordException {
        Deadline.checkValidTask(input);
        return input.substring(0, input.indexOf(" /by ")).trim();
    }

    /**
     * Returns a valid deadline string.
     *
     * @param input input string to be tested
     * @return the deadline string
     * @throws MissingArgumentException If input is empty.
     * @throws MissingKeywordException If /by keyword is missing.
     */
    public static String getValidDeadline(String input) throws MissingArgumentException, MissingKeywordException {
        Deadline.checkValidTask(input);
        return input.substring(input.indexOf(" /by ") + 5).trim();
    }

    /**
     * Returns true if, and only if, dateDeadline is not null.
     *
     * @return true if dateDeadline is not null, otherwise false
     */
    public boolean isDateDeadline() {
        return !(this.dateDeadline == null);
    }

    /**
     * Returns the Deadline as specified by the input task string.
     *
     * @param taskGiven task as given in a command format excluding the command keyword.
     * @return the Deadline specified in {@param taskGiven}
     * @throws MissingArgumentException If input is empty.
     * @throws MissingKeywordException If /from or /to keyword is missing.
     */
    public static Deadline taskStringToDeadline(String taskGiven) throws MissingArgumentException,
            MissingKeywordException {
        String deadline = Deadline.getValidDeadline(taskGiven);
        String task = Deadline.getValidTask(taskGiven);
        LocalDate dateDeadline;
        if (DateFormatter.isLocalDate(deadline)) {
            dateDeadline = DateFormatter.toLocalDate(deadline);
            return new Deadline(task, dateDeadline);
        } else {
            return new Deadline(task, deadline);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toFileString() {
        String base = "[D]\n" + (super.isMarked() ? "[x]" : "[ ]") + "\n" + super.getTask() + " /by ";
        if (this.dateDeadline != null) {
            return base + DateFormatter.dateToFileString(this.dateDeadline) + "\n";
        } else {
            return base + this.stringDeadline + "\n";
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String base = "[D] " + (super.isMarked() ? "[x] " : "[ ] ") + super.getTask() + " (by: ";
        if (this.dateDeadline != null) {
            return base + DateFormatter.dateToString(this.dateDeadline) + ")";
        } else {
            return base + this.stringDeadline + ")";
        }
    }
}
