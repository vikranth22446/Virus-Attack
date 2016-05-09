
public class WhiteCell extends Cell{

    public WhiteCell(int x, int y, int health) {
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
