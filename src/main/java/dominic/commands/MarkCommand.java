package dominic.commands;

import dominic.tasks.Task;
import dominic.utils.List;

public class MarkCommand extends Command {
    public static final String COMMAND = "mark";

    public MarkCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute() {
        try {
            int idx = Integer.parseInt(super.getArguments());
            Task[] tasks = List.toTaskArray();
            tasks[idx - 1].setMarked();
            System.out.println("Ok, bet, marked it:\n" + tasks[idx - 1]);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid arguments.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Invalid number.");
        }
    }
}
