
abstract class BasicVirus implements Locatable{
    /**
     * The Current x and y location
     */
    private int x, y; // current loc
    private final int speed;
    private int health;
    private final int attack;
    private int vx, vy;
    private int xL, yL;
    private final int attackRadius;
    private final int height;
    private final int width;

    BasicVirus(int x, int y, int speed, int health, int attack, int attackRadius, int width, int height) {
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
    void setCoordinate(int nx, int ny) {
        xL = nx;
        yL = ny;
        double hyp = Math.sqrt((xL - x) * (xL - x) + (yL - y) * (yL - y));
        double scale = speed / hyp;
        vx = (int) ((xL - x) * scale);
        vy = (int) ((yL - y) * scale);
    }

    int getAttackRadius() {
        return attackRadius;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private int getHealth() {
        return health;
    }
    void reduceHealth(int reduce) {
        health -= reduce;
    }

    void setX(int x) {
        this.x = x;
    }

    void setY(int y) {
        this.y = y;
    }

    int getVx() {
        return vx;
    }

    int getVy() {
        return vy;
    }

    int getAttack() {
        return attack;
    }

    int getHeight() {
        return height;
    }

    int getWidth() {
        return width;
    }

    int getXL() {
        return xL;
    }

    int getYL() {
        return yL;
    }

    void setVx(int vx) {
        this.vx = vx;
    }

    void setVy(int vy) {
        this.vy = vy;
    }

    /**
     * Returns is health is less than 0
     *
     * @return if health is less than 0 returns dead
     */
    boolean isDead() {
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
