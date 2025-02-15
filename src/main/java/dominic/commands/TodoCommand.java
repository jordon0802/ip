package dominic.commands;

import dominic.exceptions.MissingArgumentException;
import dominic.tasks.Todo;
import dominic.ui.Dominic;
import dominic.utils.List;

/**
 * Represents the todo command.
 *
 * @author Jordon Chang
 * @version v1.0.0-alpha
 */
public class TodoCommand extends Command {
    /** Command keyword. */
    public static final String COMMAND = "todo";

    /**
     * Constructor from a string.
     *
     * @param arguments arguments to the command
     */
    public TodoCommand(String arguments) {
        super(arguments);
    }

    /**
     * {@inheritDoc}
      */
    @Override
    public String execute() {
        try {
            String todoVal = Todo.getValidTask(super.getArguments());
            List.append(new Todo(todoVal));
            return Dominic.printRecentlyAdded();
        } catch (IndexOutOfBoundsException | MissingArgumentException e) {
            return "Eh? What do you need to do?";
        }
    }
}
