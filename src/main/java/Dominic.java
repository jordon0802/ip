import java.util.Scanner;

public class Dominic {
    public static void main(String[] args) {
        String greet = "Hello! I'm\n"
            + " .----------------.  .----------------.  .----------------.  .----------------.  .-----------"
            + "------. .----------------.  .----------------. \n"
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
            + "----'  '----------------'  '----------------' \n"
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
            } else if (input.startsWith("mark") && (input.charAt(4) == ' ')) {
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
            } else if (input.startsWith("unmark") && (input.charAt(6) == ' ')) {
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
            } else {
                list.append(new Task(input.trim()));
                System.out.println("added: " + input);
            }
            input = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
