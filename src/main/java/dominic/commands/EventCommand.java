package dominic.commands;

import dominic.exceptions.InvalidDateOrderException;
import dominic.exceptions.InvalidKeywordOrderException;
import dominic.exceptions.MissingArgumentException;
import dominic.exceptions.MissingKeywordException;
import dominic.tasks.Event;
import dominic.ui.Dominic;
import dominic.utils.List;

/**
 * Represents the event command.
 *
 * @author Jordon Chang
 * @version v1.0.0-alpha
 */
public class EventCommand extends Command {
    /** Command keyword. */
    public static final String COMMAND = "event";

    /**
     * Constructor from a string.
     *
     * @param arguments arguments to the command
     */
    public EventCommand(String arguments) {
        super(arguments);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute() {
        try {
            List.append(Event.taskStringToEvent(super.getArguments()));
            return Dominic.printRecentlyAdded();
        } catch (MissingKeywordException e) {
            return "When is the event? ";
        } catch (IndexOutOfBoundsException | MissingArgumentException e) {
            return "Eh? What event do you have? (Usage: event <text> /from <from> /to <to>)";
        } catch (InvalidKeywordOrderException e) {
            return "Invalid keyword order! (Usage: event <text> /from <from> /to <to>)";
        } catch (InvalidDateOrderException e) {
            return "Eh? How can end date be earlier than start date!";
        }
    }
}
