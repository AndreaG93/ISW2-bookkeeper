package andrea.DefaultEnsemblePlacementPolicy;

import andrea.Common.TestUtil;
import org.apache.bookkeeper.net.BookieSocketAddress;
import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.*;

public class TestOnClusterChanged extends TestDefaultEnsemblePlacementPolicy {

    @Test
    public void validTest_1() {

        Set<BookieSocketAddress> writableBookies = getBookieSocketAddresses(0);
        Set<BookieSocketAddress> readOnlyBookies = getBookieSocketAddresses(0);

        Set<BookieSocketAddress> deadBookie = policy.onClusterChanged(writableBookies, readOnlyBookies);
        assertTrue(deadBookie.isEmpty());
    }

    @Test
    public void validTest_2() {

        HashSet<BookieSocketAddress> writableBookies = getBookieSocketAddresses(5);
        HashSet<BookieSocketAddress> readOnlyBookies = getBookieSocketAddresses(5);

        Set<BookieSocketAddress> deadBookie = policy.onClusterChanged(writableBookies, readOnlyBookies);
        assertTrue(deadBookie.isEmpty());

        BookieSocketAddress crashedBookie = writableBookies.iterator().next();  // Pick first...

        HashSet<BookieSocketAddress> newViewAboutWritableBookie = new HashSet<>(writableBookies);
        newViewAboutWritableBookie.remove(crashedBookie);

        deadBookie = policy.onClusterChanged(newViewAboutWritableBookie, readOnlyBookies);
        assertEquals(deadBookie.size(), 1);
    }

    @Test
    public void validTest_3() {

        HashSet<BookieSocketAddress> writableBookies = getBookieSocketAddresses(5);
        HashSet<BookieSocketAddress> readOnlyBookies = getBookieSocketAddresses(5);

        Set<BookieSocketAddress> deadBookie = policy.onClusterChanged(writableBookies, readOnlyBookies);
        assertTrue(deadBookie.isEmpty());

        deadBookie = policy.onClusterChanged(writableBookies, readOnlyBookies);
        assertEquals(deadBookie.size(), 0);
    }

    @Test
    public void invalidTest_1() {

        try {

            HashSet<BookieSocketAddress> bookies = getBookieSocketAddresses(1);

            Set<BookieSocketAddress> deadBookie = policy.onClusterChanged(bookies, null);
            assertTrue(deadBookie.isEmpty());

        } catch (Exception exception) {
            TestUtil.printExceptionMessage("onClusterChanged", exception);
            fail();
        }
    }

    @Test
    public void invalidTest_2() {

        try {

            HashSet<BookieSocketAddress> bookies = getBookieSocketAddresses(1);

            Set<BookieSocketAddress> deadBookie = policy.onClusterChanged(null, bookies);
            assertTrue(deadBookie.isEmpty());

        } catch (Exception exception) {
            TestUtil.printExceptionMessage("onClusterChanged", exception);
            fail();
        }
    }





}


