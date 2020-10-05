package andrea.DefaultEnsemblePlacementPolicy;

import org.junit.Test;

public class TestOther extends TestDefaultEnsemblePlacementPolicy {

    public TestOther(boolean input) {
        super(input);
    }

    @Test
    public void test() {
        policy.reorderReadLACSequence(null, null, null);
    }
}
