package dominic.ui;

import java.util.Scanner;

import dominic.commands.ListCommand;
import dominic.storage.StorageReader;
import dominic.storage.StorageWriter;
import dominic.tasks.Task;
import dominic.utils.List;
import dominic.utils.Parser;

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
        // Initialize Storage File
        boolean success = StorageReader.isInitialized();
        if (!success) {
            return;
        }

        // Greet message
        Dominic.greet();

        // List current tasks, if any
        if (!List.isEmpty()) {
            ListCommand listCommand = new ListCommand("");
            System.out.println("Here are your current tasks sir:");
            listCommand.execute();
        }

        // Initialize Scanner to receive dominic.commands
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (!Parser.isByeInput(input)) {
            input = scanner.nextLine();
        }

        // Update Storage File before quitting
        StorageWriter.writeToFile();
    }

    private static void greet() {
        String message = "Sup! I'm\n"
                + " .----------------.  .----------------.  .----------------.  .----------------.  .-----------"
                + "------. .----------------.  .----------------.\n"
                + "| .--------------. || .--------------. || .--------------. || .--------------. || .----------"
                + "----. || .--------------. || .--------------. |\n"
                + "| |  ________    | || |     ____     | || | ____    ____ | || |     _____    | || | ____  ___"
                + "__  | || |     _____    | || |     ______   | |\n"
                + "| | |_   ___ `.  | || |   .'    `.   | || ||_   \\  /   _|| || |    |_   _|   | || ||_   \\|_"
                + "   _| | || |    |_   _|   | || |   .' ___  |  | |\n"
                + "| |   | |   `. \\ | || |  /  .--.  \\  | || |  |   \\/   |  | || |      | |     | || |  |"
                + "   \\ | |   | || |      | |     | || |  / .'   \\_|  | |\n"
                + "| |   | |    | | | || |  | |    | |  | || |  | |\\  /| |  | || |      | |     | || |  | |\\"
                + " \\| |   | || |      | |     | || |  | |         | |\n"
                + "| |  _| |___.' / | || |  \\  `--'  /  | || | _| |_\\/_| |_ | || |     _| |_    | || | _| |_\\"
                + "   |_  | || |     _| |_    | || |  \\ `.___.'\\  | |\n"
                + "| | |________.'  | || |   `.____.'   | || ||_____||_____|| || |    |_____|   | || ||_____|\\_"
                + "___| | || |    |_____|   | || |   `._____.'  | |\n"
                + "| |              | || |              | || |              | || |              | || |          "
                + "    | || |              | || |              | |\n"
                + "| '--------------' || '--------------' || '--------------' || '--------------' || '----------"
                + "----' || '--------------' || '--------------' |\n"
                + " '----------------'  '----------------'  '----------------'  '----------------'  '------------"
                + "----'  '----------------'  '----------------'\n"
                + "What can I do for you?";
        System.out.println(message);
    }

    /**
     * Prints a message on the last added task as well as the total tasks left in the list.
     */
    public static void printRecentlyAdded() {
        Task[] tasks = List.toTaskArray();
        int len = tasks.length;
        System.out.println("Noted, added new task:\n\t" + tasks[len - 1].toString());
        System.out.println("Now you have " + len + " task(s) pending.");
    }

    /**
     * Prints a message on the last removed task as well as the total tasks left in the list.
     */
    public static void printRecentlyDeleted(Task task) {
        Task[] tasks = List.toTaskArray();
        int len = tasks.length;
        System.out.println("Got it, deleted task:\n\t" + task.toString());
        System.out.println("Now you have " + len + " task(s) pending.");
    }
}
