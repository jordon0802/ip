package dominic.commands;

public abstract class Command {
    private final String arguments;

    protected Command(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Executes the command.
     */
    public abstract void execute();

    protected String getArguments() {
        return this.arguments;
    }
}
