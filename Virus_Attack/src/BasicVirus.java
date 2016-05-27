/**
 * Created by vikranth on 5/26/2016.
 */
public abstract class BasicVirus implements Locatable{
    /**
     * The Current x and y location
     */
    private int x, y; // current loc
    private int speed, health, attack;
    private int vx, vy;
    private int xL, yL;
    private int attackRadius;
    private int height, width;

    public BasicVirus(int x, int y, int speed, int health, int attack, int attackRadius, int width, int height) {
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

    public int getAttackRadius() {
        return attackRadius;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHealth() {
        return health;
    }
    public void reduceHealth(int reduce) {
        health -= reduce;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getVx() {
        return vx;
    }

    public int getVy() {
        return vy;
    }

    public int getAttack() {
        return attack;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getxL() {
        return xL;
    }

    public int getyL() {
        return yL;
    }

    public void setVx(int vx) {
        this.vx = vx;
    }

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
     * get distance method for when not using Locatable objects
     *
     * @param xL x coord of other point
     * @param yL y coord of other point
     * @return returns the distance between the two objects as a double
     */
    public double getDistance(int xL, int yL) {
        double distance = (x - xL) * (x - xL) + (y - yL) * (y - yL);
        distance = Math.sqrt(distance);
        return distance;
    }
}
