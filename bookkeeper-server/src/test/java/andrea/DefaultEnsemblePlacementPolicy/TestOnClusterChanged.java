package andrea.DefaultEnsemblePlacementPolicy;

import org.apache.bookkeeper.net.BookieSocketAddress;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class TestOnClusterChanged extends TestDefaultEnsemblePlacementPolicy {

    public TestOnClusterChanged(boolean input) {
        super(input);
    }

    @Test
    public void validTest_1() {

        try {

            Set<BookieSocketAddress> deadBookie = policy.onClusterChanged(new HashSet<>(), new HashSet<>());
            assertTrue(deadBookie.isEmpty());

        } catch (Exception exception) {
            fail();
        }
    }

    @Test
    public void validTest_2() {

        try {

            HashSet<BookieSocketAddress> writableBookies = getBookieSocketAddresses(1);
            HashSet<BookieSocketAddress> readOnlyBookies = getBookieSocketAddresses(1);

            Set<BookieSocketAddress> deadBookie = policy.onClusterChanged(writableBookies, readOnlyBookies);
            assertTrue(deadBookie.isEmpty());

        } catch (Exception exception) {
            fail();
        }
    }

    @Test
    public void validTest_3() {

        try {

            policy.onClusterChanged(getBookieSocketAddresses(5), getBookieSocketAddresses(5));

            HashSet<BookieSocketAddress> writableBookies = getBookieSocketAddresses(1);
            HashSet<BookieSocketAddress> readOnlyBookies = getBookieSocketAddresses(1);

            Set<BookieSocketAddress> deadBookie = policy.onClusterChanged(writableBookies, readOnlyBookies);
            assertEquals(5, deadBookie.size());

        } catch (Exception exception) {
            fail();
        }
    }

    @Test
    public void invalidTest_1() {

        try {

            HashSet<BookieSocketAddress> bookies = new HashSet<>();
            bookies.add(null);

            Set<BookieSocketAddress> output = policy.onClusterChanged(bookies, getBookieSocketAddresses(1));
            System.err.println("--> output size: " + output.size());
            fail();

        } catch (Exception exception) {
            // Expected
        }
    }

    @Test
    public void invalidTest_2() {

        try {

            HashSet<BookieSocketAddress> bookies = new HashSet<>();
            bookies.add(null);

            Set<BookieSocketAddress> output = policy.onClusterChanged(getBookieSocketAddresses(1), bookies);
            System.err.println("--> output size: " + output.size());
            fail();

        } catch (Exception exception) {
            // Expected
        }
    }

    @Test
    public void invalidTest_3() {

        try {

            Set<BookieSocketAddress> output = policy.onClusterChanged(null, getBookieSocketAddresses(1));
            System.err.println("--> output size: " + output.size());
            fail();

        } catch (Exception exception) {
            // Expected
        }
    }

    @Test
    public void invalidTest_4() {

        try {

            Set<BookieSocketAddress> output = policy.onClusterChanged(getBookieSocketAddresses(1), null);
            System.err.println("--> output size: " + output.size());
            fail();

        } catch (Exception exception) {
            // Expected
        }
    }
}