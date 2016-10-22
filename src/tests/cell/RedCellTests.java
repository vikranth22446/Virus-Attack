package tests.cell;

import main.cell.RedCell;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by vikranth on 10/21/2016.
 */
public class RedCellTests {
    /**
     * Red main.cell.Cell Tests:
     * redCellConstructor(): Checks if the constructor is right using toString
     * redCellToString(): Checks if the toString is not null
     *
     * @author Melissa
     */
    @Test
    public void redCellConstructor() {
        RedCell wc = new RedCell(0, 0, 100);
        String s = wc.toString();
        assertTrue("<<Invalid main.cell.Cell Constructor>>",
                s.contains("main.cell.RedCell[") && s.contains("x: 0") && s.contains("y: 0")
                        && s.contains("health: " + 100));
    }


    @Test
    public void redCellToString() {
        RedCell wc = new RedCell(0, 0, 100);
        assertNotNull(wc.toString());
    }
}
