package andrea.DiskChecker;

import andrea.DiskCheckerTest;
import org.apache.bookkeeper.util.DiskChecker;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class GetTotalFreeSpace extends DiskCheckerTest {

    private final String methodName = "getTotalFreeSpace";

    @Test
    public void validTestCase_1() {

        try {

            float output = diskChecker.getTotalFreeSpace(validDirectories);
            assertTrue(output > 0);

        } catch (Exception exception) {
            printExceptionMessage(methodName, exception);
            fail();
        }
    }

    @Test
    public void validTestCase_2() {

        try {

            long output = diskChecker.getTotalFreeSpace(new ArrayList<>());
            assert(output == 0);

        } catch (Exception exception) {
            printExceptionMessage(methodName, exception);
            fail();
        }
    }

    @Test
    public void invalidTestCase_1() {

        try {
            diskChecker.getTotalFreeSpace(null);
            fail();
        } catch (Exception exception) {
            printExceptionMessage(methodName, exception);
        }
    }

    @Test
    public void invalidTestCase_2() {

        try {

            List<File> list = new ArrayList<>();
            list.add(new File("/root"));
            list.add(new File("/run/sudo"));

            diskChecker.getTotalFreeSpace(list);
            fail();

        } catch (Exception exception) {
            printExceptionMessage(methodName, exception);
        }
    }

    @Test
    public void invalidTestCase_3() {

        try {

            List<File> list = new ArrayList<>();
            list.add(new File("./ExampleFile.txt"));
            list.add(new File("/dev/zero"));
            list.add(new File("/dev/null"));

            diskChecker.getTotalFreeSpace(list);
            fail();

        } catch (Exception exception) {
            printExceptionMessage(methodName, exception);
        }
    }
}