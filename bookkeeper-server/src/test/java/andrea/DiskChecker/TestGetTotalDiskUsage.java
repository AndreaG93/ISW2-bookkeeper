package andrea.DiskChecker;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestGetTotalDiskUsage extends TestDiskChecker {

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
            fail();
        }
    }

    @Test
    public void invalidTestCase_1() {

        try {

            diskChecker.getTotalDiskUsage(new ArrayList<>());
            fail();

        } catch (Exception exception) {
            // Expected
        }
    }

    @Test
    public void invalidTestCase_2() {

        try {

            List<File> list = new ArrayList<>();
            list.add(new File("/bin"));
            list.add(characterDeviceFile);

            float output = diskChecker.getTotalDiskUsage(list);
            System.out.println("invalidTestCase_1: " + output);
            fail();

        } catch (Exception exception) {
            // Expected
        }
    }

    @Test
    public void invalidTestCase_3() {

        try {

            List<File> list = new ArrayList<>();
            list.add(new File("./notExistentDirectory"));

            diskChecker.getTotalDiskUsage(list);
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

            diskChecker.getTotalDiskUsage(list);
            fail();

        } catch (Exception exception) {
            // Expected
        }
    }


    @Test
    public void invalidTestCase_5() {

        try {

            List<File> list = new ArrayList<>();
            list.add(new File("/root"));
            list.add(new File("/run/sudo"));

            float output = diskChecker.getTotalDiskUsage(list);
            System.out.println("invalidTestCase_2: " + output);
            fail();

        } catch (Exception exception) {
            // Expected
        }
    }

    @Test
    public void invalidTestCase_6() {

        try {

            List<File> list = new ArrayList<>();
            list.add(new File("\u0000"));

            float output = diskChecker.getTotalDiskUsage(list);
            System.out.println("invalidTestCase_2: " + output);
            fail();

        } catch (Exception exception) {
            // Expected
        }
    }

    @Test
    public void invalidTestCase_7() {

        try {

            diskChecker.getTotalDiskUsage(null);
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

            output = diskChecker.getTotalDiskUsage(list);
            if (output == 0)
                fail();

        } catch (Exception exception) {
            fail();
        }
    }
}
