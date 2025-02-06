package dominic.commands;

import dominic.utils.List;
import dominic.tasks.Task;
import dominic.ui.Dominic;

/**
 * Represents the delete command.
 *
 * @author Jordon Chang
 * @version v1.0.0-alpha
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
    public void execute() {
        try {
            int x = Integer.parseInt(super.getArguments());
            Task task = List.remove(x - 1);
            Dominic.printRecentlyDeleted(task);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid arguments.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: Invalid number.");
        }
    }
}
