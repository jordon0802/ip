import java.util.Scanner;

public class Dominic {
    public static void main(String[] args) {
        String greet = "Sup! I'm\n"
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
        System.out.println(greet);

        List list = new List();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equalsIgnoreCase("bye")) {
            if (input.equalsIgnoreCase("list")) {
                Task[] arr = list.toTaskArray();
                int len = arr.length;
                for (int i = 1; i <= len; i++) {
                    System.out.println(i + "." + arr[i - 1]);
                }
            } else if (input.startsWith("mark ") && (input.length() > 5)) {
                try {
                    int x = Integer.parseInt(input.substring(5));
                    Task[] arr = list.toTaskArray();
                    arr[x - 1].setMarked();
                    System.out.println("Ok, bet, marked it:\n" + arr[x - 1]);
                } catch (NumberFormatException e) {
                    System.out.println("Error: Invalid arguments.");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error: Invalid number.");
                }
            } else if (input.startsWith("unmark ") && (input.length() > 7)) {
                try {
                    int x = Integer.parseInt(input.substring(7));
                    Task[] arr = list.toTaskArray();
                    arr[x - 1].setUnMarked();
                    System.out.println("Ok, bet, unmarked it:\n" + arr[x - 1]);
                } catch (NumberFormatException e) {
                    System.out.println("Error: Invalid arguments.");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error: Invalid number.");
                }
            } else if (input.startsWith("todo ") && !input.substring(5).trim().isEmpty()) {
                list.append(new ToDos(input.substring(5).trim()));
                Task[] arr = list.toTaskArray();
                int len = arr.length;
                System.out.println("Noted, added new task:\n\t" + arr[len - 1].toString());
                System.out.println("Now you have " + len + " task(s) pending.");
            } else if (input.startsWith("deadline ") && !input.substring(9).trim().isEmpty()
                    && input.contains(" /by ")) {
                list.append(new Deadlines(input.trim().substring(9)));
                Task[] arr = list.toTaskArray();
                int len = arr.length;
                System.out.println("Noted, added new task:\n\t" + arr[len - 1].toString());
                System.out.println("Now you have " + len + " task(s) pending.");
            } else if (input.startsWith("event ") && !input.substring(6).trim().isEmpty()
                    && input.contains(" /from ") && input.contains(" /to ")) {
                list.append(new Events(input.trim().substring(6)));
                Task[] arr = list.toTaskArray();
                int len = arr.length;
                System.out.println("Noted, added new task:\n\t" + arr[len - 1].toString());
                System.out.println("Now you have " + len + " task(s) pending.");
            } else {
                System.out.println("Error: Invalid command.");
            }
            input = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
