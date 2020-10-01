package andrea.DiskChecker;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestGetTotalDiskUsage extends TestDiskChecker {

    private final String methodName = "getTotalDiskUsage";

    @Test
    public void validTestCase_1() {

        float output;

        try {

            List<File> list = new ArrayList<>();
            list.add(new File("/"));
            list.add(new File("/bin"));
            list.add(new File("/usr"));

            output = diskChecker.getTotalDiskUsage(list);
            assertTrue(output > 0);
            System.out.println("validTestCase_1: " + output);

        } catch (Exception exception) {
            printExceptionMessage(methodName, exception);
            fail();
        }
    }

    @Test
    public void invalidTestCase_1() {

        try {

            List<File> list = new ArrayList<>();
            list.add(regularFile);
            list.add(characterDeviceFile);

            for (File file : list)
                assertFalse(file.isDirectory());

            float output = diskChecker.getTotalDiskUsage(list);
            System.out.println("invalidTestCase_1: " + output);
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
            System.out.println("invalidTestCase_2: " + output);
            fail();

        } catch (Exception exception) {
            printExceptionMessage(methodName, exception);
        }
    }

    @Test
    public void invalidTestCase_3() {

        List<File> list;

        try {

            list = new ArrayList<>();
            list.add(null);

            diskChecker.getTotalDiskUsage(list);
            fail();

        } catch (Exception exception) {
            printExceptionMessage(methodName, exception);
        }

        try {

            list = new ArrayList<>();
            list.add(new File("/root/notExistentDirectory"));

            diskChecker.getTotalDiskUsage(list);
            fail();

        } catch (Exception exception) {
            printExceptionMessage(methodName, exception);
        }
    }

    @Test
    public void invalidTestCase_4() {

        try {

            diskChecker.getTotalDiskUsage(new ArrayList<>());
            fail();

        } catch (Exception exception) {
            printExceptionMessage(methodName, exception);
        }
    }

    @Test
    public void invalidTestCase_5() {

        try {

            diskChecker.getTotalDiskUsage(null);
            fail();

        } catch (Exception exception) {
            printExceptionMessage(methodName, exception);
        }
    }
}
