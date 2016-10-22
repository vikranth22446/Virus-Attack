package test.cell;

import main.cell.Cell;
import main.cell.RedCell;
import main.cell.WhiteCell;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * main.cell.Cell Tests:
 * cellToString(): Checks if the toString is not null
 * cellConstructor(): Checks if the to string contains the right fields
 * cellMax(): Checks if the initial health is right
 * cellGetX(): checks if the x value is returned right
 * cellGetY(): checks if the y value is returned  right
 * cellSetX(): checks if the x value is set right
 * cellSetY(): checks if the y value is set right
 * cellGetDistance(): Returns the distance between cells
 * cellDecrementHealth(): Checks if the cell is decremented cell is right
 * cellIncreaseHealth(): Checks if the cell is incremented cell is right
 *
 * @author Melissa
 */
public class CellTests {

    @Test
    public void cellToString() {
        Cell wc = new WhiteCell(0, 0, 100);
        assertNotNull(wc.toString());
    }


    @Test
    public void cellConstructor() {
        Cell wc = new RedCell(0, 0, 100);
        String s = wc.toString();
        assertTrue("<<Invalid main.cell.Cell Constructor>>",
                s.contains("main.cell.RedCell[") && s.contains("x: 0") && s.contains("y: 0")
                        && s.contains("health: " + 100));
    }


    @Test
    public void cellMax() {
        Cell wc = new RedCell(0, 0, 100);
        assertTrue("<<No Max Variable Stored>>", wc.max() == 100);

    }


    @Test
    public void cellGetX() {
        Cell wc = new RedCell(0, 0, 100);
        assertTrue("<<No X Variable Stored>>", wc.getX() == 0);
    }


    @Test
    public void cellGetY() {
        Cell wc = new RedCell(0, 0, 100);
        assertTrue("<<No Y Variable Stored>>", wc.getY() == 0);
    }


    @Test
    public void cellSetX() {
        Cell wc = new RedCell(0, 0, 100);
        wc.setX(10);
        assertTrue("<<No X Variable Changed>>", wc.getX() == 10);
    }


    @Test
    public void cellSetY() {
        Cell wc = new RedCell(0, 0, 100);
        wc.setY(10);
        assertTrue("<<No Y Variable Changed>>", wc.getY() == 10);
    }


    @Test
    public void cellGetDistance() {
        Cell wc = new RedCell(0, 0, 100);
        Cell wc1 = new RedCell(24, 7, 100);
        assertTrue("<<Distance incorrect>>", wc.getDistance(wc1) == 5);

    }


    @Test
    public void cellDecrementHealth() {
        Cell wc = new RedCell(0, 0, 100);
        wc.decrementHealth(20);
        assertTrue("<<Health decremented incorrectly>>", wc.getHealth() == 80);

    }


    @Test
    public void cellIncreaseHealth() {
        Cell wc = new RedCell(0, 0, 100);
        wc.increaseHealth(20);
        assertTrue("<<Health increased incorrectly>>", wc.getHealth() == 120);
    }
}
