package dominic.storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import dominic.tasks.Task;
import dominic.utils.List;

/**
 * A utility class that writes to the storage file.
 *
 * @author Jordon Chang
 * @version v1.0
 */
public final class StorageWriter {
    private static final File DB = new File("./data/dominic.txt");
    private static final File ARCHIVE = new File("./data/dominic-archive.txt");

    private StorageWriter() {
    }

    /**
     * Overwrites storage file with updated list.
     */
    public static void writeToFile() {
        // Delete File
        try {
            boolean deleted = StorageWriter.DB.delete();
            boolean deletedArchive = StorageWriter.ARCHIVE.delete();
            if (!deleted || !deletedArchive) {
                System.out.println("Error: File could not be overwritten.");
                return;
            }
        } catch (SecurityException e) {
            System.out.println("Error: File could not be overwritten.");
            return;
        }

        Task[] arr = List.toTaskArray();
        Task[] archive = List.toArchiveArray();
        // Write File
        try {
            BufferedWriter fw = new BufferedWriter(new FileWriter(StorageWriter.DB));
            for (Task task : arr) {
                fw.write(task.toFileString());
            }
            fw.close();
            BufferedWriter fwArchive = new BufferedWriter(new FileWriter(StorageWriter.ARCHIVE));
            for (Task task : archive) {
                fwArchive.write(task.toFileString());
            }
            fwArchive.close();
        } catch (IOException e) {
            System.out.println("Error: Failed to open/write file");
        }
    }
}
