package test.cell;

import main.SickCell;
import main.VirusGroup;
import main.VirusGroupManager;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by vikranth on 10/21/2016.
 */
public class SickCellTests {
    /**
     * Sick main.Cell Tests:
     * sickCellConstructor(): Checks if the Sick main.Cell has the right fields
     * sickCellToString(): Checks if the toString is not null
     * sickCellProduceUnit(): Checks if main.Cell produce viruses
     *
     * @author Melissa
     */
    @Test
    public void sickCellConstructor() {
        SickCell wc = new SickCell(0, 0, 100);
        String s = wc.toString();
        assertTrue("<<Invalid main.Cell Constructor>>",
                s.contains("main.SickCell[") && s.contains("x: 0") && s.contains("y: 0") && s.contains("health: " + 100)
                        && s.contains("ticks: " + 0));
    }


    @Test
    public void sickCellToString() {
        SickCell wc = new SickCell(0, 0, 100);
        assertNotNull(wc.toString());
    }


    @Test
    public void sickCellProduceUnit() {
        SickCell wc = new SickCell(0, 0, 100);
        VirusGroupManager vgm = new VirusGroupManager();
        Collection<VirusGroup> al = VirusGroupManager.groups.values();
        int a = 0;
        for (VirusGroup vg : al) {
            a += vg.size();
        }
        int b = 0;

        wc.setTicks(300);
        wc.produceUnit();
        for (VirusGroup vg : al) {
            b += vg.size();
        }

        assertTrue("<<main.AntiVirus Production error from Sick Cells>>", a + 1 == b);

    }

}
