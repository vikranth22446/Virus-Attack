

import java.awt.*;


/**
 * Abstract class which defines a cell. All cells can be attacked by Viruses,
 * but different cells have different functions. This class contains very basic
 * methods that pertain to all cells, as well as several abstract methods such
 * as update(), setHealth(), etc.
 * *
 */
public abstract class Cell implements Locatable {
    /**
     * The x-value on coordinate grid
     */
    private int x;

    /**
     * The y-value on coordinate grid
     */
    private int y;

    /**
     * Radius value for cell
     */
    private int radius = 50;

    /**
     * Health value for cell
     */
    private double health;
    /**
     * The initial maximum health of the cell.
     */
    private final double maxHealth;

    /**
     * Initializes the x, and y cordinate. Then initializes the health.
     * Also initializes maxHealth to health
     *
     * @param x      the x coordinate of the cell.
     * @param y      the y coordinate of the cell.
     * @param health the initial health of the cell.
     */
    public Cell(int x, int y, int health) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.maxHealth = health;
    }

    /**
     * Returns the radius of the cell.
     *
     * @return the radius of the cell.
     */
    public int getRadius() {
        return radius;
    }

    /**
     * The initial health of the cell.
     *
     * @return the initial health of the cell
     */
    public double max() {
        return maxHealth;
    }

    /**
     * The x coordinate of the cell
     *
     * @return the x coordinate of the cell
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y coordinate of the cell
     *
     * @return the y coordinate of the cell
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the x coordinate of the cell
     *
     * @param x the x coordinate to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the y coordinate of the cell
     *
     * @param y the new y coordinate to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Sets the health of the cell.
     *
     * @param health the health of the cell to set
     */
    public void setHealth(double health) {
        this.health = health;
    }

    /**
     * Returns the distance between the two locatables
     *
     * @param other the other locatable to find distance between
     * @return the distance between the two locatable
     */
    public double getDistance(Locatable other) {
        double dist = Math.sqrt(
                (((x + radius / 2) - other.getX()) * (x - other.getX()) + (y - other.getY()) * (y - other.getY())));
        return dist;
    }

    /**
     * Draw the cell on the graphics. Also initializes a health bar, and calls
     * healthbar.draw();.
     *
     * @param g       the graphics of the canvas
     * @param xOffset the xOffset of the window. This is used for movement.
     * @param yOffset the yOffset of the window. This is used for movement.
     */
    public void draw(Graphics g, int xOffset, int yOffset){
        HealthBar healthBar = new HealthBar(this);
        healthBar.draw(g, xOffset, yOffset);
    }

    /**
     * Decrement health by a certain health.
     *
     * @param down the value to decrement by
     */
    public void decrementHealth(int down) {
        setHealth(getHealth() - down);
    }

    /**
     * Increments the health by a certain amount
     *
     * @param up the value to increment by.
     */
    public void increaseHealth(int up) {
        setHealth(getHealth() + up);
    }

    /**
     * Returns the health of the cell
     *
     * @return the health field of the cell.
     */
    public double getHealth() {
        return health;
    }

    public abstract void produceUnit();
}