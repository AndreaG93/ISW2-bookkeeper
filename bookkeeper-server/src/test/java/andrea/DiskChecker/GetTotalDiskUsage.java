package andrea.DiskChecker;

import andrea.DiskCheckerTest;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class GetTotalDiskUsage extends DiskCheckerTest {

    private final String methodName = "getTotalDiskUsage";

    @Test
    public void validTestCase_1() {

        try {

            float output = diskChecker.getTotalDiskUsage(validDirectories);
            assertTrue(output >= 0 && output <= 1);

        } catch (Exception exception) {
            printExceptionMessage(methodName, exception);
            fail();
        }
    }

    @Test
    public void validTestCase_2() {

        try {

            float output = diskChecker.getTotalDiskUsage(new ArrayList<>());
            assert(output == 0);

        } catch (Exception exception) {
            printExceptionMessage(methodName, exception);
            fail();
        }
    }

    @Test
    public void invalidTestCase_1() {

        try {
            diskChecker.getTotalDiskUsage(null);
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

            float output = diskChecker.getTotalDiskUsage(list);
            assertTrue(output == 0);

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

            diskChecker.getTotalDiskUsage(list);
            fail();

        } catch (Exception exception) {
            printExceptionMessage(methodName, exception);
        }
    }
}
