package dominic.commands;

import dominic.utils.List;
import dominic.exceptions.MissingArgumentException;
import dominic.tasks.Todo;
import dominic.ui.Dominic;

public class TodoCommand extends Command{
    public static final String COMMAND = "todo";

    public TodoCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute() {
        try {
            String todoVal = Todo.getValidTask(super.getArguments());
            List.append(new Todo(todoVal));
            Dominic.printRecentlyAdded();
        } catch (IndexOutOfBoundsException | MissingArgumentException e) {
            System.out.println("Eh? What do you need to do?");
        }
    }
}
