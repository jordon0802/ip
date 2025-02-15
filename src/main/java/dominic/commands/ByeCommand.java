package dominic.commands;

/**
 * Represents the bye command.
 *
 * @author Jordon Chang
 * @version v1.0.0-alpha
 */
public class ByeCommand extends Command {
    /** Command keyword. */
    public static final String COMMAND = "bye";

    /**
     * Default constructor.
     */
    public ByeCommand() {
        super("");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute() {
        return "Bye. Hope to see you again soon!";
    }
}
