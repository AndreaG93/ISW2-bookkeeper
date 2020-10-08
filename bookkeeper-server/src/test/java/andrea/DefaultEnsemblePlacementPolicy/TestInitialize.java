package andrea.DefaultEnsemblePlacementPolicy;

import org.apache.bookkeeper.client.DefaultEnsemblePlacementPolicy;
import org.apache.bookkeeper.conf.ClientConfiguration;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.Assert.fail;

public class TestInitialize {

    @Test
    public void validTestCase_1() {

        try {
            ClientConfiguration configuration = Mockito.mock(ClientConfiguration.class);

            Mockito.when(configuration.getDiskWeightBasedPlacementEnabled()).thenReturn(true);
            Mockito.when(configuration.getBookieMaxWeightMultipleForWeightBasedPlacement()).thenReturn(0);

            DefaultEnsemblePlacementPolicy policy = new DefaultEnsemblePlacementPolicy();
            policy.initialize(configuration, Optional.empty(), null, null, null);

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void validTestCase_2() {

        try {

            ClientConfiguration configuration = Mockito.mock(ClientConfiguration.class);

            Mockito.when(configuration.getDiskWeightBasedPlacementEnabled()).thenReturn(false);
            Mockito.when(configuration.getBookieMaxWeightMultipleForWeightBasedPlacement()).thenReturn(0);

            DefaultEnsemblePlacementPolicy policy = new DefaultEnsemblePlacementPolicy();
            policy.initialize(configuration, Optional.empty(), null, null, null);

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void invalidTestCase_1() {

        try {

            DefaultEnsemblePlacementPolicy policy = new DefaultEnsemblePlacementPolicy();
            policy.initialize(null, null, null, null, null);
            fail();

        } catch (Exception e) {
            // Expected
        }
    }

    @Test
    public void additionalTestCase_1() {

        try {

            ClientConfiguration configuration = Mockito.mock(ClientConfiguration.class);

            Mockito.when(configuration.getDiskWeightBasedPlacementEnabled()).thenReturn(false);
            Mockito.when(configuration.getBookieMaxWeightMultipleForWeightBasedPlacement()).thenReturn(0);

            DefaultEnsemblePlacementPolicy policy = new DefaultEnsemblePlacementPolicy();
            if (policy.initialize(configuration, Optional.empty(), null, null, null) == null)
                fail();

        } catch (Exception e) {
            fail();
        }
    }
}
