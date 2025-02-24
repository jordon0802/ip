package dominic.commands;

import dominic.tasks.Task;
import dominic.utils.List;

/**
 * Represents the mark command.
 *
 * @author Jordon Chang
 * @version v1.0.0-alpha
 */
public class MarkCommand extends Command {
    /** Command keyword. */
    public static final String COMMAND = "mark";

    /**
     * Constructor from a string.
     *
     * @param arguments arguments to the command
     */
    public MarkCommand(String arguments) {
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
            tasks[idx - 1].setMarked();
            return "Ok, bet, marked it:\n\t" + tasks[idx - 1];
        } catch (NumberFormatException e) {
            return "Error: Invalid arguments.";
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Error: Invalid number.";
        }
    }
}
