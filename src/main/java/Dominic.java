import exceptions.InvalidKeywordException;
import exceptions.InvalidKeywordOrderException;
import exceptions.MissingArgumentException;

import java.util.Scanner;

public class Dominic {
    private enum Marker {
        MARK,
        UNMARK
    }

    public static void main(String[] args) {
        enum Command {
            LIST,
            DELETE,
            MARK,
            UNMARK,
            TODOS,
            DEADLINES,
            EVENTS,
            INVALID
        }

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
            Command command;
            if (input.equalsIgnoreCase("list")) {
                command = Command.LIST;
            } else if (input.startsWith("delete ") && (input.length() > 7)) {
                command = Command.DELETE;
            } else if (input.startsWith("mark ") && (input.length() > 5)) {
                command = Command.MARK;
            } else if (input.startsWith("unmark ") && (input.length() > 7)) {
                command = Command.UNMARK;
            } else if (input.startsWith("todo ")) {
                command = Command.TODOS;
            } else if (input.startsWith("deadline ")) {
                command = Command.DEADLINES;
            } else if (input.startsWith("event ")) {
                command = Command.EVENTS;
            } else {
                command = Command.INVALID;
            }


            switch (command) {
                case LIST:
                    Task[] arr = list.toTaskArray();
                    int len = arr.length;
                    for (int i = 1; i <= len; i++) {
                        System.out.println(i + "." + arr[i - 1]);
                    }
                    break;
                case DELETE:
                    try {
                        int x = Integer.parseInt(input.substring(7));
                        Task task = list.remove(x - 1);
                        Dominic.printRecentlyDeleted(list, task);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Invalid arguments.");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Error: Invalid number.");
                    }
                    break;
                case MARK:
                    try {
                        Dominic.marker(Marker.MARK, input, list);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Invalid arguments.");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Error: Invalid number.");
                    }
                    break;
                case UNMARK:
                    try {
                        Dominic.marker(Marker.UNMARK, input, list);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Invalid arguments.");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Error: Invalid number.");
                    }
                    break;
                case TODOS:
                    try {
                        String arg = input.substring(5).trim();
                        if (arg.isEmpty()) {
                            throw new MissingArgumentException("");
                        }
                        list.append(new ToDos(arg));
                        Dominic.printRecentlyAdded(list);
                    } catch (IndexOutOfBoundsException | MissingArgumentException e) {
                        System.out.println("Eh? What do you need to do?");
                    }
                    break;
                case DEADLINES:
                    try {
                        String arg = input.substring(9).trim();
                        if (arg.isEmpty()) {
                            throw new MissingArgumentException("");
                        }
                        Dominic.checkKeyword(input, " /by ");
                        if (arg.substring(0, arg.indexOf(" /by ")).trim().isEmpty() || arg.indexOf(" /by ") == 0) {
                            throw new MissingArgumentException("");
                        }

                        String task = arg.substring(0, arg.indexOf(" /by "));
                        String deadline = arg.substring(arg.indexOf(" /by ") + 5);
                        list.append(new Deadlines(task, deadline));
                        Dominic.printRecentlyAdded(list);
                    } catch (InvalidKeywordException e) {
                        System.out.println("When u need it done by? " + e.getMessage());
                    } catch (IndexOutOfBoundsException | MissingArgumentException e) {
                        System.out.println("What deadline do you have? (Usage: deadline <text> /by <deadline>)");
                    }
                    break;
                case EVENTS:
                    try {
                        String arg = input.substring(6).trim();
                        if (arg.isEmpty()) {
                            throw new MissingArgumentException("");
                        }
                        Dominic.checkKeyword(input, " /from ");
                        Dominic.checkKeyword(input, " /to ");
                        if (input.indexOf(" /to ") < input.lastIndexOf(" /from ")) {
                            throw new InvalidKeywordOrderException("");
                        }
                        if (arg.substring(0, arg.indexOf(" /from ")).trim().isEmpty() || arg.indexOf(" /from ") == 0) {
                            throw new MissingArgumentException("");
                        }

                        String task = arg.substring(0, arg.indexOf(" /from "));
                        String from = arg.substring(arg.indexOf(" /from ") + 7, arg.indexOf(" /to "));
                        String to = arg.substring(arg.indexOf(" /to ") + 5);
                        list.append(new Events(task, from, to));
                        Dominic.printRecentlyAdded(list);
                    } catch (InvalidKeywordException e) {
                        System.out.println("When is the event? " + e.getMessage());
                    } catch (IndexOutOfBoundsException | MissingArgumentException e) {
                        System.out.println("Eh? What event do you have? (Usage: event <text> /from <from> /to <to>)");
                    } catch (InvalidKeywordOrderException e) {
                        System.out.println("Invalid keyword order! (Usage: event <text> /from <from> /to <to>)");
                    }
                    break;
                default:
                    System.out.println("Error: Invalid command.");
            }
            input = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void checkKeyword(String arg, String kw) throws InvalidKeywordException {
        if (!arg.contains(kw) || (arg.substring(arg.indexOf(kw) + kw.length()).trim().isEmpty())) {
            throw new InvalidKeywordException("(Use" + kw + "to specify.)");
        }
    }

    private static void marker(Marker func, String input, List list) throws NumberFormatException,
            IndexOutOfBoundsException {
        switch (func) {
            case MARK:
                int x = Integer.parseInt(input.substring(5));
                Task[] arr = list.toTaskArray();
                arr[x - 1].setMarked();
                System.out.println("Ok, bet, marked it:\n" + arr[x - 1]);
                break;
            case UNMARK:
                int y = Integer.parseInt(input.substring(7));
                Task[] arr1 = list.toTaskArray();
                arr1[y - 1].setUnMarked();
                System.out.println("Ok, bet, unmarked it:\n" + arr1[y - 1]);
                break;
        }
    }

    private static void printRecentlyAdded(List list) {
        Task[] arr = list.toTaskArray();
        int len = arr.length;
        System.out.println("Noted, added new task:\n\t" + arr[len - 1].toString());
        System.out.println("Now you have " + len + " task(s) pending.");
    }

    private static void printRecentlyDeleted(List list, Task task) {
        Task[] arr = list.toTaskArray();
        int len = arr.length;
        System.out.println("Got it, deleted task:\n\t" + task.toString());
        System.out.println("Now you have " + len + " task(s) pending.");
    }
}
