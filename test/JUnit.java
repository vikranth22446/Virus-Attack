import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import cell.*;
import helper.*;
import virus.*;
// by FYICenter.com

// specify a runner class: Suite.class
@RunWith(Suite.class)

// specify an array of test classes
@Suite.SuiteClasses({
        AntiVirusManagerTests.class,
        AntiVirusTests.class,
        AttackerTests.class,
        CellManagerTests.class,
        CellTests.class,
        HealthBarTests.class,
        LocatableTests.class,
        RedCellTests.class,
        ScoreBoardTests.class,
        SickCellTests.class,
        VirusGroupManagerTests.class,
        VirusGroupTests.class,
        VirusTests.class,
        WhiteCellTests.class
})

/**
 * This Class runs all the Tests
 */
public class JUnit {
}
