
import java.awt.*;
import java.util.ArrayList;

public class WhiteCell extends Cell {
    private int ticks;
    private int generateAt;
    private int index;
    private int splitTime;

    public int getIndex() {
        return index;
    }

    public WhiteCell(int x, int y, int health, int index) {
        super(x, y, health);
        this.index = index;
        ticks = 0;
        generateAt = 200;
    }

    @Override
    public void produceValues() {

    }

    public void die(Canvas canvas) {
        Graphics g = canvas.getGraphics();
        g.setColor(World.BCOLOR);
        g.fillOval(getX(), getY(), 50, 50);
        g.drawOval(getX(), getY(), 50, 50);

    }
    public void split(ArrayList<Cell> w)
    {
        int t1 = (int) (getX()+ Math.random()*67);
        int t2 = (int) (getY()+ Math.random()*67);

        w.add(new WhiteCell(t1, t2 , 100, w.size()));
        splitTime = 0;
    }

    @Override
    public void draw(Canvas canvas) {
        Graphics g = canvas.getGraphics();
        g.setColor(Color.WHITE);
        g.fillOval(getX(), getY(), 50, 50);
        g.setColor(Color.blue);
        g.drawOval(getX(), getY(), 50, 50);

    }

    public void decrementHealth(int decreaseBy) {
        setHealth(getHealth() - decreaseBy);

    }

    public void increaseHealth(int increaseBy) {
        setHealth(getHealth() + increaseBy);

    }

    public boolean canEnemyHurt(int enemyX, int enemyY, int ableRadius) {
        double hyp = Math.sqrt(Math.pow(Math.abs(getX() - enemyX), 2)
                + Math.pow(Math.abs(getY() - enemyY), 2));
        if (ableRadius > hyp) {
            return false;
        }
        return true;

    }
    public int getTime()
    {
        return splitTime;
    }

    public void updateTime()
    {
        splitTime++;
       // System.out.println( splitTime );
    }
    public void updateViruses(VirusGroup group) {
//        // TODO Auto-generated method stub
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
    public void move() {
        int moveX = (int) (Math.random() * 2);
        if (moveX == 1)
        {
            setX(getX()+5);
        }
        else
        {
            setX(getX()-5);
        }
        int moveY = (int) (Math.random() * 2);
        if (moveY == 1)
        {
            setY(getY()+5);
        }
        else
        {
            setY(getY()-5);
        }
    }



    public void produceUnit() {
        if (ticks >= generateAt) {
            AntiVirusManager.addAnti(getX() + getRadius(), getY() + getRadius());
            ticks = 0;
        }
        ticks++;
    }

}