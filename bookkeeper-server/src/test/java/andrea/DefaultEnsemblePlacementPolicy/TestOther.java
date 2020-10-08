package andrea.DefaultEnsemblePlacementPolicy;


import org.apache.bookkeeper.client.DistributionSchedule;
import org.apache.bookkeeper.net.BookieSocketAddress;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;

public class TestOther extends TestDefaultEnsemblePlacementPolicy {

    public TestOther(boolean input) {
        super(input);
    }

    @Test
    public void test_1() {

        List<BookieSocketAddress> ensemble = new ArrayList<>();

        DistributionSchedule.WriteSet writeSet = Mockito.mock(DistributionSchedule.WriteSet.class);

        if (policy.reorderReadLACSequence(ensemble, null, writeSet) == null)
            fail();

        Mockito.verify(writeSet).addMissingIndices(0);
    }

    @Test
    public void test_2() {
        policy.registerSlowBookie(null, 9);
    }

    @Test
    public void test_3() {
        if (policy.reorderReadSequence(null, null, null) != null)
            fail();
    }

    @Test
    public void test_4() {
        if (policy.isEnsembleAdheringToPlacementPolicy(null, 0, 0) == null)
            fail();
    }

    @Test
    public void test_5() {
        policy.uninitalize();
    }

    @Test
    public void additionalTest_1() {

        DistributionSchedule.WriteSet writeSet = Mockito.mock(DistributionSchedule.WriteSet.class);

        if (policy.reorderReadSequence(null, null, writeSet) == null)
            fail();
    }
}
