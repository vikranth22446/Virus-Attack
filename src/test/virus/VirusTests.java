package test.virus;

import main.Virus;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * main.Virus test.JUnit Test Methods:
 * virusSetUp(): sets a coordinate to 123 and 123
 * virusConstructor(): checks if the x and y coordinates are in the right spot for the toString
 * virusToString(): checks if toString is not null
 * virusMoving(): Checks if virus is moving
 * virusUpdate(): Checks if the virus has moved
 * virusReduceHealth(): Checks if the virus has reduced health
 * virusIdleMovement(): checks if the virus goes through idle movement routine
 *
 * @author Alex M
 */
public class VirusTests {
    private int xCoord;

    private int yCoord;

    @Before
    public void virusSetUp() {
        xCoord = 123;
        yCoord = 123;
    }

    @Test
    public void virusConstructor() {
        Virus v = new Virus(xCoord, yCoord);
        String s = v.toString();

        assertTrue("<<Invalid main.Virus Constructor>>",
                s.contains("main.Virus[") && s.contains("x:123") && s.contains("y:123"));
    }


    @Test
    public void virusToString() {
        Virus v = new Virus(xCoord, yCoord);
        assertNotNull(v.toString());
    }


    @Test
    public void virusMoving() {
        Virus v = new Virus(xCoord, yCoord);
        v.setCoordinate(xCoord + 100, yCoord + 100);
        assertTrue("<<Coordinate not set properly>>", v.isMoving());
    }


    @Test
    public void virusUpdate() {
        Virus v = new Virus(xCoord, yCoord);
        v.setCoordinate(xCoord + 100, yCoord + 100);
        v.update();
        assertTrue("<<Did not Update>>", (v.getX() != xCoord) && (v.getY() != yCoord));
    }


    @Test
    public void virusReduceHealth() {
        Virus v = new Virus(xCoord, yCoord);
        int initialHp = v.getCuurentHealth();
        v.reduceHealth(10);
        assertTrue("<<Health not reduced>>", (initialHp - 10) == v.getCuurentHealth());
    }


    @Test
    public void virusIdleMovement() {
        Virus v = new Virus(xCoord, yCoord);
        v.setCoordinate(xCoord, yCoord);
        v.update();
        assertTrue("<<Idle not functioning>>", v.isMoving());
    }
}
