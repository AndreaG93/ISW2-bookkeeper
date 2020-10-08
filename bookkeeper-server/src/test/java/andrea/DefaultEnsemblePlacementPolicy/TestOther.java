package andrea.DefaultEnsemblePlacementPolicy;


import org.apache.bookkeeper.client.DistributionSchedule;
import org.apache.bookkeeper.net.BookieSocketAddress;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class TestOther extends TestDefaultEnsemblePlacementPolicy {

    public TestOther(boolean input) {
        super(input);
    }

    @Test
    public void test_1() {

        List<BookieSocketAddress> ensemble = new ArrayList<>();

        DistributionSchedule.WriteSet writeSet = Mockito.mock(DistributionSchedule.WriteSet.class);

        policy.reorderReadLACSequence(ensemble, null, writeSet);

        Mockito.verify(writeSet).addMissingIndices(0);
    }

    @Test
    public void test_2() {
        policy.registerSlowBookie(null, 9);
    }

    @Test
    public void test_3() {
        policy.reorderReadSequence(null, null, null);
    }

    @Test
    public void test_4() {
        policy.isEnsembleAdheringToPlacementPolicy(null, 0, 0);
    }

    @Test
    public void test_5() {
        policy.uninitalize();
    }
}
