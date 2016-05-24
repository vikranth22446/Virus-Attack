/**
 * This is a helper class used to create positions of the redCell.
 */
public class Point implements Locatable {
    /**
     * The x position of the point
     */
    private int x;
    /**
     * The y position of the point
     */
    private int y;

    /**
     * Constructs the x and y coordinate
     *
     * @param x the x position of the point
     * @param y the y position of the point
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x position of the cell.
     *
     * @return the x positon of the cell.
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * Returns the y position of the cell
     *
     * @return the y position of the cell.
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * The distance between the locatable and the point using the distance method
     *
     * @param other the other locatable to find the distance between
     * @return the distance between the values
     */
    @Override
    public double getDistance(Locatable other) {
        return Math.sqrt(Math.pow((Math.abs(getX() - other.getX())), 2) + Math.pow((Math.abs(getY() - other.getY())), 2));
    }
}
