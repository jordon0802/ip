package dominic.commands;

import dominic.utils.DateFormatter;
import dominic.utils.List;
import dominic.tasks.Deadline;
import dominic.tasks.Event;
import dominic.tasks.Task;

import java.time.LocalDate;

public class FilterCommand extends Command {
    public static final String COMMAND = "filter";

    public FilterCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute() {
        if (DateFormatter.isLocalDate(super.getArguments())) {
            LocalDate date = DateFormatter.toLocalDate(super.getArguments());
            Task[] list = List.toTaskArray();
            int length = list.length;
            int counter = 1;
            for (int i = 1; i <= length; i++) {
                if (list[i - 1] instanceof Deadline d) {
                    if (d.isDateDeadline() && (d.getDateDeadline().isEqual(date))) {
                        System.out.println(counter + "." + d);
                        counter++;
                    }
                } else if (list[i - 1] instanceof Event e) {
                    if ((e.isDateFrom() && e.isDateTo())
                            && (e.getDateFrom().isEqual(date) || e.getDateFrom().isBefore(date))
                            && (e.getDateTo().isEqual(date) || e.getDateTo().isAfter(date))) {
                        System.out.println(counter + "." + e);
                        counter++;
                    }
                }
            }
        } else {
            System.out.println("Error: Invalid date format.");
        }
    }
}
