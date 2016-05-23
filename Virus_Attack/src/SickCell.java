


import java.awt.*;


public class SickCell extends Cell {

    private int generateAt;
    private int ticks;
    private int index;

    public int getIndex() {
        return index;
    }

    public SickCell(int x, int y, int health, int index) {
        super(x, y, health);
        this.index = index;
        ticks = 0;
        generateAt = 500;
    }


    @Override
    public void produceValues() {

    }


    @Override
    public void draw(Graphics g,int xOffset,int yOffset) {
    //    Graphics g = canvas.getGraphics();
        g.setColor(new Color(5, 26, 41));
        g.fillOval(getX() - xOffset, getY() - yOffset, 50, 50);
        HealthBar healthBar = new HealthBar(this);
        healthBar.draw(g,this, xOffset, yOffset, (int)max());
//        HealthBar healthBar = new HealthBar(this);
//        healthBar.draw(canvas);
    }


    public boolean isCured() {
        return getHealth() > 0;
    }


    public void decrementHealth(int decreaseBy) {
        setHealth(getHealth() - decreaseBy);

    }


    public void increaseHealth(int increaseBy) {
        setHealth(getHealth() + increaseBy);
    }


    @Override
    public void updateViruses(VirusGroup group) {
//
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

    public void produceUnit() {
        if (ticks >= generateAt) {
            VirusGroupManager.addVirus(-20+getX(), getY());
            ticks = 0;
        }
        ticks++;
    }

}