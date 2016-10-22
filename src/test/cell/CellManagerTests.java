package test.cell;

import main.Cell;
import main.CellManager;
import main.RedCell;
import main.WhiteCell;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by vikranth on 10/21/2016.
 */
public class CellManagerTests {
    /**
     * main.Cell Manager Tests:
     * cellManagerConstructor(): Checks if cellManager is initialized right
     * cellManagerCreateCellsInPositions(): Checks if the cells in main.CellManager is in the right cell
     * cellManagerConvertSick(): Checks if the red cell gets convert to sick
     * cellManagerRemoveCell(): Checks if cell is removed
     * cellManagerToString(): Checks if toString is right
     *
     * @author Melissa
     */
    @Test
    public void cellManagerConstructor() {
        CellManager cm = new CellManager();
        String s = cm.toString();
        assertTrue("<<Invalid main.CellManager Constructor>>",
                s.contains("main.CellManager[") && s.contains("whiteValues: " + CellManager.whiteValues.toString())
                        && s.contains("redValues: " + CellManager.redValues.toString())
                        && s.contains("sickValues: " + CellManager.sickValues.toString()));
    }


    @Test
    public void cellManagerCreateCellsInPositions() {
        CellManager cm = new CellManager();
        cm.createCellsInPositions();

        assertTrue("<<Cells not Created>>",
                CellManager.whiteValues.size() == 3 && CellManager.sickValues.size() == 2
                        && CellManager.redValues.size() == 20);
    }


    @Test
    public void cellManagerConvertSick() {
        CellManager cm = new CellManager();
        CellManager.redValues.clear();
        CellManager.sickValues.clear();
        Cell c = new RedCell(0, 0, 100);
        CellManager.redValues.add(c);
        CellManager.convertCell(c);
        assertTrue("<<main.Cell did not convert to Sick>>",
                CellManager.redValues.size() == 0 && CellManager.sickValues.size() == 1);

    }


    @Test
    public void cellManagerRemoveCell() {
        CellManager cm = new CellManager();
        CellManager.whiteValues.clear();
        CellManager.whiteValues.add(new WhiteCell(0, 0, 200));
        CellManager.removeCell(0);
        assertTrue("<<main.Cell did not get removed>>", CellManager.whiteValues.isEmpty());

    }


    @Test
    public void cellManagerToString() {
        CellManager cm = new CellManager();
        assertNotNull(cm.toString());
    }
}
