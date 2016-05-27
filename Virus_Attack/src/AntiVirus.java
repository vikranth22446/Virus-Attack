
import java.awt.*;

/**
 * The AntiVirus class which is produced by the WhiteCell.
 */
class AntiVirus extends Attacker {
    /**
     * The current virus to follow
     */
    private Virus currentFollowVirus;
    /**
     * The position of the virus to follow or remove
     */
    private int positionOfVirus;
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
        super(x, y,
                Constants.ANTIVIRUS_SPEED,
                Constants.ANTIVIRUS_HEALTH,
                Constants.ANTIVIRUS_ATTACK,
                Constants.ANTIVIRUS_ATTACK_RADIUS,
                Constants.ANTIVIRUS_WIDTH,
                Constants.ANTIVIRUS_HEIGHT
        );
    }


    /**
     * if the antivirus is following
     * Update the location of the AntiVirus. this is done by looping through
     * all the virus group's viruses and if the virus is within  the acceptable range
     * the virus follows the cell
     * currentVirus is saved, and positionOfViruses that is being followed is saved
     * calls the follow method
     * else
     * reduced the health of the virus. Then moves the antivirus to the currentViruses position.
     * Then calls follow.
     *
     * @param g       the graphics of the window
     * @param xOffset the xOffset of the window. Used to move the window around
     * @param yOffset the yOffset of the window. Used to move the window around
     */
    public void update(Graphics g, int xOffset, int yOffset) {
        if (!follow) {
            for (int i = 0; i < VirusGroupManager.groups.get(VirusGroupManager.currentGroup).size(); i++) {
                Virus v = VirusGroupManager.groups.get(VirusGroupManager.currentGroup).getVirus(i);
                if (getDistance(v) <= getAttackRadius()) {
                    v.reduceHealth(getAttack());
                    setCoordinate(v.getX(), v.getY());
                    currentFollowVirus = v;
                    positionOfVirus = i;
                    follow(g, xOffset, yOffset);
                }
            }

        } else {
            currentFollowVirus.reduceHealth(getAttack());
            setCoordinate(currentFollowVirus.getX(), currentFollowVirus.getY());
            follow(g, xOffset, yOffset);
        }
    }

    /**
     * Moves the antivirus. Then if it really close draw a line. If the virus is dead
     * remove it from the VirusGroupManager. then set follow to false;
     *
     * @param g       the graphics of the window
     * @param xOffset the xOffset of the window. Used to move the window around
     * @param yOffset the yOffset of the window. Used to move the window around
     */
    private void follow(Graphics g, int xOffset, int yOffset) {
        setX(getX() + getVx());
        setY(getY() + getVy());
        follow = true;
        if (getDistance(currentFollowVirus) <= 20) {
            g.setColor(Color.green);
            g.drawLine((getX() + getWidth() / 2) - xOffset,
                    (getY() + getHeight() / 2) - yOffset,
                    (currentFollowVirus.getX() + currentFollowVirus.getWidth() / 2) - xOffset,
                    (currentFollowVirus.getY() + currentFollowVirus.getHeight() / 2) - yOffset);
        }
        if (currentFollowVirus.isDead()) {
            follow = false;
            VirusGroupManager.groups.get(VirusGroupManager.currentGroup).remove(positionOfVirus);
        }
    }

    /**
     * Draws the Antivirus at that position
     *
     * @param g       draws the rectangle of the virus
     * @param xOffset the offset to render the object on the screen according to how
     *                the screen had shifted
     * @param yOffset same as above
     */
    public void draw(Graphics g, int xOffset, int yOffset) {
        g.setColor(Color.blue);
        g.fillRect(getX() - xOffset, getY() - yOffset, getWidth(), getHeight());
    }


}
