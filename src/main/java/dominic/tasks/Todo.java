package dominic.tasks;

import dominic.exceptions.MissingArgumentException;

/**
 * Represents a Todo.
 *
 * @author Jordon Chang
 * @version v1.0
 */
public class Todo extends Task {
    /**
     * Constructs a Todo.
     *
     * @param task description of the task
     */
    public Todo(String task) {
        super(task);
    }

    /**
     * Returns a valid task string.
     *
     * @param input input string to be tested
     * @return the task string
     * @throws MissingArgumentException If input is empty.
     */
    public static String getValidTask(String input) throws MissingArgumentException {
        if (input.isEmpty()) {
            throw new MissingArgumentException("Missing argument");
        }
        return input.trim();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toFileString() {
        return "[T]\n" + (super.isMarked() ? "[x]" : "[ ]") + "\n" + super.getTask() + "\n";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
