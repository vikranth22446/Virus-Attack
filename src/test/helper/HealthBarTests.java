package test.helper;

import main.HealthBar;
import main.WhiteCell;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by vikranth on 10/21/2016.
 */
public class HealthBarTests {
    /**
     * Health Bar tests:
     * healthBarConctructor(): Checks if the health bar has the right cell
     *
     * @author Vikranth Srivatsa
     */
    @Test
    public void healthBarConctructor() {
        HealthBar healthBar = new HealthBar(new WhiteCell(30, 30, 30));
        System.out.println(healthBar.toString());
        String test = "main.HealthBar{cell=main.WhiteCell[x: 30 y: 30radius: 50health: 30.0speed: 5attack: 1]}";
        assertTrue("<<The main.Cell is contructed wrong>>", test.equalsIgnoreCase(healthBar.toString()));
    }
}
