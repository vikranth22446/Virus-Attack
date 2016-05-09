/**
 * Created by Vikranth on 5/4/2016.
 */
public class RedCell extends Cell {

    public RedCell(int x, int y, int health) {
        super(x, y, health);
    }

    @Override
    public void decrementHealth(int decrement) {
        setHealth(getHealth() - decrement);
    }

    @Override
    public void incrementHealth(int increment) {
        setHealth(getHealth() + increment);
    }


}

