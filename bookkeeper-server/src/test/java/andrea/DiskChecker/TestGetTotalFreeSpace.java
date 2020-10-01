package andrea.DiskChecker;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestGetTotalFreeSpace extends TestDiskChecker {

    private final String methodName = "getTotalFreeSpace";

    /*
    @Test
    public void validTestCase_1() {

        long output;

        try {

            List<File> list = new ArrayList<>();
            list.add(new File("/"));
            list.add(new File("/bin"));
            list.add(new File("/usr"));

            output = diskChecker.getTotalFreeSpace(list);
            assertTrue(output > 0);
            assertEquals(totalFreeSpace, output);

        } catch (Exception exception) {
            printExceptionMessage(methodName, exception);
            fail();
        }
    }
*/

    @Test
    public void validTestCase_2() {

        long output;

        try {

            output = diskChecker.getTotalFreeSpace(new ArrayList<>());
            assertEquals(0, output);

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

            long output = diskChecker.getTotalFreeSpace(list);
            assertNotEquals(totalFreeSpace, output);    // Not required but useful to verify that it is a bug...
            assertTrue(output > 0);                     // Not required but useful to verify that it is a bug...
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

            long output = diskChecker.getTotalFreeSpace(list);
            // assertNotEquals(totalFreeSpace, output);    // Not required but useful to verify that it is a bug...
            // assertTrue(output > 0);                     // Not required but useful to verify that it is a bug...
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

            diskChecker.getTotalFreeSpace(list);
            fail();

        } catch (Exception exception) {
            printExceptionMessage(methodName, exception);
        }

        try {

            list = new ArrayList<>();
            list.add(new File("/root/notExistentDirectory"));

            diskChecker.getTotalFreeSpace(list);
            fail();

        } catch (Exception exception) {
            printExceptionMessage(methodName, exception);
        }
    }

    @Test
    public void invalidTestCase_4() {

        try {

            diskChecker.getTotalFreeSpace(null);
            fail();

        } catch (Exception exception) {
            printExceptionMessage(methodName, exception);
        }
    }
}