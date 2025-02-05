package dominic.commands;

import dominic.utils.List;
import dominic.tasks.Task;

public class UnmarkCommand extends Command {
    public static final String COMMAND = "unmark";

    public UnmarkCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute() {
        try {
            int idx = Integer.parseInt(super.getArguments());
            Task[] tasks = List.toTaskArray();
            tasks[idx - 1].setUnMarked();
            System.out.println("Ok, bet, unmarked it:\n" + tasks[idx - 1]);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid arguments.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Invalid number.");
        }
    }
}
