package helper;

import main.helper.Locatable;
import main.virus.Virus;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by vikranth on 10/21/2016.
 */
public class LocatableTests {
    /**
     * main.helper.Locatable Tests:
     * locatableGetX(): Returns the x coordinate of the virus
     * locatableGetY(): Returns the y coordinate of the virus
     * locatableDistance(): Returns the distance between the two viruses
     *
     * @author Vikranth Srivatsa
     */
    @Test
    public void locatableGetX() {
        Locatable locatable = new Virus(30, 30);
        boolean test = true;
        try {
            locatable.getX();
        } catch (Exception e) {
            test = false;
        }
        assertTrue("<<main.helper.Locatable is not working>>", test);
    }

    @Test
    public void locatableGetY() {
        Locatable locatable = new Virus(30, 30);
        boolean test = true;
        try {
            locatable.getX();
        } catch (Exception e) {
            test = false;
        }
        assertTrue("<<main.helper.Locatable is not working>>", test);
    }

    @Test
    public void locatableDistance() {
        Locatable locatable = new Virus(30, 30);
        boolean test = true;
        try {
            locatable.getDistance(new Virus(30, 39));
        } catch (Exception e) {
            test = false;
        }
        assertTrue("<<main.helper.Locatable is not working>>", test);
    }
}
