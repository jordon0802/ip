package dominic.commands;

import dominic.utils.List;
import dominic.tasks.Task;

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
    public void execute() {
        Task[] tasks = List.toTaskArray();
        int len = tasks.length;
        for (int i = 1; i <= len; i++) {
            System.out.println(i + "." + tasks[i - 1]);
        }
    }
}
