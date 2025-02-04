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
        Task[] arr = List.toTaskArray();
        int len = arr.length;
        for (int i = 1; i <= len; i++) {
            System.out.println(i + "." + arr[i - 1]);
        }
    }
}
