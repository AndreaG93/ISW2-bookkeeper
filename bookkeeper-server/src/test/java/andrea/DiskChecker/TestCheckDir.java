package andrea.DiskChecker;

import org.apache.bookkeeper.util.DiskChecker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TestCheckDir extends TestDiskChecker {

    @Test
    public void validTestCase_1() {

        try {

            float output = diskChecker.checkDir(directoryFile);
            assertTrue(output > 0);

        } catch (Exception exception) {
            fail();
        }
    }

    @Test
    public void validTestCase_2() {
        try {

            float output = diskChecker.checkDir(new File("./makeMultiple/dir/path"));
            assertTrue(output > 0);

        } catch (Exception exception) {
            fail();
        } finally {

            if (!new File("./makeMultiple/dir/path").delete())
                fail();
            if (!new File("./makeMultiple/dir").delete())
                fail();
            if (!new File("./makeMultiple").delete())
                fail();
        }
    }

    @Test
    public void invalidTestCase_1() {

        try {
            diskChecker.checkDir(characterDeviceFile);
            fail();
        } catch (Exception exception) {
            // Expected
        }
    }

    @Test
    public void invalidTestCase_2() {

        try {
            diskChecker.checkDir(new File("/root/notMakeAbleDir"));
            fail();
        } catch (Exception exception) {
            // Expected
        }
    }

    @Test
    public void invalidTestCase_3() {

        try {
            diskChecker.checkDir(new File("/root"));
            fail();
        } catch (Exception exception) {
            // Expected
        }
    }

    @Test
    public void invalidTestCase_4() {

        try {
            diskChecker.checkDir(new File("/home"));
            fail();
        } catch (Exception exception) {
            // Expected
        }
    }

    @Test
    public void invalidTestCase_5() {

        try {
            diskChecker.checkDir(new File("\u0000")); // "\u0000" is unicode character 'NULL'
            fail();
        } catch (Exception exception) {
            // Expected
        }
    }

    @Test
    public void invalidTestCase_6() {

        try {
            diskChecker.checkDir(null);
            fail();

        } catch (Exception exception) {
            // Expected
        }
    }

    @Test
    public void additionalTest_1() {

        DiskChecker diskChecker = new DiskChecker(0.9f, 0.5f);
        File directory = Mockito.mock(File.class);

        Mockito.when(directory.getTotalSpace()).thenReturn(100L);
        Mockito.when(directory.exists()).thenReturn(true);

        long[] usableSpaceValues = {0L, 40L}; // First: Disk is full -- Second: Disk usage exceeds threshold.

        for (long x : usableSpaceValues) {

            try {

                Mockito.when(directory.getUsableSpace()).thenReturn(x);
                diskChecker.checkDir(directory);
                fail();

            } catch (DiskChecker.DiskWarnThresholdException exception) {

                System.err.printf("Disk Usage %f\n", exception.getUsage());


            } catch (DiskChecker.DiskOutOfSpaceException exception) {

                System.err.printf("Disk Usage %f\n", exception.getUsage());


            } catch (DiskChecker.DiskErrorException exception) {
                exception.printStackTrace();
            }
        }
    }

    @Test
    public void additionalTest_2() {

        try {

            File directory = Mockito.mock(File.class);
            File canonicalFile = Mockito.mock(File.class);

            Mockito.when(directory.mkdir()).thenReturn(false);
            Mockito.when(directory.exists()).thenReturn(false);
            Mockito.when(directory.getCanonicalFile()).thenReturn(canonicalFile);

            Mockito.when(canonicalFile.getParent()).thenReturn(null);

            diskChecker.checkDir(directory);
            fail();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void additionalTest_3() {

        try {

            File directory = Mockito.mock(File.class);
            File canonicalFile = Mockito.mock(File.class);

            Mockito.when(canonicalFile.getParent()).thenReturn("/"); // That is... a parent exist
            Mockito.when(canonicalFile.mkdir()).thenReturn(false);
            Mockito.when(canonicalFile.exists()).thenReturn(true);

            Mockito.when(directory.mkdir()).thenReturn(false);
            Mockito.when(directory.exists()).thenReturn(false);
            Mockito.when(directory.isDirectory()).thenReturn(true);
            Mockito.when(directory.canRead()).thenReturn(true);
            Mockito.when(directory.canWrite()).thenReturn(true);
            Mockito.when(directory.getCanonicalFile()).thenReturn(canonicalFile);

            diskChecker.checkDir(directory);

        } catch (Exception exception) {
            fail();
        }
    }
}