package cell;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by vikranth on 10/21/2016.
 */
public class WhiteCellTests {
    /**
     * White main.cell.Cell Tests:
     * whiteCellConstructor(): Checks if to String has the right fields
     * whiteCellToString(): Checks if toString is not Null
     * whiteCellSplit(): Checks if White main.cell.Cell Splits
     * whiteCellProduceUnit(): Checks if white Cells produce AntiViruses
     * whiteCellSetAttacked(): Checks if white cell is attacking
     * whiteCellIsAttacked(): Checks if white main.cell.Cell is attacked
     * whiteCellGetTime(): Checks if the current time is right
     * whiteCellUpdateTime(): Checks if time is updated for whiteCell
     * whiteCellMove(): Checks if white main.cell.Cell moves
     *
     * @author Melissa
     */
    @Test
    public void whiteCellConstructor() {
        WhiteCell wc = new WhiteCell(0, 0, 100);
        String s = wc.toString();
        assertTrue("<<Invalid main.cell.WhiteCell Constructor>>",
                s.contains("main.cell.WhiteCell[") && s.contains("x: 0") && s.contains("y: 0")
                        && s.contains("health: " + 100));
    }


    @Test
    public void whiteCellToString() {
        WhiteCell wc = new WhiteCell(0, 0, 100);
        assertNotNull(wc.toString());
    }


    @Test
    public void whiteCellSplit() {
        ArrayList<Cell> w = new ArrayList<Cell>();
        WhiteCell wc = new WhiteCell(0, 0, 100);
        w.add(wc);
        wc.split(w);
        assertTrue("<<Cells not Spliting>>", w.size() == 2);
    }


    @Test
    public void whiteCellProduceUnit() {
        WhiteCell wc = new WhiteCell(0, 0, 100);
        wc.produceUnit();
        assertNotNull(wc);

    }

    @Test
    public void whiteCellSetAttacked() {
        WhiteCell wc = new WhiteCell(0, 0, 100);
        wc.setAttacked(true);
        assertTrue("<<White main.cell.Cell Attack not set>>", wc.isAttacked());
    }


    @Test
    public void whiteCellIsAttacked() {
        WhiteCell wc = new WhiteCell(0, 0, 100);
        wc.setAttacked(true);
        assertTrue("<<White main.cell.Cell Attack not set>>", wc.isAttacked());
    }


    @Test
    public void whiteCellGetTime() {
        WhiteCell wc = new WhiteCell(0, 0, 100);
        System.out.println(wc.getTime());
        assertTrue("<<White main.cell.Cell Time not set>>", wc.getTime() == 0);
    }


    @Test
    public void whiteCellUpdateTime() {
        WhiteCell wc = new WhiteCell(0, 0, 100);
        wc.updateTime();
        wc.updateTime();
        assertTrue("<<White main.cell.Cell Time not set>>", wc.getTime() == 2);
    }


    @Test
    public void whiteCellMove() {
        WhiteCell wc = new WhiteCell(0, 0, 100);
        wc.setvx(5);
        wc.setvy(6);
        wc.move();
        wc.updateTime();
        assertTrue("<<White main.cell.Cell not Moving>>", wc.vx() == 5 && wc.vy() == 6);
    }
}
