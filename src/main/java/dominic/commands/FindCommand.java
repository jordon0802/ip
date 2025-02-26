package dominic.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dominic.tasks.Task;
import dominic.utils.List;

/**
 * Represents the find command.
 *
 * @author Jordon Chang
 * @version v1.1
 */
public class FindCommand extends Command {
    /** Command keyword. */
    public static final String COMMAND = "find";

    /**
     * Constructor from a string.
     *
     * @param arguments arguments to the command
     */
    public FindCommand(String arguments) {
        super(arguments);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute() {
        if (super.getArguments().isEmpty()) {
            return "No task matched!";
        }

        Pattern stringPattern = Pattern.compile(".*" + super.getArguments() + ".*");
        Task[] tasks = List.toTaskArray();
        int index = 1;
        StringBuilder message = new StringBuilder();
        for (Task task : tasks) {
            Matcher matcher = stringPattern.matcher(task.getTask().trim());
            if (matcher.matches()) {
                message.append(index)
                        .append(".")
                        .append(task)
                        .append("\n");
                index++;
            }
        }
        if (message.isEmpty()) {
            message.append("You have no tasks that contains ").append(super.getArguments());
        }
        return message.toString();
    }
}
