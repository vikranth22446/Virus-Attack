package RegularClasses;

import java.awt.*;

/**
 * The RegularClasses.Virus Class is the main unit that the player controls and uses it to
 * conquer the enemy cells and anti viruses
 * <p>
 * The virus class holds all of its own functions such as updating its location,
 * the coordinate it is headed to, drawing itself and getter methods. extends
 * locatable a interface
 *
 * @author Alex M
 */
public class Virus extends Attacker implements Locatable {


    /**
     * true if the object is moving, false if not
     */
    private boolean move = true;
    
    private boolean attacking = false;

    /**
     * constructor, takes in the initial x and y coordinate, and instantiates
     * variables
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Virus(int x, int y) {
        super(x,
                y
                , Constants.VIRUS_SPEED,
                Constants.VIRUS_HEALTH,
                Constants.VIRUS_ATTACK,
                Constants.VIRUS_ATTACK_RADIUS,
                Constants.VIRUS_WIDTH,
                Constants.VIRUS_HEIGHT

        );
    }

    /**
     * sets new coordinate for the virus to head to
     *
     * @param nx the new x
     * @param ny the new y
     */
    @Override
    public void setCoordinate(int nx, int ny) {
        super.setCoordinate(nx, ny);
        move = true;
    }

    /**
     * when the virus reaches its assigned location this method makes the virus
     * move to prevent them from overlapping
     */
    private void idleMovement() {
        /*
      range of idle movement
     */
        int range = Constants.VIRUS_IDLE_RANGE;

        if (getDistance(getXL(), getYL()) >= range) {
            setVx(getVx() * -1);
            setVy(getVy() * -1);
        }
        setX(getX() + getVx());
        setY(getY() + getVy());
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
    public void checkAttackRadius(Graphics g, int xOffset, int yOffset) {
        
        attacking = false;
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
                }
                wc.setAttacked(false);
                break;
            }

        }
    }
    
    public void update(){
    	if ((getVx() < 0 && getX() < getXL()) || (getVy() < 0 && getY() < getYL()) || (getVy() > 0 && getY() > getYL())
                || (getVx() > 0 && getX() > getXL())) {
            move = false;
        }
        if (!move) {
            idleMovement();
        } else {
            setX(getX() + getVx());
            setY(getY() + getVy());
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
        g.setColor(Color.green);
        g.drawRect((getX() + getWidth() / 4) - xOffset, (getY() + getHeight() / 4) - yOffset,
                getWidth() / 2, getHeight() / 2);
    }

    /**
     * for testing purposes
     * 
     * @see java.lang.Object#toString()
     */
    public String toString(){
    	return "RegularClasses.Virus[x:" + getX() + " y:" + getY() + " speed:" + getSpeed() + " attack:" + getAttack() +
    			" height:" + getHeight() + " width:" + getWidth() + "]"; 
    }

    
    /**
     * testing purposes
     * 
     * @return true if moving, false if not
     */
    public boolean isMoving(){
    	return move;
    }
    
    /**
     * Testing purposes
     * 
     * @return true is attacking, false if not
     */
    public boolean isAttacking(){
    	return attacking;
    }
    
    /**
     * testing purposes
     * 
     * @return the current health
     */
    public int getCuurentHealth(){
    	return getHealth();
    }
}