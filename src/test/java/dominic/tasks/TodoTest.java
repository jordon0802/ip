package dominic.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import dominic.exceptions.MissingArgumentException;

public class TodoTest {
    @Test
    public void testTodo() {
        // Task is parent class of Todo
        assertInstanceOf(Task.class, new Todo(""));
        assertInstanceOf(Todo.class, new Todo(""));
    }

    @Test
    public void testGetValidTask() {
        try {
            assertEquals("task", Todo.getValidTask("task"));
            assertEquals("longer task", Todo.getValidTask(" longer task "));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void getValidTask_emptyString_exceptionThrown() {
        assertThrows(MissingArgumentException.class, () -> Todo.getValidTask(""));
    }

    @Test
    public void testToFileString() {
        Todo todo = new Todo("some todo");
        assertEquals("[T]\n[ ]\nsome todo\n", todo.toFileString());
        todo.setMarked();
        assertEquals("[T]\n[x]\nsome todo\n", todo.toFileString());
    }

    @Test
    public void testToString() {
        Todo todo = new Todo("some todo");
        assertEquals("[T] [ ] some todo", todo.toString());
        todo.setMarked();
        assertEquals("[T] [x] some todo", todo.toString());
    }
}
