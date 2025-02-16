package dominic.utils;

import java.util.LinkedList;

import dominic.tasks.Task;

/**
 * A utility class that stores Task objects.
 *
 * @author Jordon Chang
 * @version v1.0.0-alpha
 */
public final class List {
    private static final LinkedList<Task> list = new LinkedList<>();
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
     */
    public static Task remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= List.list.size()) {
            throw new IndexOutOfBoundsException();
        }
        List.size--;
        return List.list.remove(index);
    }

    /**
     * Returns the number of tasks in this list.
     *
     * @return the number of tasks in the list
     */
    public static int getSize() {
        return List.size;
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
