package dominic.commands;

import dominic.exceptions.MissingArgumentException;
import dominic.exceptions.MissingKeywordException;
import dominic.tasks.Deadline;
import dominic.ui.Dominic;
import dominic.utils.List;

/**
 * Represents the bye command.
 *
 * @author Jordon Chang
 * @version v1.0
 */
public class DeadlineCommand extends Command {
    /**
     * Command keyword.
     */
    public static final String COMMAND = "deadline";

    /**
     * Constructor from a string
     *
     * @param arguments arguments to the command
     */
    public DeadlineCommand(String arguments) {
        super(arguments);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute() {
        try {
            List.append(Deadline.taskStringToDeadline(super.getArguments()));
            return Dominic.printRecentlyAdded();
        } catch (MissingKeywordException e) {
            return "When u need it done by? ";
        } catch (IndexOutOfBoundsException | MissingArgumentException e) {
            return "What deadline do you have? (Usage: deadline <text> /by <deadline>)";
        }
    }
}
