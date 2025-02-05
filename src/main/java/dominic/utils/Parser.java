package dominic.utils;

import dominic.commands.ByeCommand;
import dominic.commands.DeadlineCommand;
import dominic.commands.DeleteCommand;
import dominic.commands.EventCommand;
import dominic.commands.FilterCommand;
import dominic.commands.ListCommand;
import dominic.commands.MarkCommand;
import dominic.commands.TodoCommand;
import dominic.commands.UnmarkCommand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A utility class that parses user input.
 *
 * @author Jordon Chang
 * @version v1.0.0-alpha
 */
public final class Parser {
    private static final Pattern COMMAND_FORMAT = Pattern.compile("(?<command>\\w+)(?<arguments>.*)");

    private Parser() {
    }

    /**
     * Returns true if, and only if, {@code input} is "bye".
     *
     * @param input string to be matched
     * @return true if {@code input} is "bye", otherwise false
     */
    public static boolean isByeInput(String input) {
        Matcher matcher = COMMAND_FORMAT.matcher(input.trim());
        if (!matcher.matches()) {
            System.out.println("Error: Invalid command.");
            return false;
        }
        String command = matcher.group("command");
        String arguments = matcher.group("arguments").trim();

        if (!command.equalsIgnoreCase(ByeCommand.COMMAND)) {
            switch (command) {
            case ListCommand.COMMAND:
                ListCommand listCommand = new ListCommand(arguments);
                listCommand.execute();
                break;
            case FilterCommand.COMMAND:
                FilterCommand filterCommand = new FilterCommand(arguments);
                filterCommand.execute();
                break;
            case DeleteCommand.COMMAND:
                DeleteCommand deleteCommand = new DeleteCommand(arguments);
                deleteCommand.execute();
                break;
            case MarkCommand.COMMAND:
                MarkCommand markCommand = new MarkCommand(arguments);
                markCommand.execute();
                break;
            case UnmarkCommand.COMMAND:
                UnmarkCommand unmarkCommand = new UnmarkCommand(arguments);
                unmarkCommand.execute();
                break;
            case TodoCommand.COMMAND:
                TodoCommand todoCommand = new TodoCommand(arguments);
                todoCommand.execute();
                break;
            case DeadlineCommand.COMMAND:
                DeadlineCommand deadlineCommand = new DeadlineCommand(arguments);
                deadlineCommand.execute();
                break;
            case EventCommand.COMMAND:
                EventCommand eventCommand = new EventCommand(arguments);
                eventCommand.execute();
                break;
            // Invalid Command
            default:
                System.out.println("Error: Invalid command.");
            }
            return false;
        } else {
            ByeCommand byeCommand = new ByeCommand();
            byeCommand.execute();
            return true;
        }
    }
}
