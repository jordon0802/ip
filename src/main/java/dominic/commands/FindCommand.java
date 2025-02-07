package dominic.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dominic.tasks.Task;
import dominic.utils.List;

/**
 * Represents the find command.
 *
 * @author Jordon Chang
 * @version v1.0.0-alpha
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
    public void execute() {
        if (super.getArguments().isEmpty()) {
            System.out.println("No task matched!");
            return;
        }

        Pattern stringPattern = Pattern.compile(".*" + super.getArguments() + ".*");
        Task[] tasks = List.toTaskArray();
        int index = 1;
        for (Task task : tasks) {
            Matcher matcher = stringPattern.matcher(task.getTask().trim());
            if (matcher.matches()) {
                System.out.println(index + "." + task);
                index++;
            }
        }
    }
}
