package dominic.ui;

import dominic.commands.ListCommand;
import dominic.storage.StorageReader;
import dominic.storage.StorageWriter;
import dominic.utils.List;
import dominic.utils.Parser;
import dominic.tasks.Task;

import java.util.Scanner;

public class Dominic {
    public static void main(String[] args) {
        // Initialize Storage File
        boolean success = StorageReader.init();
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

        while (!Parser.parseInput(input)) {
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

    public static void printRecentlyAdded() {
        Task[] arr = List.toTaskArray();
        int len = arr.length;
        System.out.println("Noted, added new task:\n\t" + arr[len - 1].toString());
        System.out.println("Now you have " + len + " task(s) pending.");
    }

    public static void printRecentlyDeleted(Task task) {
        Task[] arr = List.toTaskArray();
        int len = arr.length;
        System.out.println("Got it, deleted task:\n\t" + task.toString());
        System.out.println("Now you have " + len + " task(s) pending.");
    }
}
