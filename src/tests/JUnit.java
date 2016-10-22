package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.cell.*;
import tests.helper.AttackerTests;
import tests.helper.HealthBarTests;
import tests.helper.LocatableTests;
import tests.helper.ScoreBoardTests;
import tests.virus.*;
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
