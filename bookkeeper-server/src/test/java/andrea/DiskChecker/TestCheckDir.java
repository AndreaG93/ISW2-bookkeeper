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

            float output = diskChecker.checkDir(new File("./src/test/java/andrea"));
            assertTrue(output > 0);

        } catch (Exception exception) {
            printExceptionMessage(methodName, exception);
            fail();
        }
    }

    @Test
    public void validTestCase_2() {

        File buildableDirectory = null;

        try {
            buildableDirectory = new File("./buildableDirectory");

            float output = diskChecker.checkDir(buildableDirectory);
            assertTrue(output > 0);

        } catch (Exception exception) {
            printExceptionMessage(methodName, exception);
            fail();
        } finally {
            if (buildableDirectory != null && !buildableDirectory.delete())
                fail();
        }
    }

    @Test
    public void invalidTestCase_1() {

        try {
            diskChecker.checkDir(new File("./ExampleFile.txt"));
            fail();
        } catch (Exception exception) {
            printExceptionMessage(methodName, exception);
        }

        try {
            diskChecker.checkDir(new File("/dev/zero"));
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