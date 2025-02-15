package dominic.commands;

import java.time.LocalDate;

import dominic.exceptions.MissingArgumentException;
import dominic.exceptions.MissingKeywordException;
import dominic.tasks.Deadline;
import dominic.ui.Dominic;
import dominic.utils.DateFormatter;
import dominic.utils.List;

/**
 * Represents the bye command.
 *
 * @author Jordon Chang
 * @version v1.0.0-alpha
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
            String task = Deadline.getValidTask(super.getArguments());
            String deadline = Deadline.getValidDeadline(super.getArguments());
            LocalDate dateDeadline;
            if (DateFormatter.isLocalDate(deadline)) {
                dateDeadline = DateFormatter.toLocalDate(deadline);
                List.append(new Deadline(task, dateDeadline));
            } else {
                List.append(new Deadline(task, deadline));
            }
            return Dominic.printRecentlyAdded();
        } catch (MissingKeywordException e) {
            return "When u need it done by? ";
        } catch (IndexOutOfBoundsException | MissingArgumentException e) {
            return "What deadline do you have? (Usage: deadline <text> /by <deadline>)";
        }
    }
}
