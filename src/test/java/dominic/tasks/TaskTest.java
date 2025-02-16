package dominic.tasks;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void testCheckKeyword() {
        try {
            // Complete match should not throw exception
            assertDoesNotThrow(() -> Task.checkKeyword("keyword", "keyword"));

            // Keyword found in string should not throw exception
            assertDoesNotThrow(() -> Task.checkKeyword("xxxkeywordyyy", "keyword"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void checkKeyword_noKeyword_exceptionThrown() {
        try {
            Task.checkKeyword(" argument ", " keyword ");
            fail();
        } catch (Exception e) {
            // Keyword not found in string should throw exception
            assertEquals("(Use keyword to specify.)", e.getMessage());
        }
    }
}
