

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * An implementation of the abstract cell. This cell is neutral to the player. It starts out at a health of a 100.
 * These cells are conquerable nodes by the player.
 */
public class RedCell extends Cell {
    /**
     * The index of the cell in cell Manager
     */
    private int index;
    private static String stuff = "C:\\Users\\viks\\Documents\\APCS-Final-Project\\Virus_Attack";

    /**
     * Constructs the cell by calling the super method and passing the current x, current y, and initial health.
     * Then set the field index to the index varaible passed
     * @param x  the current x position
     * @param y  the current y position
     * @param health the initial health of the cell
     * @param index the index of the current cell
     */
    public RedCell(int x, int y, int health, int index) {
        super(x, y, health);
        this.index = index;
    }

    /**
     * Decremenents the current health by the value passed. Used when viruses attack the cell
     * @param decreaseBy the value to decrease by
     */
    public void decrementHealth(int decreaseBy) {
        setHealth(getHealth() - decreaseBy);

    }

    /**
     * Increase the current health by the value passed. Not really used by has to be overrided because in base class
     * @param increaseBy the value to increase by
     */
    public void increaseHealth(int increaseBy) {
        setHealth(getHealth() + increaseBy);

    }

    /**
     * Draws the cell by getting a buffered image from the file pixelred.png. Then draws the image at
     *    ( getX() - xOffset, getY() - yOffset).
     * @param g the grapghics to draw with. Passed by the world class
     * @param xOffset the xOffset of the screen. This allows for screen movement.
     * @param yOffset the yOffset of the screen. This allows for screen movement.
     */
    public void draw(Graphics g, int xOffset, int yOffset) {
        g.setColor(new Color(255, 0, 0));
        BufferedImage in;
        try {
            in = ImageIO.read(new File(stuff + "\\pixelred.png"));
      //             in = ImageIO.read(new File("pixelred.png"));
            g.drawImage(in, getX() - xOffset, getY() - yOffset, null);

        } catch (IOException e) {
            e.printStackTrace();
        }


        HealthBar healthBar = new HealthBar(this);
        healthBar.draw(g, xOffset, yOffset);
    }

    /**
     * Does nothing. This has to be overided because the it is an abstract method in the cell class.
     */
    @Override
    public void produceUnit() {
    }
}