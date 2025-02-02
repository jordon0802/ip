import exceptions.InvalidFileFormatException;
import exceptions.InvalidKeywordException;
import exceptions.InvalidKeywordOrderException;
import exceptions.MissingArgumentException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.LinkedList;

public final class List {
    private static final LinkedList<Task> list = new LinkedList<>();

    private List() {
    }

    public static void append(Task t) {
        List.list.add(t);
    }

    public static void fileToList(File file) {
        try (BufferedReader fr = new BufferedReader(new FileReader(file))) {
            while (fr.ready()) {
                // Read 3 lines at a time
                String type = fr.readLine();
                String mark = fr.readLine();
                String task = fr.readLine();

                // Check valid format
                if (!type.equals("[T]") && !type.equals("[D]") && !type.equals("[E]")) {
                    throw new InvalidFileFormatException("Invalid file format.");
                }

                if (!mark.equals("[x]") && !mark.equals("[ ]")) {
                    throw new InvalidFileFormatException("Invalid file format.");
                }

                // Create task
                Task t;
                if (type.equals("[T]")) {
                    String todoVal = ToDos.getValidToDo(task);
                    t = new ToDos(todoVal);
                } else if (type.equals("[D]")) {
                    String[] deadlineVals = Deadlines.getValidDeadline(task);
                    t = new Deadlines(deadlineVals[0], deadlineVals[1]);
                } else {
                    String[] eventVals = Events.getValidEvent(task);
                    t = new Events(eventVals[0], eventVals[1], eventVals[2]);
                }

                // Mark if required
                if (mark.equals("[x]")) {
                    t.setMarked();
                }

                // Append to list
                List.append(t);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        } catch (IOException | MissingArgumentException | InvalidKeywordException
                 | InvalidKeywordOrderException | InvalidFileFormatException e) {
            System.out.println("Error: File corrupted/failed to close file." + e.getMessage());
        }
    }

    public static Task remove(int i) {
        return List.list.remove(i);
    }

    public static Task[] toTaskArray() {
        Task[] temp = new Task[List.list.size()];
        return List.list.toArray(temp);
    }

    public static void writeToFile(File file) {
        // Delete File
        try {
            boolean deleted = file.delete();
            if (!deleted) {
                System.out.println("Error: File could not be overwritten.");
                return;
            }
        } catch (SecurityException e) {
            System.out.println("Error: File could not be overwritten.");
            return;
        }

        int len = List.list.size();
        // Write File
        try (BufferedWriter fw = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < len; i++) {
                fw.write(List.list.get(i).toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error: Failed to open/write file");
        }
    }
}
