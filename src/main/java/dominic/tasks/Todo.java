package dominic.tasks;

import dominic.exceptions.MissingArgumentException;

public class Todo extends Task {
    public Todo(String task) {
        super(task);
    }

    public static String getValidTask(String input) throws MissingArgumentException {
        if (input.isEmpty()) {
            throw new MissingArgumentException("Missing argument");
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
