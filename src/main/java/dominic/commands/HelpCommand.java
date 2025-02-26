package dominic.commands;

/**
 * Represents the help command.
 *
 * @author Jordon Chang
 * @version v1.1
 */
public class HelpCommand extends Command {
    /** Command keyword. */
    public static final String COMMAND = "help";

    /**
     * Constructor from a string.
     *
     * @param arguments arguments to the command
     */
    public HelpCommand(String arguments) {
        super(arguments);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute() {
        return "1. list - list all tasks\n"
                + "2. todo TASK - add new todo\n"
                + "3. deadline TASK /by DEADLINE - add new deadline (note: dates,if any, to be in yyyy-mm-dd format)\n"
                + "4. event TASK /from FROM /to TO - add new event (note: dates,if any, to be in yyyy-mm-dd format)\n"
                + "5. mark INDEX - marks a task\n"
                + "6. unmark INDEX - unmarks a task\n"
                + "7. delete INDEX - deletes a task\n"
                + "8. archive INDEX - archives a task\n"
                + "9. find SUBSTRING - finds a task containing the substring\n"
                + "10. filter DATE - lists task that fall on that date (note: dates to be in yyyy-mm-dd format)\n"
                + "11. help - list this command list\n"
                + "12. bye - quits the program";
    }
}
