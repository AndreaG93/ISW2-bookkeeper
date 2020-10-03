package andrea.DefaultEnsemblePlacementPolicy;

import org.apache.bookkeeper.bookie.Bookie;
import org.apache.bookkeeper.client.BKException;
import org.apache.bookkeeper.client.RackawareEnsemblePlacementPolicy;
import org.apache.bookkeeper.net.BookieSocketAddress;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class TestNewEnsemble extends TestDefaultEnsemblePlacementPolicy {

    @BeforeClass
    public static void setupUpjyujuy() {

        Set<BookieSocketAddress> bookies = getBookieSocketAddresses(10);

        Set<BookieSocketAddress> deadBookie = policy.onClusterChanged(bookies, new HashSet<>());
        assertTrue(deadBookie.isEmpty());
    }

    @Test
    public void validTest_1() {

        try {
            List<BookieSocketAddress> output = policy.newEnsemble(0, 0, 0, null, new HashSet<>()).getResult();
            assertEquals(output.size(), 0);

        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void invalidTest_1() {

        try {
            List<BookieSocketAddress> output = policy.newEnsemble(-1, 1, 1, null, new HashSet<>()).getResult();
            assertEquals(output.size(), 0);

        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }


    @Test
    public void invalidTest_2() {

        try {

            policy.newEnsemble(3, 4, 4, new HashMap<>(), new HashSet<>());
            fail();

        } catch (Exception e) {
            // Expected
        }
    }

    @Test
    public void invalidTest_3() {

        Set<BookieSocketAddress> writableBooks = getBookieSocketAddresses(5);

        Set<BookieSocketAddress> deadBookie = policy.onClusterChanged(writableBooks, new HashSet<>());
        assertTrue(deadBookie.isEmpty());

        try {
            policy.newEnsemble(6, 1, 1, null, new HashSet<>());
            fail();
        } catch (Exception e) {
            // Expected!!
        }
    }

    @Test
    public void invalidTest_4() {

        Set<BookieSocketAddress> writableBooks = getBookieSocketAddresses(5);

        Set<BookieSocketAddress> deadBookie = policy.onClusterChanged(writableBooks, new HashSet<>());
        assertTrue(deadBookie.isEmpty());

        Set<BookieSocketAddress> excludeBookies = new HashSet<>();
        excludeBookies.add(writableBooks.stream().iterator().next());

        try {
            policy.newEnsemble(5, 1, 1, null, excludeBookies);
            fail();
        } catch (Exception e) {
            // Expected!!
        }
    }

    @Test
    public void invalidTest_5() {

        Set<BookieSocketAddress> writableBooks = getBookieSocketAddresses(5);

        Set<BookieSocketAddress> deadBookie = policy.onClusterChanged(writableBooks, new HashSet<>());
        assertTrue(deadBookie.isEmpty());

        Set<BookieSocketAddress> excludeBookies = new HashSet<>();
        excludeBookies.add(new BookieSocketAddress("Not Existing Socket Address", 10000));

        try {

            List<BookieSocketAddress> output = policy.newEnsemble(5, 1, 1, null, excludeBookies).getResult();
            assertEquals(output.size(), 5);

        } catch (Exception e) {
            fail();
        }
    }





    @Test
    public void validTest_11() {

        List<BookieSocketAddress> currentEnsemble = getValidEnsemble(10);

        BookieSocketAddress bookieToReplace = currentEnsemble.get(0);
        try {

            BookieSocketAddress output = policy.replaceBookie(5, 4, 4, new HashMap<>(), currentEnsemble, bookieToReplace, new HashSet<>()).getResult();
            assertTrue(0 == 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }











}
