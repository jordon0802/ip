package dominic.utils;

import java.util.LinkedList;

import dominic.tasks.Task;

/**
 * A utility class that stores Task objects.
 *
 * @author Jordon Chang
 * @version v1.1
 */
public final class List {
    private static final LinkedList<Task> archive = new LinkedList<>();
    private static final LinkedList<Task> list = new LinkedList<>();
    private static int archiveSize = 0;
    private static int size = 0;

    private List() {
    }

    /**
     * Appends the specified task to the end of the list.
     *
     * @param task task to be added to the list
     */
    public static void append(Task task) {
        List.list.add(task);
        List.size++;
    }

    /**
     * Appends the specified task to the end of the list.
     *
     * @param task task to be added to the list
     */
    public static void appendArchive(Task task) {
        List.archive.add(task);
        List.archiveSize++;
    }

    /**
     * Archives the task at the specified position in the list.
     *
     * @param task the task to be archived
     * @throws IndexOutOfBoundsException If input index is less than 0, more than or equals to the size of the list.
     */
    public static void archive(Task task) throws IndexOutOfBoundsException {
        List.archive.add(task);
        List.archiveSize++;
    }

    /**
     * Archives the entire list.
     */
    public static void archiveAll() {
        while (!List.list.isEmpty()) {
            List.archive.add(List.list.removeFirst());
            List.size--;
            List.archiveSize++;
        }
    }

    /**
     * Returns true if, and only if, size is 0.
     *
     * @return true if the {@code getSize()} is 0, otherwise false
     */
    public static boolean isEmpty() {
        return List.list.isEmpty();
    }

    /**
     * Removes the task at the specified position in this list. Shifts any subsequent elements to the left (subtracts
     * one from their indices).
     *
     * @param index the index of the task to be removed
     * @return the task that was removed from the list
     * @throws IndexOutOfBoundsException If input index is less than 0, more than or equals to the size of the list.
     */
    public static Task remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= List.list.size()) {
            throw new IndexOutOfBoundsException();
        }
        List.size--;
        return List.list.remove(index);
    }

    /**
     * Returns the list of tasks as an array.
     *
     * @return the array of tasks
     */
    public static Task[] toArchiveArray() {
        Task[] archives = new Task[List.archiveSize];
        return List.archive.toArray(archives);
    }

    /**
     * Returns the list of tasks as an array.
     *
     * @return the array of tasks
     */
    public static Task[] toTaskArray() {
        Task[] tasks = new Task[List.size];
        return List.list.toArray(tasks);
    }
}
