package andrea.DiskChecker;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestGetTotalFreeSpace extends TestDiskChecker {

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
            fail();
        }
    }


    @Test
    public void validTestCase_2() {

        long output;

        try {

            output = diskChecker.getTotalFreeSpace(new ArrayList<>());
            assertEquals(0, output);

        } catch (Exception exception) {
            fail();
        }
    }


    @Test
    public void invalidTestCase_1() {

        try {

            List<File> list = new ArrayList<>();
            list.add(characterDeviceFile);

            long output = diskChecker.getTotalFreeSpace(list);
            //assertNotEquals(totalFreeSpace, output);    // Not required but useful to verify that it is a bug...
            //assertTrue(output > 0);                     // Not required but useful to verify that it is a bug...
            fail();

        } catch (Exception exception) {
            // Expected
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
            // Expected
        }
    }

    @Test
    public void invalidTestCase_3() {

        try {

            List<File> list = new ArrayList<>();
            list.add(null);

            diskChecker.getTotalFreeSpace(list);
            fail();

        } catch (Exception exception) {
            // Expected
        }
    }


    @Test
    public void invalidTestCase_4() {

        try {

            List<File> list = new ArrayList<>();
            list.add(new File("./notExistentDirectory"));

            diskChecker.getTotalFreeSpace(list);
            fail();

        } catch (Exception exception) {
            // Expected
        }
    }

    @Test
    public void invalidTestCase_5() {

        try {

            List<File> list = new ArrayList<>();
            list.add(new File("\u0000"));

            diskChecker.getTotalDiskSpace(list);
            fail();

        } catch (Exception exception) {
            // Expected
        }
    }

    @Test
    public void invalidTestCase_6() {

        try {

            diskChecker.getTotalFreeSpace(null);
            fail();

        } catch (Exception exception) {
            // Expected
        }
    }
}