import exceptions.InvalidKeywordException;
import exceptions.MissingArgumentException;

public class Deadlines extends Task {
    private final String deadline;

    public Deadlines(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    public static String[] getValidDeadline(String input) throws MissingArgumentException, InvalidKeywordException {
        if (input.isEmpty()) {
            throw new MissingArgumentException("");
        }
        Task.checkKeyword(input, " /by ");
        if (input.substring(0, input.indexOf(" /by ")).trim().isEmpty() || input.indexOf(" /by ") == 0) {
            throw new MissingArgumentException("");
        }

        String[] args = new String[2];
        args[0] = input.substring(0, input.indexOf(" /by "));
        args[1] = input.substring(input.indexOf(" /by ") + 5);
        return args;
    }

    @Override
    public String toFileString() {
        return "[D]\n" + (super.isMarked() ? "[x]" : "[ ]") + "\n" + super.getTask() + " /by " + this.deadline + "\n";
    }

    @Override
    public String toString() {
        return "[D] " + (super.isMarked() ? "[x] " : "[ ] ")
            + super.getTask() + " (by: " + this.deadline + ")";
    }
}
