package dominic.tasks;

import dominic.exceptions.MissingKeywordException;

import java.util.regex.Pattern;

/**
 * Abstract representation of a basic task.
 *
 * @author Jordon Chang
 * @version v1.0.0-alpha
 */
public abstract class Task {
    private final String TASK;
    private boolean isMarked;

    /**
     * Constructs a task.
     *
     * @param task description of the task
     */
    protected Task(String task) {
        this.TASK = task;
        this.isMarked = false;
    }

    /**
     * Returns if input string contains keyword, throws {@code MissingKeywordException} otherwise.
     *
     * @param input the input to be checked
     * @param keyword the keyword to look for
     * @throws MissingKeywordException If input string does not contain keyword
     */
    public static void checkKeyword(String input, String keyword) throws MissingKeywordException {
        Pattern pattern = Pattern.compile(".*" + keyword + ".*");
        if (!pattern.matcher(input).matches()) {
            throw new MissingKeywordException("(Use" + keyword + "to specify.)");
        }
    }

    /**
     * Returns the task description.
     *
     * @return the task description
     */
    protected String getTask() {
        return this.TASK;
    }

    /**
     * Returns true if, and only if, the task is marked.
     *
     * @return true if the task is marked, otherwise false
     */
    protected boolean isMarked() {
        return this.isMarked;
    }

    /**
     * Sets isMarked to true.
     */
    public void setMarked() {
        this.isMarked = true;
    }

    /**
     * Sets isMarked to false.
     */
    public void setUnMarked() {
        this.isMarked = false;
    }

    /**
     * Returns the string representation of the task to be written to the storage file.
     *
     * @return the string representation in the storage file format
     */
    public abstract String toFileString();

    /**
     * Returns the string representation of the task.
     *
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        return (this.isMarked ? "[x] " : "[ ] ") + this.TASK;
    }
}
