package RegularClasses;

/**
 * Contains the basic methods that are shared by both RegularClasses.AntiVirus and RegularClasses.Virus Class
 */
public abstract class Attacker implements Locatable {
    /**
     * The Current x and y location
     */
    private int x, y; // current loc
    /**
     * The speed, attack, attack radius, height and width that is taken from the RegularClasses.Constants class
     */
    private final int speed, attack, attackRadius, height, width;
    /**
     * The health of the basic RegularClasses.Virus
     */
    private int health;
    /**
     * The x and y velocity
     */
    private int vx, vy;
    /**
     * The new x and y Location
     */
    private int xL, yL;

    /**
     * Constructs the fields of the BasicVirus Class
     *
     * @param x            the x position of the BasicVirus
     * @param y            the y position of the BasicVirus
     * @param speed        the speed of the BasicVirus
     * @param health       the health of the BasicVirus
     * @param attack       the attack of the BasicVirus
     * @param attackRadius the attackRadius of the BasicVirus
     * @param width        the width of the BasicVirus
     * @param height       the height of the BasicVirus
     */
    Attacker(int x, int y, int speed, int health, int attack, int attackRadius, int width, int height) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.health = health;
        this.attack = attack;
        this.attackRadius = attackRadius;
        this.height = height;
        this.width = width;
    }

    /**
     * sets new coordinate for the virus to head to
     *
     * @param nx the new x
     * @param ny the new y
     */
    public void setCoordinate(int nx, int ny) {
        xL = nx;
        yL = ny;
        double hyp = Math.sqrt((xL - x) * (xL - x) + (yL - y) * (yL - y));
        double scale = speed / hyp;
        vx = (int) ((xL - x) * scale);
        vy = (int) ((yL - y) * scale);
    }

    /**
     * Reduces the Basic RegularClasses.Virus's health
     *
     * @param reduce the basic RegularClasses.Virus's health
     */
    public void reduceHealth(int reduce) {
        health -= reduce;
    }

    /**
     * Returns the attack Radius
     *
     * @return the attack radius
     */
    public int getAttackRadius() {
        return attackRadius;
    }

    /**
     * Returns the Basic RegularClasses.Virus's x coordinate
     *
     * @return the x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the Basic RegularClasses.Virus's y coordinate
     *
     * @return the y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Returns the Basic RegularClasses.Virus's health
     *
     * @return the basic virus's health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Gets the x Velocity of the Basic RegularClasses.Virus
     *
     * @return the velocity of the Basic RegularClasses.Virus
     */
    public int getVx() {
        return vx;
    }

    /**
     * Returns the y velocity of the Basic RegularClasses.Virus
     *
     * @return the y velocity of the basic RegularClasses.Virus
     */
    public int getVy() {
        return vy;
    }

    /**
     * Returns the attack of the Basic RegularClasses.Virus
     *
     * @return the attack of the Basic virus
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Returns the Height of the Basic RegularClasses.Virus
     *
     * @return the height of the Basic RegularClasses.Virus
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the width of the Basic RegularClasses.Virus
     *
     * @return the width of the Basic RegularClasses.Virus
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the speed of the Basic RegularClasses.Virus
     *
     * @return the speed of the Basic RegularClasses.Virus
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Returns the new X Location
     *
     * @return the new X Location
     */
    public int getXL() {
        return xL;
    }

    /**
     * Returns the y Location
     *
     * @return the y Location
     */
    public int getYL() {
        return yL;
    }

    /**
     * Sets the x location of the Basic RegularClasses.Virus
     *
     * @param x the x location of the Basic RegularClasses.Virus
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the y location of Basic RegularClasses.Virus
     *
     * @param y the y location of Basic RegularClasses.Virus
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Sets the x velocity of Basic RegularClasses.Virus
     *
     * @param vx the x velocity of Basic RegularClasses.Virus
     */
    public void setVx(int vx) {
        this.vx = vx;
    }

    /**
     * Sets the y velocity of the Basic RegularClasses.Virus
     *
     * @param vy the y velocity of the Basic RegularClasses.Virus
     */
    public void setVy(int vy) {
        this.vy = vy;
    }

    /**
     * Returns is health is less than 0
     *
     * @return if health is less than 0 returns dead
     */
    public boolean isDead() {
        return getHealth() <= 0;
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

    /**
     * get distance method for when not using RegularClasses.Locatable objects
     *
     * @param xL x coordinate of other point
     * @param yL y coordinate of other point
     * @return returns the distance between the two objects as a double
     */
    double getDistance(int xL, int yL) {
        double distance = (x - xL) * (x - xL) + (y - yL) * (y - yL);
        distance = Math.sqrt(distance);
        return distance;
    }
}
