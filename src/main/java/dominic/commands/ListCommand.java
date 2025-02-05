package dominic.commands;

import dominic.utils.List;
import dominic.tasks.Task;

public class ListCommand extends Command {
    public static final String COMMAND = "list";

    public ListCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute() {
        Task[] tasks = List.toTaskArray();
        int len = tasks.length;
        for (int i = 1; i <= len; i++) {
            System.out.println(i + "." + tasks[i - 1]);
        }
    }
}
