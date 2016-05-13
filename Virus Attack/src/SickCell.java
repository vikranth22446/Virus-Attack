package v2;

import javax.swing.*;
import java.awt.*;


public class SickCell extends Cell
{
	
	private int generateAt;
	private int ticks;

    public SickCell( int x, int y, int health, int index )
    {
        super( x, y, health, index );
        
        ticks = 0;
        generateAt = 100;
    }


    @Override
    public void produceValues()
    {

    }


    @Override
    public void draw( Canvas canvas)
    {
        Graphics g = canvas.getGraphics();
        g.setColor( new Color( 5, 26, 41 ) );
        g.fillOval( getX(), getY(), 50, 50 );
    }


    public boolean isCured()
    {
        return getHealth() > 0;
    }


    public void decrementHealth( int decreaseBy )
    {
        setHealth( getHealth() - decreaseBy );

    }


    public void increaseHealth( int increaseBy )
    {
        setHealth( getHealth() + increaseBy );
    }


  


    @Override
    public void updateViruses( VirusGroup group ){
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
		if(ticks >= generateAt){
    		VirusGroupManager.addVirus(getX()+getRadius(), getY()+getRadius());
    		ticks = 0;
    	}
    	ticks++;
	}

}