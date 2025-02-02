import exceptions.InvalidKeywordException;
import exceptions.InvalidKeywordOrderException;
import exceptions.MissingArgumentException;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Dominic {
    private enum Command {
        LIST,
        DELETE,
        MARK,
        UNMARK,
        TODOS,
        DEADLINES,
        EVENTS,
        INVALID
    }

    private enum Marker {
        MARK,
        UNMARK
    }

    private static final File DIR = new File("./data/");
    private static final File DB = new File("./data/dominic.txt");

    public static void main(String[] args) {
        try {
            // Case 1: Valid File and Directory
            if (DIR.isDirectory() && DB.isFile()) {
                // Load file data to List
                List.fileToList(DB);
            }
            // Case 2a: Missing Directory, attempt to Create Directory
            if (!DIR.isDirectory()) {
                System.out.println("data directory not found.");
                System.out.println("Creating data directory...");
                boolean result = DIR.mkdir();
                if (result) {
                    System.out.println("Data directory created.");
                } else {
                    System.out.println("Error: Failed to create ./data directory.");
                }
            }
            // Case 2b: Missing File, attempt to Create File
            if (!DB.isFile()) {
                System.out.println("dominic.txt not found.");
                System.out.println("Creating dominic.txt...");
                boolean result = DB.createNewFile();
                if (result) {
                    System.out.println("dominic.txt created.");
                } else {
                    System.out.println("Error: dominic.txt already exists.");
                }
            }
        } catch (SecurityException e) {
            System.out.println("Error: Failed to read/create data directory and/or dominic.txt file.");
        } catch (IOException e) {
            System.out.println("Error: IOException while creating dominic.txt file.");
        }

        // Greet message
        Dominic.greet();

        // Initialize Scanner to receive commands
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
                    Task[] arr = List.toTaskArray();
                    int len = arr.length;
                    for (int i = 1; i <= len; i++) {
                        System.out.println(i + "." + arr[i - 1]);
                    }
                    break;
                case DELETE:
                    try {
                        int x = Integer.parseInt(input.substring(7));
                        Task task = List.remove(x - 1);
                        Dominic.printRecentlyDeleted(task);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Invalid arguments.");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Error: Invalid number.");
                    }
                    break;
                case MARK:
                    try {
                        Dominic.marker(Marker.MARK, input);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Invalid arguments.");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Error: Invalid number.");
                    }
                    break;
                case UNMARK:
                    try {
                        Dominic.marker(Marker.UNMARK, input);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Invalid arguments.");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Error: Invalid number.");
                    }
                    break;
                case TODOS:
                    try {
                        String todoVal = ToDos.getValidTask(input.substring(5).trim());
                        List.append(new ToDos(todoVal));
                        Dominic.printRecentlyAdded();
                    } catch (IndexOutOfBoundsException | MissingArgumentException e) {
                        System.out.println("Eh? What do you need to do?");
                    }
                    break;
                case DEADLINES:
                    try {
                        String task = Deadlines.getValidTask(input.substring(9).trim());
                        String deadline = Deadlines.getValidDeadline(input.substring(9).trim());
                        LocalDate dateDeadline;
                        if (Dominic.isLocalDate(deadline)) {
                            dateDeadline = Dominic.toLocalDate(deadline);
                            List.append(new Deadlines(task, dateDeadline));
                        } else {
                            List.append(new Deadlines(task, deadline));

                        }
                        Dominic.printRecentlyAdded();
                    } catch (InvalidKeywordException e) {
                        System.out.println("When u need it done by? ");
                    } catch (IndexOutOfBoundsException | MissingArgumentException e) {
                        System.out.println("What deadline do you have? (Usage: deadline <text> /by <deadline>)");
                    }
                    break;
                case EVENTS:
                    try {
                        String task = Events.getValidTask(input.substring(6).trim());
                        String from = Events.getValidFrom(input.substring(6).trim());
                        String to = Events.getValidTo(input.substring(6).trim());
                        LocalDate dateFrom;
                        LocalDate dateTo;
                        if (Dominic.isLocalDate(from) && Dominic.isLocalDate(to)) {
                            dateFrom = Dominic.toLocalDate(from);
                            dateTo = Dominic.toLocalDate(to);
                            List.append(new Events(task, dateFrom, dateTo));
                        } else if (Dominic.isLocalDate(from)) {
                            dateFrom = Dominic.toLocalDate(from);
                            List.append(new Events(task, dateFrom, to));
                        } else if (Dominic.isLocalDate(to)) {
                            dateTo = Dominic.toLocalDate(to);
                            List.append(new Events(task, from, dateTo));
                        } else {
                            List.append(new Events(task, from, to));
                        }
                        Dominic.printRecentlyAdded();
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
        List.writeToFile(DB);
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static boolean isLocalDate(String string) {
        try {
            Dominic.toLocalDate(string);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static LocalDate toLocalDate(String string) throws DateTimeParseException {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(string, inputFormat);
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

    private static void marker(Marker func, String input) throws NumberFormatException,
            IndexOutOfBoundsException {
        switch (func) {
            case MARK:
                int x = Integer.parseInt(input.substring(5));
                Task[] arr = List.toTaskArray();
                arr[x - 1].setMarked();
                System.out.println("Ok, bet, marked it:\n" + arr[x - 1]);
                break;
            case UNMARK:
                int y = Integer.parseInt(input.substring(7));
                Task[] arr1 = List.toTaskArray();
                arr1[y - 1].setUnMarked();
                System.out.println("Ok, bet, unmarked it:\n" + arr1[y - 1]);
                break;
        }
    }

    private static void printRecentlyAdded() {
        Task[] arr = List.toTaskArray();
        int len = arr.length;
        System.out.println("Noted, added new task:\n\t" + arr[len - 1].toString());
        System.out.println("Now you have " + len + " task(s) pending.");
    }

    private static void printRecentlyDeleted(Task task) {
        Task[] arr = List.toTaskArray();
        int len = arr.length;
        System.out.println("Got it, deleted task:\n\t" + task.toString());
        System.out.println("Now you have " + len + " task(s) pending.");
    }
}
