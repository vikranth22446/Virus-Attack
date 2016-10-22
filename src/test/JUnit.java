package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.cell.*;
import test.helper.AttackerTests;
import test.helper.HealthBarTests;
import test.helper.LocatableTests;
import test.helper.ScoreBoardTests;
import test.virus.*;
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

// the actual class is empty
public class JUnit {
}
