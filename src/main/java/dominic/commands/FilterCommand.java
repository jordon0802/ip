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
 * @version v1.0.0-alpha
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
    public void execute() {
        if (DateFormatter.isLocalDate(super.getArguments())) {
            LocalDate date = DateFormatter.toLocalDate(super.getArguments());
            Task[] tasks = List.toTaskArray();
            int length = tasks.length;
            int counter = 1;
            for (int i = 1; i <= length; i++) {
                if (tasks[i - 1] instanceof Deadline deadline) {
                    if (deadline.isDateDeadline() && (deadline.getDateDeadline().isEqual(date))) {
                        System.out.println(counter + "." + deadline);
                        counter++;
                    }
                } else if (tasks[i - 1] instanceof Event event) {
                    if ((event.isDateFrom() && event.isDateTo())
                            && (event.getDateFrom().isEqual(date) || event.getDateFrom().isBefore(date))
                            && (event.getDateTo().isEqual(date) || event.getDateTo().isAfter(date))) {
                        System.out.println(counter + "." + event);
                        counter++;
                    }
                }
            }
        } else {
            System.out.println("Error: Invalid date format.");
        }
    }
}
