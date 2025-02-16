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
 * @version v1.0.0-alpha
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

    public static String initialize() {
        // Initialize Storage File
        boolean success = StorageReader.isInitialized();
        if (!success) {
            return "Failed to initialize storage";
        }

        StringBuilder message = new StringBuilder();
        // Greet message
        message.append(Dominic.greet());

        // List current tasks, if any
        if (!List.isEmpty()) {
            ListCommand listCommand = new ListCommand("");
            message.append("Here are your current tasks sir:\n");
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
        return "Noted, added new task:\n\t" +
                tasks[len - 1] + "\n" +
                "Now you have " + len + " task(s) pending.";
    }

    /**
     * Prints a message on the last removed task as well as the total tasks left in the list.
     */
    public static String printRecentlyDeleted(Task task) {
        Task[] tasks = List.toTaskArray();
        int len = tasks.length;
        return "Got it, deleted task:\n\t" +
                task.toString() + "\n" +
                "Now you have " + len + " task(s) pending.";
    }


}
