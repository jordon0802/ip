package dominic.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dominic.commands.ArchiveCommand;
import dominic.commands.ByeCommand;
import dominic.commands.DeadlineCommand;
import dominic.commands.DeleteCommand;
import dominic.commands.EventCommand;
import dominic.commands.FilterCommand;
import dominic.commands.FindCommand;
import dominic.commands.ListCommand;
import dominic.commands.MarkCommand;
import dominic.commands.TodoCommand;
import dominic.commands.UnmarkCommand;

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
     * Runs command given.
     *
     * @param input string to be matched
     * @return output of the command
     */
    public static String run(String input) {
        Matcher matcher = COMMAND_FORMAT.matcher(input.trim());
        if (!matcher.matches()) {
            return "Error: Invalid command.";
        }
        String command = matcher.group("command");
        String arguments = matcher.group("arguments").trim();

        if (!command.equalsIgnoreCase(ByeCommand.COMMAND)) {
            return switch (command) {
                case ListCommand.COMMAND -> {
                    ListCommand listCommand = new ListCommand(arguments);
                    yield listCommand.execute();
                }
                case FilterCommand.COMMAND -> {
                    FilterCommand filterCommand = new FilterCommand(arguments);
                    yield filterCommand.execute();
                }
                case DeleteCommand.COMMAND -> {
                    DeleteCommand deleteCommand = new DeleteCommand(arguments);
                    yield deleteCommand.execute();
                }
                case MarkCommand.COMMAND -> {
                    MarkCommand markCommand = new MarkCommand(arguments);
                    yield markCommand.execute();
                }
                case UnmarkCommand.COMMAND -> {
                    UnmarkCommand unmarkCommand = new UnmarkCommand(arguments);
                    yield unmarkCommand.execute();
                }
                case TodoCommand.COMMAND -> {
                    TodoCommand todoCommand = new TodoCommand(arguments);
                    yield todoCommand.execute();
                }
                case DeadlineCommand.COMMAND -> {
                    DeadlineCommand deadlineCommand = new DeadlineCommand(arguments);
                    yield deadlineCommand.execute();
                }
                case EventCommand.COMMAND -> {
                    EventCommand eventCommand = new EventCommand(arguments);
                    yield eventCommand.execute();
                }
                case FindCommand.COMMAND -> {
                    FindCommand findCommand = new FindCommand(arguments);
                    yield findCommand.execute();
                }
                case ArchiveCommand.COMMAND -> {
                    ArchiveCommand archiveCommand = new ArchiveCommand(arguments);
                    yield archiveCommand.execute();
                }
                default -> "Error: Invalid command.";
            };
        } else {
            ByeCommand byeCommand = new ByeCommand();
            return byeCommand.execute();
        }
    }
}
