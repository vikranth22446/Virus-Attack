package src;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class WhiteCell extends Cell implements AI {
    private int ticks;
    private int generateAt;
    private int index;
    private int splitTime;
    private int vx, xL;
    private int vy, yL;
    private boolean attacking;
    private int speed;
    private int sightRadius = 300;

    private int attackRadius = 80;
    private int attack;

    public int getIndex() {
        return index;
    }

    public WhiteCell(int x, int y, int health, int index) {
        super(x, y, health);
        this.index = index;
        ticks = 0;
        generateAt = 200;
        vx = 0;
        vy = 0;
        speed = 5;
        attack = 1;
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
    public void draw(Graphics g) {
      //  Graphics g = canvas.getGraphics();
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
    public void setCoord(int nx, int ny) {

        xL = nx;
        yL = ny;
        double hyp = Math.sqrt((xL - getX()) * (xL - getX()) + (yL - getY()) * (yL - getY()));
        double scale = speed / hyp;
        vx = (int) ((xL - getX()) * scale/2.0);
        vy = (int) ((yL - getY()) * scale/2.0);
    }
    public void move() {
       setX(getX() + vx);
       setY(getY() + vy);
//        int moveX = (int) (Math.random() * 2);
//        if (moveX == 1)
//        {
//            setX(getX()+5);
//        }
//        else
//        {
//            setX(getX()-5);
//        }
//        int moveY = (int) (Math.random() * 2);
//        if (moveY == 1)
//        {
//            setY(getY()+5);
//        }
//        else
//        {
//            setY(getY()-5);
//        }
    }



    public void produceUnit() {
        if (ticks >= generateAt) {
            AntiVirusManager.addAnti(getX() + getRadius(), getY() + getRadius());
            ticks = 0;
        }
        ticks++;
    }

    @Override
    public void sendSignal()
    {
        // TODO Auto-generated method stub
        
    }

   // @Override
    public void findVirus(Graphics g)
    {
        // TODO Auto-generated method stub
        boolean attacking = false;
        int closest = Integer.MAX_VALUE;
        Iterator<Entry<Integer, VirusGroup>> it = VirusGroupManager.virusGroupMap().entrySet().iterator();
        while (it.hasNext()) {
            VirusGroup pair = it.next().getValue();
            int add = 0;
            for (int i = 0; i < pair.size(); i+=add)
            {
                Virus v = pair.getVirus( i );
                add = 1;
                if (getDistance(v) <= sightRadius && getDistance(v) < closest)
                {
                    setCoord(v.getX(), v.getY());
                }
                if (getDistance(v) <= attackRadius) 
                {
                    attacking = true;
                    v.reduceHealth(attack);
                    //g = canvas.getGraphics();
                    g.setColor(Color.black);
                    g.drawLine(getX() + getRadius(), getY() + getRadius() / 2,
                            v.getX() + v.getWidth() / 2,
                            v.getY() + v.getHeight() / 2);
                    if (v.isDead()) {
                        pair.remove(i);
                        add = 0;
                    }
                    
                }
                if (attacking) break;
                
            }


          //  it.remove(); // avoids a ConcurrentModificationException
        }
           
        if (attacking)
            return;
        
    }
   

    @Override
    public void callHelp()
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean needHelp()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean canGiveHelp()
    {
        // TODO Auto-generated method stub
        return false;
    }

}