package virus;

import main.virus.Virus;
import main.virus.VirusGroup;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * main.virus.Virus Group Tests:
 * virusGroupConstructor(): Checks if virus group constructor is right
 * virusGroupOtherConstructor(): Checks overload constructor
 * virusGroupAddVirus(): Checks if virus is added
 * virusGroupRemove(): Checks if viruses is removed
 * virusGroupGetVirus(): checks if the right virus is returned when you call getVirus
 * virusGroupSetCoord() sets the coordinates of the viruses
 *
 * @author Alex M
 */
public class VirusGroupTests {
    private int xCoord;

    private int yCoord;

    @Before
    public void virusSetUp() {
        xCoord = 123;
        yCoord = 123;
    }

    @Test
    public void virusGroupConstructor() {
        VirusGroup vg = new VirusGroup(new Virus(xCoord, yCoord));
        String s = vg.toString();
        assertTrue("<<Constuctor Incorrect>>", s.contains("Viruses:2"));
    }


    @Test
    public void virusGroupOtherConstructor() {
        Virus[] a = new Virus[]{new Virus(0, 0), new Virus(0, 0)};
        VirusGroup vg = new VirusGroup(a);
        String s = vg.toString();
        assertTrue("<<Overloaded Constructor Not Working>>", s.contains("Viruses:2"));
    }


    @Test
    public void virusGroupAddVirus() {
        VirusGroup vg = new VirusGroup(new Virus(xCoord, yCoord));
        vg.addVirus(new Virus(0, 0));
        String s = vg.toString();
        assertTrue("<<Did not add correctly>>", s.contains("Viruses:3"));
    }


    @Test
    public void virusGroupRemove() {
        VirusGroup vg = new VirusGroup(new Virus(xCoord, yCoord));
        vg.remove(0);
        String s = vg.toString();
        assertTrue("<<Did not remove correctly>>", s.contains("Viruses:1"));
    }


    @Test
    public void virusGroupGetVirus() {
        VirusGroup vg = new VirusGroup(new Virus(xCoord, yCoord));
        Virus v = vg.getVirus(0);
        assertTrue("<<Not Equal>>", vg.remove(0).equals(v));
    }


    @Test
    public void virusGroupSetCoord() {
        VirusGroup vg = new VirusGroup(new Virus(xCoord, yCoord));
        vg.getVirus(0).setCoordinate(100, 100);
        assertTrue("<<Coord not set correctly>>", vg.getVirus(0).isMoving());
    }

}
