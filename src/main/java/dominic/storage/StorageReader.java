package dominic.storage;

import dominic.commands.ListCommand;
import dominic.exceptions.InvalidFileFormatException;
import dominic.exceptions.InvalidKeywordException;
import dominic.exceptions.InvalidKeywordOrderException;
import dominic.exceptions.MissingArgumentException;
import dominic.tasks.Deadline;
import dominic.tasks.Event;
import dominic.tasks.Task;
import dominic.tasks.Todo;
import dominic.utils.DateFormatter;
import dominic.utils.List;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

public final class StorageReader {
    private static final File DIR = new File("./data/");
    private static final File DB = new File("./data/dominic.txt");

    private StorageReader() {
    }

    public static void fileToList() {
        try (BufferedReader fr = new BufferedReader(new FileReader(StorageReader.DB))) {
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
                    t = new Todo(task);
                } else if (type.equals("[D]")) {
                    String deadline = Deadline.getValidDeadline(task);
                    task = Deadline.getValidTask(task);
                    LocalDate dateDeadline;
                    if (DateFormatter.isLocalDate(deadline)) {
                        dateDeadline = DateFormatter.toLocalDate(deadline);
                        t = new Deadline(task, dateDeadline);
                    } else {
                        t = new Deadline(task, deadline);
                    }
                } else {
                    String from = Event.getValidFrom(task);
                    String to = Event.getValidTo(task);
                    LocalDate dateFrom;
                    LocalDate dateTo;
                    if (DateFormatter.isLocalDate(from) && DateFormatter.isLocalDate(to)) {
                        dateFrom = DateFormatter.toLocalDate(from);
                        dateTo = DateFormatter.toLocalDate(to);
                        t = new Event(task, dateFrom, dateTo);
                    } else if (DateFormatter.isLocalDate(from)) {
                        dateFrom = DateFormatter.toLocalDate(from);
                        t = new Event(task, dateFrom, to);
                    } else if (DateFormatter.isLocalDate(to)) {
                        dateTo = DateFormatter.toLocalDate(to);
                        t = new Event(task, from, dateTo);
                    } else {
                        t = new Event(task, from, to);
                    }
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

    public static boolean init() {
        try {
            // Case 1: Valid File and Directory
            if (StorageReader.DIR.isDirectory() && StorageReader.DB.isFile()) {
                // Load file data to dominic.utils.List
                StorageReader.fileToList();
            }
            // Case 2a: Missing Directory, attempt to Create Directory
            if (!StorageReader.DIR.isDirectory()) {
                System.out.println("data directory not found.");
                System.out.println("Creating data directory...");
                boolean result = StorageReader.DIR.mkdir();
                if (result) {
                    System.out.println("Data directory created.");
                } else {
                    System.out.println("Error: Failed to create ./data directory.");
                }
            }
            // Case 2b: Missing File, attempt to Create File
            if (!StorageReader.DB.isFile()) {
                System.out.println("dominic.txt not found.");
                System.out.println("Creating dominic.txt...");
                boolean result = StorageReader.DB.createNewFile();
                if (result) {
                    System.out.println("dominic.txt created.");
                } else {
                    System.out.println("Error: dominic.txt already exists.");
                }
            }
            return true;
        } catch (SecurityException e) {
            System.out.println("Error: Failed to read/create data directory and/or dominic.txt file.");
            return false;
        } catch (IOException e) {
            System.out.println("Error: IOException while creating dominic.txt file.");
            return false;
        }
    }
}
