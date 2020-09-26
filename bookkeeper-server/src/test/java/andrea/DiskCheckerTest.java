package andrea;

import org.apache.bookkeeper.util.DiskChecker;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.fail;

public class DiskCheckerTest {

    @Test
    public void validTestCase_1() {

        try {
            new DiskChecker(0.5f, 0.5f);
        } catch (Exception exception) {
            fail();
        }
    }
    
    @Test
    public void validTestCase_2() {
        fail();
    }
}
