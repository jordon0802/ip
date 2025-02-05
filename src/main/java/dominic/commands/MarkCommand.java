package dominic.commands;

import dominic.utils.List;
import dominic.tasks.Task;

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
    public void execute() {
        try {
            int idx = Integer.parseInt(super.getArguments());
            Task[] tasks = List.toTaskArray();
            tasks[idx - 1].setMarked();
            System.out.println("Ok, bet, marked it:\n" + tasks[idx - 1]);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid arguments.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Invalid number.");
        }
    }
}
