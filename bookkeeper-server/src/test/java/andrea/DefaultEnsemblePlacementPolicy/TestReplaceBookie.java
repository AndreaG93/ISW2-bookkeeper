package andrea.DefaultEnsemblePlacementPolicy;

import org.apache.bookkeeper.net.BookieSocketAddress;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class TestReplaceBookie extends TestDefaultEnsemblePlacementPolicy {

    public TestReplaceBookie(boolean input) {
        super(input);

        Set<BookieSocketAddress> bookies = getBookieSocketAddresses(10);

        Set<BookieSocketAddress> deadBookie = policy.onClusterChanged(bookies, new HashSet<>());
        assertTrue(deadBookie.isEmpty());
    }

    @Test
    public void validTest_1() {

        try {

            List<BookieSocketAddress> currentEnsemble = getValidEnsemble(10);
            BookieSocketAddress bookieToReplace = currentEnsemble.get(0);


            BookieSocketAddress output = policy.replaceBookie(5,5,5, new HashMap<>(), currentEnsemble, bookieToReplace, new HashSet<>()).getResult();
            assertNotNull(output);
            assertNotEquals(bookieToReplace, output);

        } catch (Exception exception) {
            fail();
        }
    }

    @Test
    public void invalidTest_1() {

        try {

            List<BookieSocketAddress> currentEnsemble = getValidEnsemble(10);
            BookieSocketAddress bookieToReplace = new BookieSocketAddress("NotExistent", 1000);


            policy.replaceBookie(5,5,5, new HashMap<>(), currentEnsemble, bookieToReplace, new HashSet<>());
            fail();

        } catch (Exception exception) {
            // Expected
        }
    }

    @Test
    public void invalidTest_2() {

        try {

            List<BookieSocketAddress> currentEnsemble = getValidEnsemble(10);

            policy.replaceBookie(5,5,5, new HashMap<>(), currentEnsemble, null, new HashSet<>());
            fail();

        } catch (Exception exception) {
            // Expected
        }
    }

    @Test
    public void invalidTest_3() {

        try {

            BookieSocketAddress bookieToReplace = new BookieSocketAddress("NotExistent", 1000);
            policy.replaceBookie(5,5,5, new HashMap<>(), null, bookieToReplace, new HashSet<>());
            fail();

        } catch (Exception exception) {
            // Expected
        }
    }

    @Test
    public void invalidTest_4() {

        try {

            BookieSocketAddress bookieToReplace = new BookieSocketAddress("NotExistent", 1000);
            policy.replaceBookie(5,5,5, new HashMap<>(), new ArrayList<>(), bookieToReplace, new HashSet<>());
            fail();

        } catch (Exception exception) {
            // Expected
        }
    }

    @Test
    public void invalidTest_6() {

        try {

            HashSet<BookieSocketAddress> bookie = getBookieSocketAddresses(10);
            this.policy.onClusterChanged(bookie, new HashSet<>());

            List<BookieSocketAddress> currentEnsemble = getValidEnsemble(10);
            BookieSocketAddress bookieToReplace = currentEnsemble.get(0);

            policy.replaceBookie(5,1,1, new HashMap<>(), currentEnsemble, bookieToReplace, bookie);
            fail();

        } catch (Exception exception) {
            // Expected
        }
    }

    @Test
    public void invalidTest_7() {

        try {

            List<BookieSocketAddress> currentEnsemble = getValidEnsemble(10);
            BookieSocketAddress bookieToReplace = currentEnsemble.get(0);

            policy.replaceBookie(1000,1,1, new HashMap<>(), currentEnsemble, bookieToReplace, new HashSet<>());
            fail();

        } catch (Exception exception) {
            // Expected
        }
    }

    @Test
    public void invalidTest_8() {

        try {

            List<BookieSocketAddress> currentEnsemble = getValidEnsemble(10);
            BookieSocketAddress bookieToReplace = currentEnsemble.get(0);

            policy.replaceBookie(1,2,1, new HashMap<>(), currentEnsemble, bookieToReplace, new HashSet<>());
            fail();

        } catch (Exception exception) {
            // Expected
        }
    }

    @Test
    public void invalidTest_9() {

        try {

            List<BookieSocketAddress> currentEnsemble = getValidEnsemble(10);
            BookieSocketAddress bookieToReplace = currentEnsemble.get(0);

            policy.replaceBookie(-1,1,1, new HashMap<>(), currentEnsemble, bookieToReplace, new HashSet<>());
            fail();

        } catch (Exception exception) {
            // Expected
        }
    }
}
