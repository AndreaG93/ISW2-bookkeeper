package andrea.DefaultEnsemblePlacementPolicy;

import org.apache.bookkeeper.net.BookieSocketAddress;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class TestNewEnsemble extends TestDefaultEnsemblePlacementPolicy {


    public TestNewEnsemble(boolean input) {
        super(input);

        Set<BookieSocketAddress> bookies = getBookieSocketAddresses(10);

        Set<BookieSocketAddress> deadBookie = policy.onClusterChanged(bookies, new HashSet<>());
        assertTrue(deadBookie.isEmpty());
    }

    @Test
    public void validTest_1() {

        try {
            List<BookieSocketAddress> output = policy.newEnsemble(0, 0, 0, new HashMap<>(), new HashSet<>()).getResult();
            assertEquals(output.size(), 0);

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void validTest_2() {

        try {
            List<BookieSocketAddress> output = policy.newEnsemble(5, 4, 4, new HashMap<>(), new HashSet<>()).getResult();
            assertEquals(output.size(), 5);

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void validTest_3() {

        try {

            HashSet<BookieSocketAddress> bookies = getBookieSocketAddresses(10);

            Set<BookieSocketAddress> deadBookie = policy.onClusterChanged(bookies, new HashSet<>());
            assertEquals(10, deadBookie.size());

            HashSet<BookieSocketAddress> excludedBookies = new HashSet<>();
            BookieSocketAddress excludedBookie = bookies.iterator().next();
            excludedBookies.add(excludedBookie);

            List<BookieSocketAddress> output = policy.newEnsemble(9, 0, 0, new HashMap<>(), excludedBookies).getResult();

            for (BookieSocketAddress obj : output)
                assertNotEquals(excludedBookie, obj);

            assertEquals(9, output.size());

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void invalidTest_1() {

        try {
            policy.newEnsemble(-1, 1, 1, new HashMap<>(), new HashSet<>());
            fail();

        } catch (Exception e) {
            // Expected
        }
    }

    @Test
    public void invalidTest_2() {

        try {
            List<BookieSocketAddress> output = policy.newEnsemble(1, 2, 2, new HashMap<>(), new HashSet<>()).getResult();
            System.err.println("--> output size: " + output.size());
            fail();

        } catch (Exception e) {
            // Expected
        }
    }

    @Test
    public void invalidTest_3() {

        try {

            policy.newEnsemble(100, 4, 4, new HashMap<>(), new HashSet<>());
            fail();

        } catch (Exception e) {
            // Expected
        }
    }

    @Test
    public void invalidTest_4() {

        try {

            policy.newEnsemble(5, 4, 4, new HashMap<>(), null);
            fail();

        } catch (Exception e) {
            // Expected
        }
    }

    @Test
    public void invalidTest_5() {

        try {

            HashSet<BookieSocketAddress> excludeBookies = new HashSet<>();
            excludeBookies.add(null);
            excludeBookies.add(new BookieSocketAddress("NotExistent", 55));

            List<BookieSocketAddress> output = policy.newEnsemble(5, 4, 4, new HashMap<>(), excludeBookies).getResult();
            assertEquals(5, output.size());

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void invalidTest_6() {

        try {

            HashSet<BookieSocketAddress> excludeBookies = new HashSet<>();
            excludeBookies.add(null);
            excludeBookies.add(new BookieSocketAddress("NotExistent", 55));

            List<BookieSocketAddress> output = policy.newEnsemble(5, 4, 4, new HashMap<>(), excludeBookies).getResult();
            assertEquals(5, output.size());

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void invalidTest_7() {

        try {
            policy.newEnsemble(5, -1, 1, new HashMap<>(), new HashSet<>());
            fail();
        } catch (Exception e) {
            // Expected!!
        }
    }

    @Test
    public void invalidTest_8() {

        try {
            policy.newEnsemble(5, 1, -1, new HashMap<>(), new HashSet<>());
            fail();
        } catch (Exception e) {
            // Expected!!
        }
    }
}
