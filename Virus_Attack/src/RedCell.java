

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
     * Constructs the cell by calling the super method and passing the current x, current y, and initial health.
     * Then set the field index to the index varaible passed
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
     * Also calls super.draw to draw healthbar.
     *
     * @param g       the grapghics to draw with. Passed by the world class
     * @param xOffset the xOffset of the screen. This allows for screen movement.
     * @param yOffset the yOffset of the screen. This allows for screen movement.
     */
    public void draw(Graphics g, int xOffset, int yOffset) {
        super.draw(g, xOffset, yOffset);
        g.setColor(new Color(255, 0, 0));
        BufferedImage in;
        try {
            in = ImageIO.read(new File("pixelred.png"));
            g.drawImage(in, getX() - xOffset, getY() - yOffset, null);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void produceUnit() {

    }

}