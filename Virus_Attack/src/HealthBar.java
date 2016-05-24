

import java.awt.*;

/**
 * This is a health bar used by all cells to show the health of the cell.
 * A green color is used to show health, while red is used to the show the rest.
 * In the case of the Sick cell( which has a health of -100). A red bar
 * is drawn, and the green bar for the rest of the bar
 */
public class HealthBar {
    /**
     * The cell which the health bar is drawn on top of.
     */
    private Cell cell;

    /**
     * Initializes the cell with the cell passed
     *
     * @param cell the cell that needs a health bar.
     */
    public HealthBar(Cell cell) {
        this.cell = cell;
    }

    /**
     * This draws the health bar on top of the cell.
     * create 2 variables: currentColor, and oppositeColor
     * If sickcell :
     * currentColor = Color.red;
     * oppositeColor = Color.green;
     * else
     * currentColor = Color.green;
     * oppositeColor = Color.red;
     * <p>
     * Then set color to currentColor.
     * Draw the rectangle at  (cell.getX() - 25 - xOffset, cell.getY() - 20 - yOffset)
     * With width :  (int) (Math.abs(cell.getHealth() / cell.max()) * 100)
     * With height: 10
     * The call draw OtherHalf. Passing the cellHealth as 100-width.
     *
     * @param xOffset the xOffset of the window. This allows for window movement
     * @param yOffset the yOffset of the window. This allows for window movement
     */
    public void draw(Graphics graph, int xOffset, int yOffset) {
        Color currentColor, oppositeColor;
        if (cell instanceof SickCell) {
            currentColor = Color.red;
            oppositeColor = Color.green;
        } else {
            currentColor = Color.green;
            oppositeColor = Color.red;
        }
        graph.setColor(currentColor);
        int width = (int) (Math.abs(cell.getHealth() / cell.max()) * 100);
        graph.fillRect(cell.getX() - 25 - xOffset, cell.getY() - 20 - yOffset, width, 10);
        drawOtherHalf(graph, xOffset, yOffset, width, oppositeColor);
    }

    /**
     * Sets the color of the graphics to the passed color. Creates the bar at
     * ((cell.getX() - 25) + cellHealth - xOffset, cell.getY() - 20 - yOffset))
     * The create variable remining where you subtract 100-cellHealth;
     * This represents the rest of the bar
     * The create a bar that has width remaining and height 10.
     *
     * @param g          the graphics of the plane
     * @param xOffset    the xoffset of the window.This allows for window movement.
     * @param yOffset    the yoffset of the window.This allows for window movement.
     * @param cellHealth the width of first bar.
     * @param color      the opposite color of the color drawn.
     */
    public void drawOtherHalf(Graphics g, int xOffset, int yOffset, int cellHealth, Color color) {
        int remaining = 100 - cellHealth;
        g.setColor(color);
        g.fillRect((cell.getX() - 25) + cellHealth - xOffset, cell.getY() - 20 - yOffset, remaining, 10);
    }


}