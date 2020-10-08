package andrea.DiskChecker;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestGetTotalDiskSpace extends TestDiskChecker {

    @Test
    public void validTestCase_1() {

        long output;

        try {

            List<File> list = new ArrayList<>();
            list.add(new File("/"));
            list.add(new File("/bin"));
            list.add(new File("/usr"));

            output = diskChecker.getTotalDiskSpace(list);
            assertTrue(output > 0);
            assertEquals(totalSpace, output);

        } catch (Exception exception) {
            fail();
        }
    }

    @Test
    public void validTestCase_2() {

        try {

            long output = diskChecker.getTotalDiskSpace(new ArrayList<>());
            assertEquals(0, output);

        } catch (IOException e) {
            fail();
        }
    }


    @Test
    public void invalidTestCase_1() {

        try {

            List<File> list = new ArrayList<>();
            list.add(characterDeviceFile);

            long output = diskChecker.getTotalDiskSpace(list);
            // assertNotEquals(totalSpace, output);    // Not required but useful to verify that it is a bug...
            // assertTrue(output > 0);                 // Not required but useful to verify that it is a bug...
            fail();

        } catch (Exception exception) {
            // Expected
        }
    }

    @Test
    public void invalidTestCase_2() {

        try {

            List<File> list = new ArrayList<>();
            list.add(new File("./notExistentDirectory"));

            diskChecker.getTotalDiskSpace(list);
            fail();

        } catch (Exception exception) {
            // Expected
        }
    }

    @Test
    public void invalidTestCase_3() {

        try {

            List<File> list = new ArrayList<>();
            list.add(new File("/root"));
            list.add(new File("/run/sudo"));

            long output = diskChecker.getTotalDiskSpace(list);
            // assertNotEquals(totalSpace, output);    // Not required but useful to verify that it is a bug...
            // assertTrue(output > 0);                 // Not required but useful to verify that it is a bug...
            fail();

        } catch (Exception exception) {
            // Expected
        }
    }

    @Test
    public void invalidTestCase_4() {

        try {

            List<File> list = new ArrayList<>();
            list.add(null);

            diskChecker.getTotalDiskSpace(list);
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

            diskChecker.getTotalDiskSpace(null);
            fail();

        } catch (Exception exception) {
            // Expected
        }
    }

    @Test
    public void additionalTest_1() {

        float output;

        try {

            List<File> list = new ArrayList<>();
            list.add(new File("/"));
            list.add(new File("/bin"));
            list.add(new File("/usr"));

            output = diskChecker.getTotalDiskSpace(list);
            if (output == 0)
                fail();

        } catch (Exception exception) {
            fail();
        }
    }
}