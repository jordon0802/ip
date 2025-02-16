package dominic.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.Test;

import dominic.exceptions.MissingArgumentException;
import dominic.exceptions.MissingKeywordException;

public class DeadlineTest {
    @Test
    public void testDeadline() {
        assertInstanceOf(Task.class, new Deadline("deadline", "now"));
        assertInstanceOf(Deadline.class, new Deadline("deadline", "now"));
        assertInstanceOf(Task.class, new Deadline("deadline", LocalDate.now()));
        assertInstanceOf(Deadline.class, new Deadline("deadline", LocalDate.now()));
    }

    @Test
    public void testGetDateDeadline() {
        LocalDate localDate = LocalDate.of(2024, Month.APRIL, 1);
        Deadline deadline = new Deadline("deadline", localDate);
        assertEquals(deadline.getDateDeadline(), localDate);
    }

    @Test
    public void getDateDeadline_noDateDeadline_nullReturned() {
        Deadline deadline = new Deadline("some deadline", "today");
        assertNull(deadline.getDateDeadline());
    }

    @Test
    public void testGetValidTask() {
        try {
            assertEquals("some deadline", Deadline.getValidTask("some deadline /by tmr"));
            assertEquals("another deadline", Deadline.getValidTask(" another deadline  /by today"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void getValidTask_emptyString_exceptionThrown() {
        assertThrows(MissingArgumentException.class, () -> Deadline.getValidTask(""));
    }

    @Test
    public void getValidTask_noByKeyword_exceptionThrown() {
        assertThrows(MissingKeywordException.class, () -> Deadline.getValidTask("some deadline"));
    }

    @Test
    public void testGetValidDeadline() {
        try {
            assertEquals("tmr", Deadline.getValidDeadline("some deadline /by tmr"));
            assertEquals("today", Deadline.getValidDeadline("another deadline /by  today "));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testIsDateDeadline() {
        Deadline stringDeadline = new Deadline("some deadline", "today");
        assertFalse(stringDeadline.isDateDeadline());

        Deadline dateDeadline = new Deadline("some deadline", LocalDate.of(2024, Month.APRIL, 1));
        assertTrue(dateDeadline.isDateDeadline());
    }

    @Test
    public void testToFileString() {
        Deadline stringDeadline = new Deadline("some deadline", "now");
        assertEquals("[D]\n[ ]\nsome deadline /by now\n", stringDeadline.toFileString());
        stringDeadline.setMarked();
        assertEquals("[D]\n[x]\nsome deadline /by now\n", stringDeadline.toFileString());

        LocalDate localDate = LocalDate.of(2024, Month.APRIL, 1);
        Deadline dateDeadline = new Deadline("some deadline", localDate);
        assertEquals("[D]\n[ ]\nsome deadline /by 2024-04-01\n", dateDeadline.toFileString());
        dateDeadline.setMarked();
        assertEquals("[D]\n[x]\nsome deadline /by 2024-04-01\n", dateDeadline.toFileString());
    }

    @Test
    public void testToString() {
        Deadline stringDeadline = new Deadline("some deadline", "now");
        assertEquals("[D] [ ] some deadline (by: now)", stringDeadline.toString());
        stringDeadline.setMarked();
        assertEquals("[D] [x] some deadline (by: now)", stringDeadline.toString());

        LocalDate localDate = LocalDate.of(2024, Month.APRIL, 1);
        Deadline dateDeadline = new Deadline("some deadline", localDate);
        assertEquals("[D] [ ] some deadline (by: Apr 01 2024)", dateDeadline.toString());
        dateDeadline.setMarked();
        assertEquals("[D] [x] some deadline (by: Apr 01 2024)", dateDeadline.toString());
    }
}
