/**
 * Created by Vikranth on 5/4/2016.
 */

public abstract class Cell implements Locatable {
    private int x;
    private int y;
    private int health;

    public Cell(int x, int y, int health) {
        this.x = x;
        this.y = y;
        this.health = health;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
    public abstract void decrementHealth(int decrement);
    public abstract void incrementHealth(int increment);

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }
}

