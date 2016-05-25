
import java.awt.*;

/**
 * The AntivVirus class which is produced by the WhiteCell.
 */
public class AntiVirus implements Locatable {
    /**
     * The Current x and y location
     */
    private int x, y; // current loc
    /**
     * The speed, attack, and health of the cell
     */
    private int speed = 5, attack = 1, health = 30; // stats
    /**
     * The velocity of x and y
     */
    private int vx, vy;
    /**
     * The x and y location that the cell is heading too
     */
    private int xL, yL; // location it is headed to
    /**
     * The width and height of the cell
     */
    private int width = 15, height = 15;
    /**
     * The current virus to follow
     */
    private Virus currentFollowVirus;
    /**
     * The position of the virus to follow or remove
     */
    private int positionOfVirus;
    /**
     * The attack radius of the antivirus
     */
    private int attackRadius = 100;
    /**
     * Whether the values following an antivirus or not.
     */
    private boolean follow = false;


    /**
     * Initializes the x and y position of the antivirus
     *
     * @param x the x position of the antivirus
     * @param y the y position of the antivirus
     */
    public AntiVirus(int x, int y) {
        this.x = x;
        this.y = y;
    }


    /**
     * sets new coordiate for the virus to head to
     *
     * @param nx the new x
     * @param ny the new y
     */
    public void setCoord(int nx, int ny) {
        xL = nx;
        yL = ny;
        double hyp = Math.sqrt((xL - x) * (xL - x) + (yL - y) * (yL - y));
        double scale = speed / hyp;
        vx = (int) ((xL - x) * scale);
        vy = (int) ((yL - y) * scale);
    }


    /**
     * if the antivirus is following
     * Update the location of the AntiVirus. this is done by looping through
     * all the virusgroup's viruses and if the virus is within  the acceptable range
     * the virus follows the cell
     * currentVirus is saved, and positionOfViruses that is being followed is aved
     * calls the follow method
     * else
     * reduced the health of the virus. Then moves the antivirus to the currenViruses position.
     * Then calls follow.
     *
     * @param g
     * @param xOffset
     * @param yOffset
     */
    public void update(Graphics g, int xOffset, int yOffset) {
        if (!follow) {
            for (int i = 0; i < VirusGroupManager.groups.get(VirusGroupManager.currentGroup).size(); i++) {
                Virus v = VirusGroupManager.groups.get(VirusGroupManager.currentGroup).getVirus(i);
                if (getDistance(v) <= attackRadius) {
                    v.reduceHealth(attack);
                    // Graphics g = canvas.getGraphics();
                    setCoord(v.getX(), v.getY());
                    currentFollowVirus = v;
                    positionOfVirus = i;
                    follow(g, xOffset, yOffset);
                }
            }

        } else {
            currentFollowVirus.reduceHealth(attack);
            setCoord(currentFollowVirus.getX(), currentFollowVirus.getY());
            follow(g, xOffset, yOffset);
        }
    }

    /**
     * Moves the antivirus. Then if it really close draw a line. If the virus is dead
     * remove it from the VirusGroupManager. then set follow to false;
     *
     * @param g
     * @param xOffset
     * @param yOffset
     */
    private void follow(Graphics g, int xOffset, int yOffset) {
        x += vx;
        y += vy;
        follow = true;
        if (getDistance(currentFollowVirus) <= 20) {
            g.setColor(Color.green);
            g.drawLine((x + width / 2) - xOffset,
                    (y + height / 2) - yOffset,
                    (currentFollowVirus.getX() + currentFollowVirus.getWidth() / 2) - xOffset,
                    (currentFollowVirus.getY() + currentFollowVirus.getHeight() / 2) - yOffset);
        }
        if (currentFollowVirus.isDead()) {
            follow = false;
            VirusGroupManager.groups.get(VirusGroupManager.currentGroup).remove(positionOfVirus);
        }
    }

    /**
     * Draws the Antivirus at that postion
     *
     * @param g       draws the rectangle of the virus
     * @param xOffset the offset to render the object on the screen according to how
     *                the screen had shifted
     * @param yOffset same as above
     */
    public void draw(Graphics g, int xOffset, int yOffset) {
        g.setColor(Color.blue);
        g.fillRect(x - xOffset, y - yOffset, width, height);
    }


    public void reduceHealth(int reduce) {
        health -= reduce;
    }

    /**
     * Returns is health is less than 0
     *
     * @return if health is less than 0 returns dead
     */
    public boolean isDead() {
        return health <= 0;
    }

    /**
     * Returns the width field
     *
     * @return the width field
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height field
     *
     * @return the height field
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the x postion of the field
     *
     * @return the x position of the field
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y position of the field
     *
     * @return the y position of the field
     */
    public int getY() {
        return y;
    }

    /**
     * Uses the distance formula to find the distance between to locatable
     *
     * @param other the other locatable to find distance between
     * @return the distance between the 2 locatable.
     */
    @Override
    public double getDistance(Locatable other) {
        double distance = (x - other.getX()) * (x - other.getX()) + (y - other.getY()) * (y - other.getY());
        distance = Math.sqrt(distance);
        return distance;
    }
}
