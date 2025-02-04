package dominic.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.fail;

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
            assertEquals("longer task", Todo.getValidTask("longer task"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void getValidTask_emptyString_exceptionThrown() {
        try {
            Todo.getValidTask("");
            fail();
        } catch (Exception e) {
            assertEquals("Missing argument", e.getMessage());
        }
    }
}
