import exceptions.InvalidKeywordException;
import exceptions.MissingArgumentException;

import java.time.LocalDate;

public class Deadlines extends Task {
    private final String stringDeadline;
    private final LocalDate dateDeadline;

    public Deadlines(String task, String deadline) {
        super(task);
        this.stringDeadline = deadline;
        this.dateDeadline = null;
    }

    public Deadlines(String task, LocalDate deadline) {
        super(task);
        this.stringDeadline = null;
        this.dateDeadline = deadline;
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
        Deadlines.checkValidTask(input);
        return input.substring(0, input.indexOf(" /by "));
    }

    public static String getValidDeadline(String input) throws MissingArgumentException, InvalidKeywordException {
        Deadlines.checkValidTask(input);
        return input.substring(input.indexOf(" /by ") + 5);
    }

    @Override
    public String toFileString() {
        String base = "[D]\n" + (super.isMarked() ? "[x]" : "[ ]") + "\n" + super.getTask() + " /by ";
        if (this.dateDeadline != null) {
            return base + Task.dateToFileString(this.dateDeadline) + "\n";
        } else {
            return base + this.stringDeadline + "\n";
        }
    }

    @Override
    public String toString() {
        String base = "[D] " + (super.isMarked() ? "[x] " : "[ ] ") + super.getTask() + " (by: ";
        if (this.dateDeadline != null) {
            return base + Task.dateToString(this.dateDeadline) + ")";
        } else {
            return base + this.stringDeadline + ")";
        }
    }
}
