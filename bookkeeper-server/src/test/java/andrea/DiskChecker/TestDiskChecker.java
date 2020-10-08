package andrea.DiskChecker;

import org.apache.bookkeeper.util.DiskChecker;
import org.apache.bookkeeper.util.IOUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestDiskChecker {

    protected static DiskChecker diskChecker;

    protected static final float DEFAULT_THRESHOLD = 0.95f;

    protected static File regularFile;
    protected static File characterDeviceFile;
    protected static File directoryFile;

    protected static long totalSpace;
    protected static long totalFreeSpace;

    @BeforeClass
    public static void setupClassTest() {

        diskChecker = new DiskChecker(DEFAULT_THRESHOLD, DEFAULT_THRESHOLD);

        try {

            regularFile = File.createTempFile("regularFile", "test");
            characterDeviceFile = new File("/dev/zero");
            directoryFile = IOUtils.createTempDir("directoryFile", "test");

            File directory = new File("/");


            totalSpace = directory.getTotalSpace();
            totalFreeSpace = directory.getFreeSpace();

        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void validTestCase_1() {

        try {
            new DiskChecker(DEFAULT_THRESHOLD, DEFAULT_THRESHOLD);
        } catch (Exception exception) {
            fail();
        }
    }

    @Test
    public void invalidTestCase_1() {

        try {
            new DiskChecker(0, 0);
            fail();
        } catch (Exception exception) {
            // Expected
        }
    }

    @Test
    public void invalidTestCase_2() {

        try {
            new DiskChecker(1, 1);
            fail();
        } catch (Exception exception) {
            // Expected
        }
    }

    @Test
    public void invalidTestCase_3() {

        try {
            new DiskChecker(0.5f, 0.6f);
            fail();
        } catch (Exception exception) {
            // Expected
        }
    }

    @Test
    public void additionalTest_1() {

        DiskChecker obj = new DiskChecker(DEFAULT_THRESHOLD, DEFAULT_THRESHOLD);

        assertEquals(DEFAULT_THRESHOLD, obj.getDiskUsageThreshold(), 0.0);
        assertEquals(DEFAULT_THRESHOLD, obj.getDiskUsageWarnThreshold(), 0.0);
    }
}