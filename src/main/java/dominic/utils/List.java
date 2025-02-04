package dominic.utils;

import dominic.tasks.Task;

import java.util.LinkedList;

public final class List {
    private static final LinkedList<Task> list = new LinkedList<>();

    private List() {
    }

    public static void append(Task t) {
        List.list.add(t);
    }

    public static boolean isEmpty() {
        return List.list.isEmpty();
    }

    public static Task remove(int i) {
        return List.list.remove(i);
    }

    public static Task[] toTaskArray() {
        Task[] temp = new Task[List.list.size()];
        return List.list.toArray(temp);
    }
}
