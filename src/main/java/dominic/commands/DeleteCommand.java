package dominic.commands;

import dominic.tasks.Task;
import dominic.ui.Dominic;
import dominic.utils.List;

public class DeleteCommand extends Command {
    public static final String COMMAND = "delete";

    public DeleteCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute() {
        try {
            int x = Integer.parseInt(super.getArguments());
            Task task = List.remove(x - 1);
            Dominic.printRecentlyDeleted(task);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid arguments.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: Invalid number.");
        }
    }
}
