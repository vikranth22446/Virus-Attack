package virus;

import main.antivirus.AntiVirus;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by vikranth on 10/21/2016.
 */
public class AntiVirusTests {
    /**
     * main.antivirus.AntiVirus Test Classes
     * antiVirusConstructor(): Checks if the antiVirus constructor is right
     * antiVirusToString(): Checks if the toString is null
     *
     * @author Vikranth Srivatsa
     */
    @Test
    public void antiVirusConstructor() {
        AntiVirus antiVirus = new AntiVirus(20, 30);
        String test = antiVirus.toString();
        assertTrue("<<Invalid Construction Of objects>>", test.equals("main.antivirus.AntiVirus{currentFollowVirus=null, positionOfVirus=0, follow=false}"));
    }


    @Test
    public void antiVirusToString() {
        AntiVirus antiVirus = new AntiVirus(20, 30);
        assertNotNull("<<The to String is not Null>>", antiVirus.toString());
    }

}
