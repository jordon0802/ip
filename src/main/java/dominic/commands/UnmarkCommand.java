package dominic.commands;

import dominic.tasks.Task;
import dominic.utils.List;

/**
 * Represents the unmark command.
 *
 * @author Jordon Chang
 * @version v1.0.0-alpha
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
    public void execute() {
        try {
            int idx = Integer.parseInt(super.getArguments());
            Task[] tasks = List.toTaskArray();
            tasks[idx - 1].setUnMarked();
            System.out.println("Ok, bet, unmarked it:\n" + tasks[idx - 1]);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid arguments.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Invalid number.");
        }
    }
}
