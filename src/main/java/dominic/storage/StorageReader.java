package dominic.storage;

import dominic.exceptions.InvalidFileFormatException;
import dominic.exceptions.MissingKeywordException;
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

/**
 * A utility class that initialize and reads from the storage file.
 *
 * @author Jordon Chang
 * @version v1.0.0-alpha
 */
public final class StorageReader {
    private static final File DIR = new File("./data/");
    private static final File DB = new File("./data/dominic.txt");

    private enum Type {
        TODO,
        DEADLINE,
        EVENT
    }

    private enum Mark {
        MARKED,
        UNMARKED
    }

    private StorageReader() {
    }

    /**
     * Reads the storage file and populates the list with tasks.
     */
    private static void fileToList() {
        try (BufferedReader fr = new BufferedReader(new FileReader(StorageReader.DB))) {
            while (fr.ready()) {
                // Read 3 lines at a time
                String type = fr.readLine();
                String mark = fr.readLine();
                String task = fr.readLine();

                // Check valid format
                Type taskType = switch (type) {
                    case "[T]" -> Type.TODO;
                    case "[D]" -> Type.DEADLINE;
                    case "[E]" -> Type.EVENT;
                    default -> throw new InvalidFileFormatException("Invalid file format.");
                };

                Mark markType = switch (mark) {
                    case "[M]" -> Mark.MARKED;
                    case "[U]" -> Mark.UNMARKED;
                    default -> throw new InvalidFileFormatException("Invalid file format.");
                };

                // Create task
                Task t;
                if (taskType == Type.TODO) {
                    t = new Todo(task);
                } else if (taskType == Type.DEADLINE) {
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
                if (markType == Mark.MARKED) {
                    t.setMarked();
                }

                // Append to list
                List.append(t);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        } catch (IOException | MissingArgumentException | MissingKeywordException
                 | InvalidKeywordOrderException | InvalidFileFormatException e) {
            System.out.println("Error: File corrupted/failed to close file." + e.getMessage());
        }
    }

    /**
     * Returns true if, and only if, storage directory exists or is created successfully.
     *
     * @return true if storage directory exists or is created successfully, otherwise false
     */
    public static boolean isInitialized() {
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
