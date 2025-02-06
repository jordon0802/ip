package dominic.storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import dominic.tasks.Task;
import dominic.utils.List;

public final class StorageWriter {
    private static final File DB = new File("./data/dominic.txt");

    private StorageWriter() {
    }

    public static void writeToFile() {
        // Delete File
        try {
            boolean deleted = StorageWriter.DB.delete();
            if (!deleted) {
                System.out.println("Error: File could not be overwritten.");
                return;
            }
        } catch (SecurityException e) {
            System.out.println("Error: File could not be overwritten.");
            return;
        }

        Task[] arr = List.toTaskArray();
        // Write File
        try (BufferedWriter fw = new BufferedWriter(new FileWriter(StorageWriter.DB))) {
            for (Task task : arr) {
                fw.write(task.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error: Failed to open/write file");
        }
    }
}
