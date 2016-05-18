import java.awt.*;

public class RedCell extends Cell {
    private int index;

    public int getIndex() {
        return index;
    }

    public RedCell(int x, int y, int health, int index) {
        super(x, y, health);
        this.index = index;
    }

    public void decrementHealth(int decreaseBy) {
        setHealth(getHealth() - decreaseBy);

    }

    public void increaseHealth(int increaseBy) {
        setHealth(getHealth() + increaseBy);

    }

    @Override
    public void produceValues() {
        // TODO Auto-generated method stub

    }

    public void draw(Graphics g) {
      //  Graphics g = canvas.getGraphics();
        g.setColor(new Color(255, 0, 0));
        g.fillOval(getX(), getY(), 50, 50);
    }

    public void updateViruses(VirusGroup group) {
//        for ( int i = 0; i < group.size(); i++ )
//        {
//            if (getDistance( group.getVirus( i ) ) <= group.getVirus(i).getAttackRadius())
//            {
//                setHealth( getHealth() - 1 );
//                if (!getViruses().contains(group.getVirus( i )))
//                {
//                    addVirus(group.getVirus( i ));
//                }
//
//            }
//        }

    }

    @Override
    public void produceUnit() {
        // TODO Auto-generated method stub

    }
}