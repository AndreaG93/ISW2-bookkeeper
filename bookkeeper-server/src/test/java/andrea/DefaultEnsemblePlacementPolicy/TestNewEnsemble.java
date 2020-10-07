package andrea.DefaultEnsemblePlacementPolicy;

import org.apache.bookkeeper.net.BookieSocketAddress;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class TestNewEnsemble extends TestDefaultEnsemblePlacementPolicy {

    public TestNewEnsemble(boolean input) {
        super(input);
    }

    @Test
    public void validTest_1() {

        try {

            List<BookieSocketAddress> output = policy.newEnsemble(0, 0, 0, null, new HashSet<>()).getResult();
            assertEquals(output.size(), 0);

        } catch (Exception e) {
            fail();
        }
    }

    /**
     * In this test case, we assume that:
     * |K| = 10
     *
     * Since {|K| − |E| ≥ ensembleSize} AND {|E| > 0}
     * excludeBookies.size() = 5
     * ensembleSize = 5
     */
    @Test
    public void validTest_2() {

        try {

            Set<BookieSocketAddress> knownBookies = getBookieSocketAddresses(10);
            Set<BookieSocketAddress> deadBookie = policy.onClusterChanged(knownBookies, new HashSet<>());
            assertTrue(deadBookie.isEmpty());

            Set<BookieSocketAddress> excludeBookies = getBookieSocketAddressesFrom(knownBookies, 5);

            List<BookieSocketAddress> output = policy.newEnsemble(5, 5, 5, null, excludeBookies).getResult();
            assertEquals(output.size(), 5);

            // Check if output doesn't contain 'excluded bookie'...
            for (BookieSocketAddress outputBookie : output)
                assertFalse(excludeBookies.contains(outputBookie));

        } catch (Exception e) {
            fail();
        }
    }

    /**
     * In this test case, we assume that:
     * |K| = 10
     * |E| = 0
     */
    @Test
    public void validTest_3() {

        try {

            Set<BookieSocketAddress> knownBookies = getBookieSocketAddresses(10);
            Set<BookieSocketAddress> deadBookie = policy.onClusterChanged(knownBookies, new HashSet<>());
            assertTrue(deadBookie.isEmpty());

            Set<BookieSocketAddress> excludeBookies = new HashSet<>();

            List<BookieSocketAddress> output = policy.newEnsemble(5, 5, 5, null, excludeBookies).getResult();
            assertEquals(output.size(), 5);

        } catch (Exception e) {
            fail();
        }
    }

    /**
     * In this test case, we assume that:
     * |K| = 10
     * |E| = 5
     * ensembleSize = 0
     *
     * An empty ensemble is expected!
     */
    @Test
    public void validTest_4() {

        try {

            Set<BookieSocketAddress> knownBookies = getBookieSocketAddresses(10);
            Set<BookieSocketAddress> deadBookie = policy.onClusterChanged(knownBookies, new HashSet<>());
            assertTrue(deadBookie.isEmpty());

            Set<BookieSocketAddress> excludeBookies = getBookieSocketAddressesFrom(knownBookies, 5);

            List<BookieSocketAddress> output = policy.newEnsemble(0, 0, 0, null, excludeBookies).getResult();
            assertEquals(output.size(), 0);

        } catch (Exception e) {
            fail();
        }
    }

    /**
     * --> Only 'ensembleSize' parameter assumes an invalid input.
     */
    @Test
    public void invalidTest_1() {

        try {
            policy.newEnsemble(-1, -1, -1, null, new HashSet<>());
            fail();

        } catch (Exception e) {
            // Expected
        }
    }

    /**
     * --> Only 'quorumSize' parameter assumes an invalid input.
     */
    @Test
    public void invalidTest_2() {

        try {

            Set<BookieSocketAddress> knownBookies = getBookieSocketAddresses(10);
            Set<BookieSocketAddress> deadBookie = policy.onClusterChanged(knownBookies, new HashSet<>());
            assertTrue(deadBookie.isEmpty());

            List<BookieSocketAddress> output = policy.newEnsemble(1, 2, 1, null, new HashSet<>()).getResult();
            System.err.println("--> output size: " + output.size());
            fail();

        } catch (Exception e) {
            // Expected
        }
    }

    /**
     * --> Only 'ackQuorumSize' parameter assumes an invalid input.
     */
    @Test
    public void invalidTest_3() {

        try {

            Set<BookieSocketAddress> knownBookies = getBookieSocketAddresses(10);
            Set<BookieSocketAddress> deadBookie = policy.onClusterChanged(knownBookies, new HashSet<>());
            assertTrue(deadBookie.isEmpty());

            List<BookieSocketAddress> output = policy.newEnsemble(1, 1, 2, null, new HashSet<>()).getResult();
            System.err.println("--> output size: " + output.size());
            fail();

        } catch (Exception e) {
            // Expected
        }
    }

    /**
     * --> 'ensembleSize' > |K| + |E|
     */
    @Test
    public void invalidTest_4() {

        try {

            Set<BookieSocketAddress> knownBookies = getBookieSocketAddresses(10);
            Set<BookieSocketAddress> deadBookie = policy.onClusterChanged(knownBookies, new HashSet<>());
            assertTrue(deadBookie.isEmpty());

            Set<BookieSocketAddress> excludedBookies = new HashSet<>();

            int ensembleSize = excludedBookies.size() + knownBookies.size() + 1; // Boundary value...

            policy.newEnsemble(ensembleSize, ensembleSize, ensembleSize, new HashMap<>(), new HashSet<>());
            fail();

        } catch (Exception e) {
            // Expected
        }
    }

    @Test
    public void invalidTest_5() {

        try {

            Set<BookieSocketAddress> knownBookies = getBookieSocketAddresses(10);
            Set<BookieSocketAddress> deadBookie = policy.onClusterChanged(knownBookies, new HashSet<>());
            assertTrue(deadBookie.isEmpty());

            Set<BookieSocketAddress> excludedBookies = getBookieSocketAddressesFrom(knownBookies, 4);
            excludedBookies.add(null);

            List<BookieSocketAddress> output = policy.newEnsemble(5, 5, 5, new HashMap<>(), excludedBookies).getResult();
            System.err.println("--> output size: " + output.size());
            fail();

        } catch (Exception e) {
            // Expected
        }
    }

    @Test
    public void invalidTest_6() {

        try {

            Set<BookieSocketAddress> knownBookies = getBookieSocketAddresses(10);
            Set<BookieSocketAddress> deadBookie = policy.onClusterChanged(knownBookies, new HashSet<>());
            assertTrue(deadBookie.isEmpty());

            Set<BookieSocketAddress> excludedBookies = getBookieSocketAddressesFrom(knownBookies, 4);
            excludedBookies.add(new BookieSocketAddress("127.9.9.9", 1000)); // An object not belong to K...

            List<BookieSocketAddress> output = policy.newEnsemble(5, 5, 5, new HashMap<>(), excludedBookies).getResult();
            System.err.println("--> output size: " + output.size());
            fail();

        } catch (Exception e) {
            // Expected
        }
    }

    @Test
    public void invalidTest_7() {

        try {

            Set<BookieSocketAddress> knownBookies = getBookieSocketAddresses(10);
            Set<BookieSocketAddress> deadBookie = policy.onClusterChanged(knownBookies, new HashSet<>());
            assertTrue(deadBookie.isEmpty());

            List<BookieSocketAddress> output = policy.newEnsemble(5, 5, 5, new HashMap<>(), null).getResult();
            System.err.println("--> output size: " + output.size());
            fail();

        } catch (Exception e) {
            // Expected
        }
    }
}
