package virus;

import main.virus.VirusGroupManager;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by vikranth on 10/21/2016.
 */
public class VirusGroupManagerTests {
    /**
     * main.virus.Virus Group Manager Tests:
     * virusGroupManagerConstructor(): Checks if groups are set correctly
     * virusGroupManagerHasKey(): Checks if key = 1 exsits
     * virusGroupManagerAddVirus(): Checks if there 3 viruses
     * virusGroupManagerRemove(): Checks if 1 virus will remain after remove()
     * virusGroupManagerSplit(): Checks if the groups will split right
     *
     * @author Alex M
     */

    @Test
    public void virusGroupManagerConstructor() {
        VirusGroupManager vgm = new VirusGroupManager();
        String s = vgm.toString();
        assertTrue("<<Incorrect Constructor>>", s.contains("numGroups:1") && s.contains("current group:1"));

    }

    @Test
    public void virusGroupManagerHasKey() {
        VirusGroupManager vgm = new VirusGroupManager();
        assertTrue("<<Doesn't Have Key>>", vgm.hasKey(1));
    }

    @Test
    public void virusGroupManagerAddVirus() {
        VirusGroupManager vgm = new VirusGroupManager();
        vgm.addVirus(0, 0);
        assertTrue("<<main.virus.Virus Not Added>>", vgm.groups.get(1).size() == 3);
    }

    @Test
    public void virusGroupManagerRemove() {
        VirusGroupManager vgm = new VirusGroupManager();
        vgm.remove(vgm.groups.get(1).getVirus(0));
        assertTrue("<<main.virus.Virus not removed>>", vgm.groups.get(1).size() == 1);
    }

    @Test
    public void virusGroupManagerSplit() {
        VirusGroupManager vgm = new VirusGroupManager();
        vgm.split();
        assertTrue("<<Did not Split>>", vgm.hasKey(1) && vgm.hasKey(2));
    }
}
