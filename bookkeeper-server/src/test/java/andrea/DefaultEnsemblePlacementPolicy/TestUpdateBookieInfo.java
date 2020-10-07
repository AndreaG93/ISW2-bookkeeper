package andrea.DefaultEnsemblePlacementPolicy;

import org.apache.bookkeeper.client.BookieInfoReader;
import org.apache.bookkeeper.net.BookieSocketAddress;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.fail;

public class TestUpdateBookieInfo extends TestDefaultEnsemblePlacementPolicy {

    public TestUpdateBookieInfo(boolean input) {
        super(input);
    }

    @Test
    public void validTest_1() {

        try {

            policy.updateBookieInfo(new HashMap<>());

        } catch (Exception exception) {
            fail();
        }
    }

    @Test
    public void validTest_2() {

        try {

            BookieSocketAddress bookieSocketAddress = new BookieSocketAddress("127.0.0.1", 5000);
            BookieInfoReader.BookieInfo bookieInfo = new BookieInfoReader.BookieInfo();

            HashMap<BookieSocketAddress, BookieInfoReader.BookieInfo> map = new HashMap<>();
            map.put(bookieSocketAddress, bookieInfo);

            policy.updateBookieInfo(map);

        } catch (Exception exception) {
            exception.printStackTrace();
            fail();
        }
    }

    @Test
    public void invalidTest_1() {

        try {

            HashMap<BookieSocketAddress, BookieInfoReader.BookieInfo> map = new HashMap<>();
            map.put(null, new BookieInfoReader.BookieInfo());

            policy.updateBookieInfo(map);
            fail();

        } catch (Exception exception) {
            // Expected
        }
    }

    @Test
    public void invalidTest_2() {

        try {

            HashMap<BookieSocketAddress, BookieInfoReader.BookieInfo> map = new HashMap<>();
            map.put(new BookieSocketAddress("127.0.0.1", 500), null);

            policy.updateBookieInfo(map);
            fail();

        } catch (Exception exception) {
            // Expected
        }
    }

    @Test
    public void invalidTest_3() {

        try {

            HashMap<BookieSocketAddress, BookieInfoReader.BookieInfo> map = new HashMap<>();
            map.put(null, null);

            policy.updateBookieInfo(map);
            fail();

        } catch (Exception exception) {
            // Expected
        }
    }

    @Test
    public void invalidTest_4() {

        try {

            policy.updateBookieInfo(null);
            fail();

        } catch (Exception exception) {
            // Expected
        }
    }
}
