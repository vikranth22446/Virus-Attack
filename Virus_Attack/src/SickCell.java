


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * An Implementation of the Cell abstract class. The cells are infected and produce viruses.
 * These Cells Help the main player. They start at an inital health of  -100, and when attacked by white cells their
 * health slowly reduces.
 */
public class SickCell extends Cell {
   // private static String stuff = "C:\\Users\\viks\\Documents\\APCS-Final-Project\\Virus_Attack";
    /**
     * The rate at which virus are produced
     */
    private int generateAt;
    /**
     * The current time used to determine if viruses are produced
     */
    private int ticks;
    /**
     * The position of the cell in the CellManager
     */
    private int index;

    /**
     * sets index field to the index, and the generateAt variable to 200. The the super method is called with the
     * rest of the fields to construct the other variables
     *
     * @param x      the x position of the cell
     * @param y      the y position of the cell
     * @param health the initial health of the cell
     * @param index  the index of the cell in the cellManager
     */
    public SickCell(int x, int y, int health, int index) {
        super(x, y, health);
        this.index = index;
        ticks = 0;
        generateAt = 200;
    }

    /**
     * Reads a Buffer image from the file pixelsick.png. Then draws the image at (getX() - xOffset, getY() - yOffset).
     * Then construct a Health Bar. The draw a health bar at (g,xOffset,yOffset)
     *
     * @param g       The Graphics of the current canvas
     * @param xOffset the xOffset of the graphics. This allows for moving of screen
     * @param yOffset the yOffset of the graphics. This allows for moving of screen
     */
    @Override
    public void draw(Graphics g, int xOffset, int yOffset) {
        g.setColor(new Color(255, 0, 0));
        BufferedImage in;
        try {
      //      in = ImageIO.read(new File(stuff + "\\pixelsick.png"));
              in = ImageIO.read(new File("pixelsick.png"));
            g.drawImage(in, getX() - xOffset, getY() - yOffset, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HealthBar healthBar = new HealthBar(this);
        healthBar.draw(g, xOffset, yOffset);
    }

    /**
     * Returns true if the health is greater than 0
     *
     * @return true if the health is greater than 0
     */
    public boolean isCured() {
        return getHealth() > 0;
    }

    /**
     * Decrements the health by a certain amount passed. This is never used but is needed because
     * it is an abstract method used in RedCell and WhiteCell
     *
     * @param decreaseBy the amount to decrease by
     */
    public void decrementHealth(int decreaseBy) {
        setHealth(getHealth() - decreaseBy);

    }

    /**
     * Calls the setHealth method and increases it by a certain amount. This is used when white cell attacks
     *
     * @param increaseBy the health to increase by
     */
    public void increaseHealth(int increaseBy) {
        setHealth(getHealth() + increaseBy);
    }

    /**
     * Checks if the number of ticks has reached the generateAt value. If it is then the ticks variable is set to 0, and
     * a virus is created at(-20+getX(), getY())
     */
    public void produceUnit() {
        if (ticks >= generateAt) {
            VirusGroupManager.addVirus(-20 + getX(), getY());
            ticks = 0;
        }

        ticks++;
    }

}