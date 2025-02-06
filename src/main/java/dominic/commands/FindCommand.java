package dominic.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dominic.tasks.Task;
import dominic.utils.List;

public class FindCommand extends Command {
    public static final String COMMAND = "find";

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

        Pattern stringPattern = Pattern.compile("(\\w.*)" + super.getArguments() + "(\\w.*)");
        Task[] tasks = List.toTaskArray();
        int index = 1;
        for (Task task : tasks) {
            Matcher matcher = stringPattern.matcher(task.getTask());
            if (matcher.matches()) {
                System.out.println(index + "." + task);
            }
        }
    }
}
