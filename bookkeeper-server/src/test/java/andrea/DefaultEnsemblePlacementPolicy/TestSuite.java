package andrea.DefaultEnsemblePlacementPolicy;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestDefaultEnsemblePlacementPolicy.class,
        TestInitialize.class,
        TestNewEnsemble.class,
        TestOnClusterChanged.class,
        TestOther.class,
        TestReplaceBookie.class,
        TestUpdateBookieInfo.class,
})
public class TestSuite {
}