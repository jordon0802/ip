package dominic.commands;

import java.time.LocalDate;

import dominic.exceptions.InvalidKeywordException;
import dominic.exceptions.MissingArgumentException;
import dominic.tasks.Deadline;
import dominic.ui.Dominic;
import dominic.utils.DateFormatter;
import dominic.utils.List;

public class DeadlineCommand extends Command {
    public static final String COMMAND = "deadline";

    public DeadlineCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute() {
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
            Dominic.printRecentlyAdded();
        } catch (InvalidKeywordException e) {
            System.out.println("When u need it done by? ");
        } catch (IndexOutOfBoundsException | MissingArgumentException e) {
            System.out.println("What deadline do you have? (Usage: deadline <text> /by <deadline>)");
        }
    }
}
