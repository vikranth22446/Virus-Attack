


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * 
 * An implementation of the abstract cell. This cell is neutral to the player. It starts out at a health of a 100.
 * These cells are conquerable nodes by the player.
 *
 *  @author  Melissa Wei
 *  @version May 28, 2016
 *  @author  Period: 6
 *  @author  Assignment: APCS-Final-Project
 *
 *  @author  Sources: n/a
 */
public class RedCell extends Cell {

    /**
     * Constructs the cell by calling the super method and passing the current x, current y, and initial health.
     * Then set the field index to the index variable passed
     *
     * @param x      the current x position
     * @param y      the current y position
     * @param health the initial health of the cell
     */
    public RedCell(int x, int y, int health) {
        super(x, y, health);
    }

    /**
     * Draws the cell by getting a buffered image from the file pixelred.png. Then draws the image at
     * ( getX() - xOffset, getY() - yOffset).
     * Also calls super.draw to draw Health Bar.
     *
     * @param g       the graphics to draw with. Passed by the world class
     * @param xOffset the xOffset of the screen. This allows for screen movement.
     * @param yOffset the yOffset of the screen. This allows for screen movement.
     */
    public void draw(Graphics g, int xOffset, int yOffset) {
        super.draw(g, xOffset, yOffset);
        BufferedImage in;
        try {
            URL url = getClass().getResource("images/cells/pixelred.png");
            File f;
            try {
                f = new File(url.toURI());
            } catch (URISyntaxException e) {
                f = new File(url.getPath());
            }
            in = ImageIO.read(f);
            g.drawImage(in, getX() - xOffset, getY() - yOffset, null);

        } catch (IOException e) {
            System.out.println("Cannot Read Image for redImage");
        }

    }

    @Override
    public void produceUnit() {

    }
    //Testing purposes
    public String toString()
    {
        
        return "RedCell[x: " + getX() + " y: " + getY() + "radius: " + getRadius() + "health: " + getHealth() + "]";
        
    }

}