package dominic.commands;

import dominic.tasks.Task;
import dominic.ui.Dominic;
import dominic.utils.List;

/**
 * Represents the delete command.
 *
 * @author Jordon Chang
 * @version v1.1
 */
public class DeleteCommand extends Command {
    /** Command keyword. */
    public static final String COMMAND = "delete";

    /**
     * Constructor from a string.
     *
     * @param arguments arguments to the command
     */
    public DeleteCommand(String arguments) {
        super(arguments);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute() {
        try {
            int x = Integer.parseInt(super.getArguments());
            Task task = List.remove(x - 1);
            return Dominic.printRecentlyDeleted(task);
        } catch (NumberFormatException e) {
            return "Error: Invalid arguments.";
        } catch (IndexOutOfBoundsException e) {
            return "Error: Invalid number.";
        }
    }
}
