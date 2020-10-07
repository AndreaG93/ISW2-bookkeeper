package andrea.DefaultEnsemblePlacementPolicy;

import org.apache.bookkeeper.client.DefaultEnsemblePlacementPolicy;
import org.apache.bookkeeper.conf.ClientConfiguration;
import org.apache.bookkeeper.net.BookieSocketAddress;
import org.junit.Test;
import org.mockito.Mockito;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(Parameterized.class)
public class TestDefaultEnsemblePlacementPolicy {

    protected DefaultEnsemblePlacementPolicy policy;
    private static int nextBookieSocketAddressID = 0;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {true}, {false}
        });
    }

    public TestDefaultEnsemblePlacementPolicy(boolean input) {

        ClientConfiguration configuration = Mockito.mock(ClientConfiguration.class);

        Mockito.when(configuration.getDiskWeightBasedPlacementEnabled()).thenReturn(input);
        Mockito.when(configuration.getBookieMaxWeightMultipleForWeightBasedPlacement()).thenReturn(5);

        this.policy = new DefaultEnsemblePlacementPolicy();
        this.policy.initialize(configuration, Optional.empty(), null, null, null);
    }


    protected static HashSet<BookieSocketAddress> getBookieSocketAddresses(int quantity) {

        HashSet<BookieSocketAddress> output = new HashSet<>();
        for (int i = 0; i < quantity; i++, nextBookieSocketAddressID++) {

            String hostname = String.format("127.0.0.%d", nextBookieSocketAddressID);
            int port = 3000 + nextBookieSocketAddressID;

            output.add(new BookieSocketAddress(hostname, port));
        }

        return output;
    }

    protected static Set<BookieSocketAddress> getBookieSocketAddressesFrom(Set<BookieSocketAddress> source, int quantity) {

        Set<BookieSocketAddress> output = new HashSet<>();

        int i = 0;

        for (BookieSocketAddress x : source) {

            output.add(x);
            i++;

            if (i == quantity)
                break;
        }

        return output;
    }
}