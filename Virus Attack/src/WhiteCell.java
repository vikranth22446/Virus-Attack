package v2;

import java.awt.*;

public class WhiteCell extends Cell
{
	private int ticks;
	private int generateAt;

    public WhiteCell(int x, int y, int health,int index)
    {
        super(x, y, health, index);
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
        g.drawOval( getX(), getY(), 50, 50);

    }


    @Override
    public void draw(Canvas canvas) {
        Graphics g = canvas.getGraphics();
        g.setColor(Color.WHITE);
        g.fillOval(getX(), getY(), 50, 50);
        g.setColor( Color.blue );
        g.drawOval( getX(), getY(), 50, 50);

    }

    public  void decrementHealth(int decreaseBy)
    {
        setHealth(getHealth() - decreaseBy);

    }
    public  void increaseHealth(int increaseBy)
    {
        setHealth(getHealth() + increaseBy);

    }

    public boolean canEnemyHurt(int enemyX, int enemyY , int ableRadius){
        double hyp = Math.sqrt(Math.pow(Math.abs(getX()-enemyX),2)
                +Math.pow(Math.abs(getY()-enemyY),2));
        if(ableRadius>hyp){
            return false;
        }
        return true;

    }
    public void updateViruses( VirusGroup group )
    {
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

	
	public void produceUnit() {
		if(ticks >= generateAt){
    		AntiVirusManager.addAnti(getX()+getRadius(), getY()+getRadius());
    		ticks = 0;
    	}
    	ticks++;
	}
    
}