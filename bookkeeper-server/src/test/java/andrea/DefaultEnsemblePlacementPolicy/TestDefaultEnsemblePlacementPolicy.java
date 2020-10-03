package andrea.DefaultEnsemblePlacementPolicy;

import org.apache.bookkeeper.client.BKException;
import org.apache.bookkeeper.client.DefaultEnsemblePlacementPolicy;
import org.apache.bookkeeper.conf.ClientConfiguration;
import org.apache.bookkeeper.net.BookieSocketAddress;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class TestDefaultEnsemblePlacementPolicy {

    protected static DefaultEnsemblePlacementPolicy policy;
    private static int nextBookieSocketAddressID = 0;

    @BeforeClass
    public static void setupUp() {

        ClientConfiguration configuration = Mockito.mock(ClientConfiguration.class);

        Mockito.when(configuration.getDiskWeightBasedPlacementEnabled()).thenReturn(true);
        Mockito.when(configuration.getBookieMaxWeightMultipleForWeightBasedPlacement()).thenReturn(5);

        policy = new DefaultEnsemblePlacementPolicy();
        policy.initialize(configuration, null, null, null, null);

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

    protected static List<BookieSocketAddress> getValidEnsemble(int size) {

        HashSet<BookieSocketAddress> data = getBookieSocketAddresses(size);

        return new ArrayList<>(data);
    }



}
