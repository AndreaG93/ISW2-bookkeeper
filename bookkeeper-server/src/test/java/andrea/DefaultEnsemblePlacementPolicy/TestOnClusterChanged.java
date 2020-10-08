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
    public void validTestCase_1() {

        try {

            Set<BookieSocketAddress> deadBookie = policy.onClusterChanged(new HashSet<>(), new HashSet<>());
            assertTrue(deadBookie.isEmpty());

        } catch (Exception exception) {
            fail();
        }
    }

    @Test
    public void validTestCase_2() {

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
    public void validTestCase_3() {

        try {

            HashSet<BookieSocketAddress> writableBookies = getBookieSocketAddresses(1);
            HashSet<BookieSocketAddress> readOnlyBookies = new HashSet<>();

            Set<BookieSocketAddress> deadBookie = policy.onClusterChanged(writableBookies, readOnlyBookies);
            assertTrue(deadBookie.isEmpty());

        } catch (Exception exception) {
            fail();
        }
    }

    @Test
    public void validTestCase_4() {

        try {

            HashSet<BookieSocketAddress> writableBookies = new HashSet<>();
            HashSet<BookieSocketAddress> readOnlyBookies = getBookieSocketAddresses(1);

            Set<BookieSocketAddress> deadBookie = policy.onClusterChanged(writableBookies, readOnlyBookies);
            assertTrue(deadBookie.isEmpty());

        } catch (Exception exception) {
            fail();
        }
    }

    @Test
    public void invalidTestCase_1() {

        try {

            HashSet<BookieSocketAddress> writableBookies = getBookieSocketAddresses(1);
            HashSet<BookieSocketAddress> readOnlyBookies = getBookieSocketAddresses(1);

            writableBookies.add(readOnlyBookies.iterator().next());  // writableBookies contain also a readOnlyBookie!!

            Set<BookieSocketAddress> output = policy.onClusterChanged(writableBookies, readOnlyBookies);
            System.err.println("--> output size: " + output.size());
            fail();

        } catch (Exception exception) {
            // Expected
        }
    }

    @Test
    public void invalidTestCase_2() {

        try {

            HashSet<BookieSocketAddress> writableBookies = getBookieSocketAddresses(1);
            HashSet<BookieSocketAddress> readOnlyBookies = getBookieSocketAddresses(1);

            readOnlyBookies.add(writableBookies.iterator().next());  // readOnlyBookies contain also a writableBookie!!

            Set<BookieSocketAddress> output = policy.onClusterChanged(writableBookies, readOnlyBookies);
            System.err.println("--> output size: " + output.size());
            fail();

        } catch (Exception exception) {
            // Expected
        }
    }

    @Test
    public void invalidTestCase_3() {

        try {

            HashSet<BookieSocketAddress> bookies = new HashSet<>();
            bookies.add(null);

            Set<BookieSocketAddress> output = policy.onClusterChanged(bookies, new HashSet<>());
            System.err.println("--> output size: " + output.size());
            fail();

        } catch (Exception exception) {
            // Expected
        }
    }

    @Test
    public void invalidTestCase_4() {

        try {

            HashSet<BookieSocketAddress> bookies = new HashSet<>();
            bookies.add(null);

            Set<BookieSocketAddress> output = policy.onClusterChanged(new HashSet<>(), bookies);
            System.err.println("--> output size: " + output.size());
            fail();

        } catch (Exception exception) {
            // Expected
        }
    }

    @Test
    public void invalidTestCase_5() {

        try {

            Set<BookieSocketAddress> output = policy.onClusterChanged(null, getBookieSocketAddresses(1));
            System.err.println("--> output size: " + output.size());
            fail();

        } catch (Exception exception) {
            // Expected
        }
    }

    @Test
    public void invalidTestCase_6() {

        try {

            Set<BookieSocketAddress> output = policy.onClusterChanged(getBookieSocketAddresses(1), null);
            System.err.println("--> output size: " + output.size());
            fail();

        } catch (Exception exception) {
            // Expected
        }
    }

    @Test
    public void additionalTestCase_1() {

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
}