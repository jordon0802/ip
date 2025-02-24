package dominic.commands;

import java.time.LocalDate;

import dominic.tasks.Deadline;
import dominic.tasks.Event;
import dominic.tasks.Task;
import dominic.utils.DateFormatter;
import dominic.utils.List;

/**
 * Represents the filter command.
 *
 * @author Jordon Chang
 * @version v1.0
 */
public class FilterCommand extends Command {
    /** Command keyword */
    public static final String COMMAND = "filter";

    /**
     * Constructor from a string.
     *
     * @param arguments arguments to the command
     */
    public FilterCommand(String arguments) {
        super(arguments);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute() {
        if (DateFormatter.isLocalDate(super.getArguments())) {
            LocalDate date = DateFormatter.toLocalDate(super.getArguments());
            Task[] tasks = List.toTaskArray();
            int length = tasks.length;
            int counter = 1;
            StringBuilder message = new StringBuilder();
            for (int i = 1; i <= length; i++) {
                if (tasks[i - 1] instanceof Deadline deadline) {
                    if (deadline.isDateDeadline() && (deadline.getDateDeadline().isEqual(date))) {
                        message.append(counter)
                                .append(".")
                                .append(deadline)
                                .append("\n");
                        counter++;
                    }
                } else if (tasks[i - 1] instanceof Event event) {
                    if ((event.isDateFrom() && event.isDateTo())
                            && (event.getDateFrom().isEqual(date) || event.getDateFrom().isBefore(date))
                            && (event.getDateTo().isEqual(date) || event.getDateTo().isAfter(date))) {
                        message.append(counter)
                                .append(".")
                                .append(event)
                                .append("\n");
                        counter++;
                    }
                }
            }
            return message.toString();
        } else {
            return "Error: Invalid date format.";
        }
    }
}
