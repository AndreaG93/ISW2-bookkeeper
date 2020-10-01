package andrea.DiskChecker;

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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestDiskChecker {

    protected static DiskChecker diskChecker;

    private static final float DEFAULT_THRESHOLD = 0.95f;

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

    @AfterClass
    public static void cleanClassTest() {
    }

    public void printExceptionMessage(String methodName, Exception exception) {

        String exceptionClassName = exception.getClass().getName();
        String exceptionMessage = exception.getMessage();

        System.err.println(":::" + methodName + ":::" + exceptionClassName + " -> " + exceptionMessage);
    }

    // Constructor

    @Test
    public void validTestCase_1() {

        try {
            DiskChecker diskChecker = new DiskChecker(DEFAULT_THRESHOLD, DEFAULT_THRESHOLD);

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