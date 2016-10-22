package test.virus;

import main.antivirus.AntiVirusManager;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by vikranth on 10/21/2016.
 */
public class AntiVirusManagerTests {

    @Test
    public void antiVirusManagerConstructor() {
        AntiVirusManager avm = new AntiVirusManager();
        String s = avm.toString();
        assertTrue("<<Invalid main.AntiVirusManagerTests constructor>>",
                s.contains("main.AntiVirusManagerTests[") && s.contains("anti: " + AntiVirusManager.anti.toString()));
    }

    @Test
    public void antiVirusManagerToString() {
        AntiVirusManager avm = new AntiVirusManager();
        String s = avm.toString();
        assertNotNull(s);
    }

    @Test
    public void antiVirusManagerAddAnti() {
        AntiVirusManager.addAnti(0, 0);
        assertTrue("<<main.antivirus.AntiVirus not added to anti>>", AntiVirusManager.anti.size() != 0);


    }

}
