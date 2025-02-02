import exceptions.InvalidKeywordException;
import exceptions.InvalidKeywordOrderException;
import exceptions.MissingArgumentException;

public class Events extends Task {
    private final String from;
    private final String to;

    public Events(String task, String from, String to) {
        super(task);
        this.from = from;
        this.to = to;
    }

    public static String[] getValidEvent(String input) throws MissingArgumentException, InvalidKeywordOrderException,
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

        String[] args = new String[3];
        args[0] = input.substring(0, input.indexOf(" /from "));
        args[1] = input.substring(input.indexOf(" /from ") + 7, input.indexOf(" /to "));
        args[2] = input.substring(input.indexOf(" /to ") + 5);
        return args;
    }

    @Override
    public String toFileString() {
        return "[E]\n" + (super.isMarked() ? "[x]" : "[ ]") + "\n" + super.getTask()
                + " /from " + this.from + " /to " + this.to + "\n";
    }

    @Override
    public String toString() {
        return "[E] " + (super.isMarked() ? "[x] " : "[ ] ")
            + super.getTask() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
