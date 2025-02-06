package dominic.tasks;

import java.time.LocalDate;

import dominic.exceptions.InvalidKeywordException;
import dominic.exceptions.MissingArgumentException;
import dominic.utils.DateFormatter;

public class Deadline extends Task {
    private final String STRING_DEADLINE;
    private final LocalDate DATE_DEADLINE;
    private final boolean IS_DATE_DEADLINE;

    public Deadline(String task, String deadline) {
        super(task);
        this.STRING_DEADLINE = deadline;
        this.DATE_DEADLINE = null;
        this.IS_DATE_DEADLINE = false;
    }

    public Deadline(String task, LocalDate deadline) {
        super(task);
        this.STRING_DEADLINE = null;
        this.DATE_DEADLINE = deadline;
        this.IS_DATE_DEADLINE = true;
    }

    private static void checkValidTask(String input) throws MissingArgumentException, InvalidKeywordException {
        if (input.isEmpty()) {
            throw new MissingArgumentException("");
        }
        Task.checkKeyword(input, " /by ");
        if (input.substring(0, input.indexOf(" /by ")).trim().isEmpty() || input.indexOf(" /by ") == 0) {
            throw new MissingArgumentException("");
        }
    }

    public static String getValidTask(String input) throws MissingArgumentException, InvalidKeywordException {
        Deadline.checkValidTask(input);
        return input.substring(0, input.indexOf(" /by "));
    }

    public static String getValidDeadline(String input) throws MissingArgumentException, InvalidKeywordException {
        Deadline.checkValidTask(input);
        return input.substring(input.indexOf(" /by ") + 5);
    }

    public LocalDate getDateDeadline() {
        return this.DATE_DEADLINE;
    }

    public boolean isDateDeadline() {
        return this.IS_DATE_DEADLINE;
    }

    @Override
    public String toFileString() {
        String base = "[D]\n" + (super.isMarked() ? "[x]" : "[ ]") + "\n" + super.getTask() + " /by ";
        if (this.DATE_DEADLINE != null) {
            return base + DateFormatter.dateToFileString(this.DATE_DEADLINE) + "\n";
        } else {
            return base + this.STRING_DEADLINE + "\n";
        }
    }

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
