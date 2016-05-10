public class RedCell extends Cell
{

    public RedCell(int x, int y, int health,int index)
    {
        super(x, y, health,index);
    }

    @Override
    public void produceValues() {

    }

    @Override
    public boolean canEnemeyHurt(int enemyX, int enemyY, int ableRadius) {
        return false;
    }

    public  void decrementHealth(int decreaseBy)
    {
        setHealth(getHealth() - decreaseBy);

    }
    public  void increaseHealth(int increaseBy)
    {
        setHealth(getHealth() + increaseBy);

    }

}
