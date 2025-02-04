package dominic.commands;

import dominic.utils.List;
import dominic.tasks.Task;

public class MarkCommand extends Command {
    public static final String COMMAND = "mark";

    public MarkCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute() {
        try {
            int x = Integer.parseInt(super.getArguments());
            Task[] arr = List.toTaskArray();
            arr[x - 1].setMarked();
            System.out.println("Ok, bet, marked it:\n" + arr[x - 1]);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid arguments.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Invalid number.");
        }
    }
}
