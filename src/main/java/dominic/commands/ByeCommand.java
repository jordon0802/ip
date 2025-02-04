package dominic.commands;

public class ByeCommand extends Command {
    public static final String COMMAND = "bye";

    public ByeCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
