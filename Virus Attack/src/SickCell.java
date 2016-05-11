package v2;

import javax.swing.*;
import java.awt.*;


public class SickCell extends Cell
{

    public SickCell( int x, int y, int health, int index )
    {
        super( x, y, health, index );
    }


    @Override
    public void produceValues()
    {

    }


    @Override
    public void draw( Canvas canvas, int position )
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

}