package andrea.DefaultEnsemblePlacementPolicy;

import org.apache.bookkeeper.net.BookieSocketAddress;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class TestReplaceBookie extends TestDefaultEnsemblePlacementPolicy {

    public TestReplaceBookie(boolean input) {
        super(input);
    }

    @Test
    public void validTestCase_1() {

        try {

            Set<BookieSocketAddress> knownBookies = getBookieSocketAddresses(10);
            Set<BookieSocketAddress> deadBookie = policy.onClusterChanged(knownBookies, new HashSet<>());
            assertTrue(deadBookie.isEmpty());

            List<BookieSocketAddress> currentEnsemble = policy.newEnsemble(5, 5, 5, null, new HashSet<>()).getResult();
            BookieSocketAddress bookieToReplace = currentEnsemble.get(0);


            BookieSocketAddress output = policy.replaceBookie(5, 5, 5, new HashMap<>(), currentEnsemble, bookieToReplace, new HashSet<>()).getResult();
            assertNotNull(output);
            assertNotEquals(bookieToReplace, output);

        } catch (Exception exception) {
            fail();
        }
    }

    /**
     * bookieToReplace does not belong to currentEnsemble
     */
    @Test
    public void invalidTestCase_1() {

        try {

            Set<BookieSocketAddress> knownBookies = getBookieSocketAddresses(10);
            Set<BookieSocketAddress> deadBookie = policy.onClusterChanged(knownBookies, new HashSet<>());
            assertTrue(deadBookie.isEmpty());

            List<BookieSocketAddress> currentEnsemble = policy.newEnsemble(5, 5, 5, null, new HashSet<>()).getResult();
            BookieSocketAddress bookieToReplace = new BookieSocketAddress("190.190.190.190", 190);

            policy.replaceBookie(5, 5, 5, new HashMap<>(), currentEnsemble, bookieToReplace, new HashSet<>());
            fail();

        } catch (Exception exception) {
            // Expected
        }
    }

    /**
     * bookieToReplace is null
     */
    @Test
    public void invalidTestCase_2() {

        try {

            Set<BookieSocketAddress> knownBookies = getBookieSocketAddresses(10);
            Set<BookieSocketAddress> deadBookie = policy.onClusterChanged(knownBookies, new HashSet<>());
            assertTrue(deadBookie.isEmpty());

            List<BookieSocketAddress> currentEnsemble = policy.newEnsemble(5, 5, 5, null, new HashSet<>()).getResult();

            policy.replaceBookie(5, 5, 5, new HashMap<>(), currentEnsemble, null, new HashSet<>());
            fail();

        } catch (Exception exception) {
            // Expected
        }
    }

    /**
     * currentEnsemble contains one element 'null'
     */
    @Test
    public void invalidTestCase_3() {

        try {

            Set<BookieSocketAddress> knownBookies = getBookieSocketAddresses(10);
            Set<BookieSocketAddress> deadBookie = policy.onClusterChanged(knownBookies, new HashSet<>());
            assertTrue(deadBookie.isEmpty());

            List<BookieSocketAddress> currentEnsemble = policy.newEnsemble(5, 5, 5, null, new HashSet<>()).getResult();
            currentEnsemble.add(null);
            BookieSocketAddress bookieToReplace = currentEnsemble.get(0);

            policy.replaceBookie(5, 5, 5, new HashMap<>(), currentEnsemble, bookieToReplace, new HashSet<>());
            fail();

        } catch (Exception exception) {
            // Expected
        }
    }

    /**
     * currentEnsemble contains one element which not belong to 'KnownBookies'
     */
    @Test
    public void invalidTestCase_4() {

        try {

            Set<BookieSocketAddress> knownBookies = getBookieSocketAddresses(10);
            Set<BookieSocketAddress> deadBookie = policy.onClusterChanged(knownBookies, new HashSet<>());
            assertTrue(deadBookie.isEmpty());

            List<BookieSocketAddress> currentEnsemble = policy.newEnsemble(5, 5, 5, null, new HashSet<>()).getResult();
            currentEnsemble.add(new BookieSocketAddress("190.190.190.190", 190));
            BookieSocketAddress bookieToReplace = currentEnsemble.get(0);

            policy.replaceBookie(5, 5, 5, new HashMap<>(), currentEnsemble, bookieToReplace, new HashSet<>());
            fail();

        } catch (Exception exception) {
            // Expected
        }
    }
}
