package andrea;

import org.apache.bookkeeper.util.DiskChecker;
import org.apache.bookkeeper.util.IOUtils;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.fail;

public class DiskCheckerTest {

    protected static DiskChecker diskChecker;
    protected static List<File> validDirectories;

    private static Logger logger;
    private static final float DEFAULT_THRESHOLD = 0.95f;

    @BeforeClass
    public static void setupClassTest() {

        logger = Logger.getLogger(DiskCheckerTest.class.getName());

        diskChecker = new DiskChecker(DEFAULT_THRESHOLD, DEFAULT_THRESHOLD);
        validDirectories = new ArrayList<>();

        try {

            validDirectories.add(IOUtils.createTempDir("Andrea_1", "test"));
            validDirectories.add(IOUtils.createTempDir("Andrea_2", "test"));

        } catch (IOException e) {
            fail(e.getMessage());
        }

    }

    @AfterClass
    public static void cleanClassTest() {

        try {

            for (File directory : validDirectories)
                FileUtils.deleteDirectory(directory);

            validDirectories.clear();

        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    public void printExceptionMessage(String methodName, Exception exception) {

        String exceptionClassName = exception.getClass().getName();
        String exceptionMessage = exception.getMessage();

        logger.info("\n:::" + methodName + ":::" + exceptionClassName + " -> " + exceptionMessage);
    }

    // Constructor

    @Test
    public void validTestCase_1() {

        try {
            new DiskChecker(0.5f, 0.5f);
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
            printExceptionMessage("DiskChecker", exception);
        }
    }

    @Test
    public void invalidTestCase_2() {

        try {
            new DiskChecker(1, 1);
            fail();
        } catch (Exception exception) {
            printExceptionMessage("DiskChecker", exception);
        }
    }

    @Test
    public void invalidTestCase_3() {

        try {
            new DiskChecker(0.5f, 0.6f);
            fail();
        } catch (Exception exception) {
            printExceptionMessage("DiskChecker", exception);
        }
    }
}
