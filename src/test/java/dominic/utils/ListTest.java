package dominic.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import dominic.tasks.Task;

public class ListTest {
    @Test
    public void toTaskArray_taskAppended_returnAppendedTask() {
        Task task0 = new TaskStub("task0");
        List.append(task0);
        Task[] tasks = List.toTaskArray();
        assertEquals(task0, tasks[0]);

        Task task1 = new TaskStub("task1");
        List.append(task1);
        tasks = List.toTaskArray();
        assertEquals(task0, tasks[0]);
        assertEquals(task1, tasks[1]);
        try {
            List.remove(0);
            List.remove(0);
        } catch (IndexOutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void testRemove() {
        Task task = new TaskStub("task");
        List.append(task);
        try {
            Task removedTask = List.remove(0);
            assertEquals(task, removedTask);
        } catch (IndexOutOfBoundsException e) {
            fail();
        }
    }

    @Test
    public void isEmpty_appendAndRemoveTask_returnTrue() {
        Task task = new TaskStub("task");
        List.append(task);
        try {
            List.remove(0);
            assertTrue(List.isEmpty());
        } catch (IndexOutOfBoundsException e) {
            fail();
        }
    }

    static class TaskStub extends Task {
        protected TaskStub(String task) {
            super(task);
        }

        @Override
        public String toFileString() {
            return "";
        }
    }
}

