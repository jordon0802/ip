package dominic.commands;

import dominic.tasks.Task;
import dominic.utils.List;

/**
 * Represents the unmark command.
 *
 * @author Jordon Chang
 * @version v1.0
 */
public class UnmarkCommand extends Command {
    /** Command keyword. */
    public static final String COMMAND = "unmark";

    /**
     * Constructor from a string.
     *
     * @param arguments arguments to the command
     */
    public UnmarkCommand(String arguments) {
        super(arguments);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute() {
        try {
            int idx = Integer.parseInt(super.getArguments());
            Task[] tasks = List.toTaskArray();
            tasks[idx - 1].setUnMarked();
            return "Ok, bet, unmarked it:\n\t" + tasks[idx - 1];
        } catch (NumberFormatException e) {
            return "Error: Invalid arguments.";
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Error: Invalid number.";
        }
    }
}
