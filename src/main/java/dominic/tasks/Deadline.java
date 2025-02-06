package dominic.tasks;

import java.time.LocalDate;

import dominic.exceptions.MissingKeywordException;
import dominic.exceptions.MissingArgumentException;
import dominic.utils.DateFormatter;

/**
 * Represents a Deadline.
 *
 * @author Jordon Chang
 * @version v1.0.0-alpha
 */
public class Deadline extends Task {
    private final String STRING_DEADLINE;
    private final LocalDate DATE_DEADLINE;

    /**
     * Constructs a Deadline when deadline is a String
     *
     * @param task description of the task
     * @param deadline deadline of the task
     */
    public Deadline(String task, String deadline) {
        super(task);
        this.STRING_DEADLINE = deadline;
        this.DATE_DEADLINE = null;
    }

    /**
     * Constructs a Deadline when deadline is a LocalDate
     *
     * @param task description of the task
     * @param deadline deadline of the task
     */
    public Deadline(String task, LocalDate deadline) {
        super(task);
        this.STRING_DEADLINE = null;
        this.DATE_DEADLINE = deadline;
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
            throw new MissingArgumentException();
        }
        Task.checkKeyword(input, " /by ");
        if (input.substring(0, input.indexOf(" /by ")).trim().isEmpty() || input.indexOf(" /by ") == 0) {
            throw new MissingArgumentException();
        }
    }

    /**
     * Returns a LocalDate that represents the deadline.
     *
     * @return a LocalDate that represents the deadline
     */
    public LocalDate getDateDeadline() {
        return this.DATE_DEADLINE;
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
        return input.substring(0, input.indexOf(" /by "));
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
        return input.substring(input.indexOf(" /by ") + 5);
    }

    /**
     * Returns true if, and only if, DATE_DEADLINE is not null.
     *
     * @return true if DATE_DEADLINE is not null, otherwise false
     */
    public boolean isDateDeadline() {
        return !(this.DATE_DEADLINE == null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toFileString() {
        String base = "[D]\n" + (super.isMarked() ? "[x]" : "[ ]") + "\n" + super.getTask() + " /by ";
        if (this.DATE_DEADLINE != null) {
            return base + DateFormatter.dateToFileString(this.DATE_DEADLINE) + "\n";
        } else {
            return base + this.STRING_DEADLINE + "\n";
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String base = "[D] " + (super.isMarked() ? "[x] " : "[ ] ") + super.getTask() + " (by: ";
        if (this.DATE_DEADLINE != null) {
            return base + DateFormatter.dateToString(this.DATE_DEADLINE) + ")";
        } else {
            return base + this.STRING_DEADLINE + ")";
        }
    }
}
