import exceptions.MissingArgumentException;

public class ToDos extends Task {
    public ToDos(String task) {
        super(task);
    }

    public static String getValidToDo(String input) throws MissingArgumentException {
        if (input.isEmpty()) {
            throw new MissingArgumentException("");
        }
        return input;
    }

    @Override
    public String toFileString() {
        return "[T]\n" + (super.isMarked() ? "[x]" : "[ ]") + "\n" + super.getTask() + "\n";
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
