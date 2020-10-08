package andrea.DiskChecker;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestCheckDir.class,
        TestDiskChecker.class,
        TestGetTotalDiskSpace.class,
        TestGetTotalDiskUsage.class,
        TestGetTotalFreeSpace.class,
        TestTest.class
})
public class TestSuite {
}
