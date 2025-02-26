package dominic.commands;

import dominic.tasks.Task;
import dominic.ui.Dominic;
import dominic.utils.List;

/**
 * Represents the archive command.
 *
 * @author Jordon Chang
 * @version v1.1
 */
public class ArchiveCommand extends Command {
    /** Command keyword. */
    public static final String COMMAND = "archive";

    /**
     * Default constructor.
     */
    public ArchiveCommand(String arguments) {
        super(arguments);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute() {
        if (super.getArguments().equalsIgnoreCase("all")) {
            List.archiveAll();
            return "All tasks have been archived.";
        }
        try {
            int x = Integer.parseInt(super.getArguments());
            Task task = List.remove(x - 1);
            List.archive(task);
            return Dominic.printRecentlyArchived(task);
        } catch (NumberFormatException e) {
            return "Error: Invalid arguments.";
        } catch (IndexOutOfBoundsException e) {
            return "Error: Invalid number.";
        }
    }
}
