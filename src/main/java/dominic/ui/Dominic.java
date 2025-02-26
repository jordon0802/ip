package dominic.ui;

import dominic.commands.ListCommand;
import dominic.storage.StorageReader;
import dominic.storage.StorageWriter;
import dominic.tasks.Task;
import dominic.utils.List;

/**
 * Dominic, a personal assistant chatbot that helps to keep track of tasks.
 *
 * @author Jordon Chang
 * @version v1.1
 */
public class Dominic {
    /**
     * The entry point to the chatbot, Dominic.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Update Storage File before quitting
        StorageWriter.writeToFile();
    }

    private static String greet() {
        return "Sup! I'm Dominic!\nWhat can I do for you?\n";
    }

    /**
     * Initializes the Storage File
     *
     * @return task list if, and only if, storage file is initialized, otherwise, error message
     */
    public static String initialize() {
        boolean success = StorageReader.isInitialized();
        while (!success) {
            success = StorageReader.isInitialized();
        }

        StringBuilder message = new StringBuilder();
        // Greet message
        message.append(Dominic.greet());

        // List current tasks, if any
        if (!List.isEmpty()) {
            ListCommand listCommand = new ListCommand("");
            message.append("\nHere are your current tasks sir:\n");
            message.append(listCommand.execute());
        }
        return message.toString();
    }

    /**
     * Prints a message on the last added task as well as the total tasks left in the list.
     */
    public static String printRecentlyAdded() {
        assert !List.isEmpty() : "List should not be empty";
        Task[] tasks = List.toTaskArray();
        int len = tasks.length;
        return "Noted, added new task:\n\t" + tasks[len - 1] + "\n"
                + "Now you have " + len + " task(s) pending.";
    }

    /**
     * Prints a message on the last archived task as well as the total tasks left in the list.
     */
    public static String printRecentlyArchived(Task task) {
        Task[] tasks = List.toTaskArray();
        int len = tasks.length;
        return "Got it, archived task:\n\t" + task.toString() + "\n"
                + "Now you have " + len + " task(s) pending.";
    }

    /**
     * Prints a message on the last removed task as well as the total tasks left in the list.
     */
    public static String printRecentlyDeleted(Task task) {
        Task[] tasks = List.toTaskArray();
        int len = tasks.length;
        return "Got it, deleted task:\n\t" + task.toString() + "\n"
                + "Now you have " + len + " task(s) pending.";
    }


}
