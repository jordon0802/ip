package dominic.commands;

import dominic.utils.DateFormatter;
import dominic.utils.List;
import dominic.exceptions.InvalidDateOrderException;
import dominic.exceptions.InvalidKeywordException;
import dominic.exceptions.InvalidKeywordOrderException;
import dominic.exceptions.MissingArgumentException;
import dominic.tasks.Event;
import dominic.ui.Dominic;

import java.time.LocalDate;

public class EventCommand extends Command {
    public static final String COMMAND = "event";

    public EventCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute() {
        try {
            String task = Event.getValidTask(super.getArguments());
            String from = Event.getValidFrom(super.getArguments());
            String to = Event.getValidTo(super.getArguments());
            LocalDate dateFrom;
            LocalDate dateTo;
            if (DateFormatter.isLocalDate(from) && DateFormatter.isLocalDate(to)) {
                dateFrom = DateFormatter.toLocalDate(from);
                dateTo = DateFormatter.toLocalDate(to);
                if (dateFrom.isAfter(dateTo)) {
                    throw new InvalidDateOrderException("");
                }
                List.append(new Event(task, dateFrom, dateTo));
            } else if (DateFormatter.isLocalDate(from)) {
                dateFrom = DateFormatter.toLocalDate(from);
                List.append(new Event(task, dateFrom, to));
            } else if (DateFormatter.isLocalDate(to)) {
                dateTo = DateFormatter.toLocalDate(to);
                List.append(new Event(task, from, dateTo));
            } else {
                List.append(new Event(task, from, to));
            }
            Dominic.printRecentlyAdded();
        } catch (InvalidKeywordException e) {
            System.out.println("When is the event? ");
        } catch (IndexOutOfBoundsException | MissingArgumentException e) {
            System.out.println("Eh? What event do you have? (Usage: event <text> /from <from> /to <to>)");
        } catch (InvalidKeywordOrderException e) {
            System.out.println("Invalid keyword order! (Usage: event <text> /from <from> /to <to>)");
        } catch (InvalidDateOrderException e) {
            System.out.println("Eh? How can end date be earlier than start date!");
        }
    }
}
