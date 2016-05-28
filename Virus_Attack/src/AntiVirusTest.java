package src;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AntiVirusTest {
    private AntiVirus antiVirus;
    @Before
    public void setUp(){
        antiVirus = new AntiVirus(30,40);
    }
    @Test
    public void toStringTest(){
        assertNotNull(antiVirus.toString());
    }
    @Test
    public void constructorTest(){
        String testString = "AntiVirus{currentFollowVirus=null, positionOfVirus=0, follow=false}";
        assertTrue("<<Values are not Constructed Properly>>",testString.equalsIgnoreCase(antiVirus.toString()));
    }
    @Test
    public void drawTest(){
        VirusGroupManager virusGroupManager = new VirusGroupManager();
        VirusGroupManager.addVirus(40,40);

    }

}
