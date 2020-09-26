package andrea.DiskChecker;

import andrea.DiskCheckerTest;
import org.apache.bookkeeper.util.DiskChecker;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TestCheckDir extends DiskCheckerTest {

    private final String methodName = "checkDir";

    @Test
    public void validTestCase_1() {

        try {

            float output = diskChecker.checkDir(directoryFile);
            assertTrue(output > 0);

        } catch (Exception exception) {
            printExceptionMessage(methodName, exception);
            fail();
        }
    }

    @Test
    public void invalidTestCase_1() {

        try {
            diskChecker.checkDir(regularFile);
            fail();
        } catch (Exception exception) {
            printExceptionMessage(methodName, exception);
        }

        try {
            diskChecker.checkDir(characterDeviceFile);
            fail();
        } catch (Exception exception) {
            printExceptionMessage(methodName, exception);
        }
    }

    @Test
    public void invalidTestCase_2() {

        try {
            diskChecker.checkDir(new File("/root/notMakeAbleDir"));
            fail();
        } catch (Exception exception) {
            printExceptionMessage(methodName, exception);
        }
    }

    @Test
    public void invalidTestCase_3() {

        try {
            diskChecker.checkDir(new File("/root"));
            fail();
        } catch (Exception exception) {
            printExceptionMessage(methodName, exception);
        }
    }

    @Test
    public void invalidTestCase_4() {

        try {
            diskChecker.checkDir(new File("/home")); // Alternatively, you can use "/" as non writable directory.
            fail();

        } catch (Exception exception) {
            printExceptionMessage(methodName, exception);
        }
    }

    @Test
    public void invalidTestCase_5() {

        try {
            diskChecker.checkDir(new File("\u0000")); // "\u0000" is unicode character 'NULL'
            fail();

        } catch (Exception exception) {
            printExceptionMessage(methodName, exception);
        }
    }

    @Test
    public void invalidTestCase_6() {

        try {
            diskChecker.checkDir(null);
            fail();

        } catch (Exception exception) {
            printExceptionMessage(methodName, exception);
        }
    }

    // To increase adequacy...
    @Test
    public void checkDirectoryUnderFollowingConditions() {

        DiskChecker diskChecker = new DiskChecker(0.9f,0.5f);
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
                printExceptionMessage(methodName, exception);

            } catch (DiskChecker.DiskOutOfSpaceException exception) {

                System.err.printf("Disk Usage %f\n", exception.getUsage());
                printExceptionMessage(methodName, exception);

            } catch (DiskChecker.DiskErrorException exception) {
                printExceptionMessage(methodName, exception);
            }
        }
    }
}