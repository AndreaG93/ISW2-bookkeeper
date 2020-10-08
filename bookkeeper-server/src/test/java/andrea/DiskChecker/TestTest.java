package andrea.DiskChecker;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;

public class TestTest extends TestDiskChecker {

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

    @Test
    public void additionalTest_2() {

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

    @Test
    public void additionalTest_3() {

        float output;

        try {

            List<File> list = new ArrayList<>();
            list.add(new File("/"));
            list.add(new File("/bin"));
            list.add(new File("/usr"));

            output = diskChecker.getTotalFreeSpace(list);
            if (output == 0)
                fail();

        } catch (Exception exception) {
            fail();
        }
    }
}
