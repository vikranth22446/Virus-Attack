

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 
 * SickCell is child class (extends) Cell abstract class. The cells are infected and produce viruses.
 * These Cells Help the main player. They start at an initial health of  -100, and when attacked by white cells their
 * health slowly increases, and when it becomes high enough it is removed and a red cell is added when it dies.
 *
 *  @author  Melissa Wei
 *  @version May 28, 2016
 *  @author  Period: 6
 *  @author  Assignment: APCS-Final-Project
 *
 *  @author  Sources: n/a
 */
public class SickCell extends Cell {
    /**
     * The current time used to determine if viruses are produced
     */
    private int ticks;

    /**
     * sets index field to the index, and the generateAt variable to 200. The the super method is called with the
     * rest of the fields to construct the other variables
     *
     * @param x      the x position of the cell
     * @param y      the y position of the cell
     * @param health the initial health of the cell
     */
    public SickCell(int x, int y, int health) {
        super(x, y, health);
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
        super.draw(g, xOffset, yOffset);
        BufferedImage in;
        try {
            in = ImageIO.read(new File("images/pixelsick.png"));
            g.drawImage(in, getX() - xOffset, getY() - yOffset, null);
        } catch (IOException e) {
            System.out.println("Cannot Read Image for sickImage");
        }
    }

    /**
     * Every certain seconds a virus is added at (-20 + getX(), getY())
     */
    public void produceUnit() {
        if (ticks >= Constants.SICK_CELL_VIRUS_GENERATE_RATE) {
            VirusGroupManager.addVirus(Constants.VIRUS_GENERATION_CORRECTION_FACTOR + getX(), getY());
            ticks = 0;
        }
        ticks++;
    }
    
    //Testing purposes
    public int ticks()
    {
        return ticks;
    }
    public void setTicks(int ticks)
    {
        this.ticks = ticks;
    }
    public String toString()
    {
        
        return "SickCell[x: " + getX() + " y: " + getY() + "radius: " + getRadius() + "health: " + getHealth() + "ticks: " +ticks+"]";
        
    }
    

}