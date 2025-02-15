package dominic.commands;

import dominic.tasks.Task;
import dominic.utils.List;

/**
 * Represents the list command.
 *
 * @author Jordon Chang
 * @version v1.0.0-alpha
 */
public class ListCommand extends Command {
    /** Command keyword. */
    public static final String COMMAND = "list";

    /**
     * Constructor from a string.
     *
     * @param arguments arguments to the command
     */
    public ListCommand(String arguments) {
        super(arguments);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute() {
        Task[] tasks = List.toTaskArray();
        int len = tasks.length;
        StringBuilder message = new StringBuilder();
        for (int i = 1; i <= len; i++) {
            message.append(i)
                    .append(".")
                    .append(tasks[i - 1])
                    .append("\n");
        }
        return message.toString();
    }
}
