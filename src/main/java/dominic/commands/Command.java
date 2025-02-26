package dominic.commands;

/**
 * Abstract class for all commands.
 *
 * @author Jordon Chang
 * @version v1.1
 */
public abstract class Command {
    private final String arguments;

    protected Command(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Executes the command.
     */
    public abstract String execute();

    protected String getArguments() {
        return this.arguments;
    }
}
