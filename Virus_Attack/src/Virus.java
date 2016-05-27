
import java.awt.*;

/**
 * The Virus Class is the main unit that the player controls and uses it to
 * conquer the enemy cells and anti viruses
 * <p>
 * The virus class holds all of its own functions such as updating its location,
 * the coordinate it is headed to, drawing itself and getter methods. extends
 * locatable a interface
 * 
 * @author Alex M
 */
public class Virus extends BasicVirus implements Locatable {


    /**
     * the group number
     */
    private int num;

    /**
     * true if the object is moving, false if not
     */
    private boolean move = true;

    /**
     * range of idle movment
     */
    private int range = 50;

    /**
     * constructor, takes in the intial x and y coordinate, and instantiates
     * variables
     *
     * @param x x coord
     * @param y y coord
     */
    public Virus(int x, int y) {
        super(x,
                y
                , APCS_CONSTANTS.VIRUS_SPEED,
                APCS_CONSTANTS.VIRUS_HEALTH,
                APCS_CONSTANTS.VIRUS_ATTACK,
                APCS_CONSTANTS.ATTACK_RADIUS,
                APCS_CONSTANTS.VIRUS_WIDTH,
                APCS_CONSTANTS.VIRUS_HEIGHT

        );
    }

    /**
     * sets new coordiate for the virus to head to
     *
     * @param nx the new x
     * @param ny the new y
     */
    @Override
    public void setCoord(int nx, int ny) {
        super.setCoord(nx,ny);
        move = true;
    }

    /**
     * when the virus reaches its assigned location this method makes the virus
     * move to prevent them from overlapping
     */
    public void idleMovement() {
        if (getDistance(getxL(), getyL()) >= range) {
            setVx(getVx()*-1);
            setVy(getVy()*-1);
        }
        setX(getX()+getVx());
        setY(getY()+getVy());
    }

    /**
     * moves the virus, checks if it has reached the designated location, and if
     * there is anything to attack in its attack range
     *
     * @param g       draws lines to show attack using graphics
     * @param xOffset the offset to render the object on the screen according to how
     *                the screen had shifted
     * @param yOffset same as above
     */
    public void update(Graphics g, int xOffset, int yOffset) {
        if ((getVx() < 0 && getX() < getxL()) || (getVy() < 0 && getY() < getyL()) || (getVy() > 0 && getY() > getyL())
                || (getVx() > 0 && getX() > getxL())) {
            move = false;
        }
        if (!move) {
            idleMovement();
        } else {
            setX(getX()+getVx());
            setY(getY()+getVy());
        }
        boolean attacking = false;
        for (int i = 0; i < AntiVirusManager.anti.size(); i++) {
            AntiVirus av = AntiVirusManager.anti.get(i);
            if (getDistance(av) <= getAttackRadius()) {
                av.reduceHealth(getAttack());
                // g = canvas.getGraphics();
                g.setColor(Color.black);
                g.drawLine(getX() + getWidth() / 2 - xOffset, getY() + getHeight() / 2 - yOffset,
                        av.getX() + av.getWidth() / 2 - xOffset,
                        av.getY() + av.getHeight() / 2 - yOffset);
                if (av.isDead()) {
                    AntiVirusManager.anti.remove(av);
                }
                attacking = true;
                break;
            }

        }

        if (attacking)
            return;

        for (int i = 0; i < CellManager.redValues.size(); i++) {
            Cell c = CellManager.redValues.get(i);
            if (getDistance(c) <= getAttackRadius() && !(c instanceof SickCell)) {
                c.decrementHealth(getAttack());
                // g = canvas.getGraphics();
                g.setColor(Color.black);
                g.drawLine(getX() + getWidth() / 2 - xOffset, getY() + getHeight() / 2 - yOffset,
                        c.getX() + c.getRadius() / 2 - xOffset,
                        c.getY() + c.getRadius() / 2 - yOffset);
                if (c.getHealth() <= 0) {
                    CellManager.convertCell(c);
                }
                attacking = true;

                break;
            }

        }

        if (attacking)
            return;

        for (int i = 0; i < CellManager.whiteValues.size(); i++) {
            Cell c = CellManager.whiteValues.get(i);
            if (getDistance(c) <= getAttackRadius()) {
                c.decrementHealth(getAttack());
                // g = canvas.getGraphics();
                g.setColor(Color.black);
                g.drawLine(getX() + getWidth() / 2 - xOffset, getY() + getHeight() / 2 - yOffset,
                        c.getX() + c.getRadius() / 2 - xOffset,
                        c.getY() + c.getRadius() / 2 - yOffset);
                WhiteCell wc = (WhiteCell) c;
                wc.setAttacked(true);
                if (c.getHealth() <= 0) {
                    CellManager.removeCell(i);
                }wc.setAttacked(false);
                break;
            }

        }
    }

    /**
     * draws the virus on the screen
     *
     * @param g       draws in using this graphics
     * @param xOffset the offset to render the object on the screen according to how
     *                the screen had shifted
     * @param yOffset same as above
     */
    public void draw(Graphics g, int xOffset, int yOffset) {
        g.setColor(new Color(122, 122, 0));
        g.fillRect(getX() - xOffset, getY() - yOffset, getWidth(), getHeight());
        g.setColor(World.BCOLOR);
        g.drawRect((getX() + getWidth() / 4) - xOffset, (getY() + getHeight() / 4) - yOffset,
                getWidth() / 2, getHeight() / 2);
    }


}