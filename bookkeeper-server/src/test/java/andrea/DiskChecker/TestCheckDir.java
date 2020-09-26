package andrea.DiskChecker;

import andrea.DiskCheckerTest;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
            diskChecker.checkDir(new File("/home"));
            fail();

        } catch (Exception exception) {
            printExceptionMessage(methodName, exception);
        }
    }

    @Test
    public void invalidTestCase_5() {

        try {
            diskChecker.checkDir(null);
            fail();

        } catch (Exception exception) {
            printExceptionMessage(methodName, exception);
        }
    }
}