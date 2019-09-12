import ca.demo.terminal.Deadline;
import ca.demo.terminal.Event;
import ca.demo.terminal.ToDo;
import ca.demo.terminal.UnderstandDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    ToDo test = new ToDo("sleep");
    Deadline test1 = new Deadline("sleep", "12/09/2019 2359");
    Event test2 = new Event("abc", "1/1/2001 0000");
    UnderstandDate test3 = new UnderstandDate("12/09/2019", "2359");

    @Test
    void test () {
        assertEquals(test.toString(), "[T][" + "\u2718" + "] " + "sleep");
        assertEquals(test1.toString(), "[D][" + "\u2718" + "] " + "sleep" + " (by: " + "12/09/2019 2359" + ")");
        assertEquals(test2.toString(), "[E][" + "\u2718" + "] " + "abc" + " (at: " + "1/1/2001 0000" + ")");
        assertEquals(test3.convertDate("12/09/2019", "2359"), "12th of September 2019 -11:59 PM");
    }
}
