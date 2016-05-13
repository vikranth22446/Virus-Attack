
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

        g.setColor( new Color(0, 0, 0 ) );

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
    public boolean canEnemyHurt( int enemyX, int enemyY, int ableRadius )
    {
        double hyp = Math
                .sqrt( Math.pow( Math.abs( getX() - enemyX ), 2 ) + Math.pow( Math.abs( getY() - enemyY ), 2 ) );
        if ( ableRadius > hyp )
        {
            return false;
        }
        return true;
    }


    @Override
    public void updateViruses( VirusGroup group )
    {
        // TODO Auto-generated method stub
        for ( int i = 0; i < group.size(); i++ )
        {
            if (getDistance( group.getVirus( i ) ) <= group.getVirus(i).getAttackRadius())
            {
                setHealth( getHealth() - 1 );
                if (!getViruses().contains(group.getVirus( i )))
                {
                    addVirus(group.getVirus( i ));
                }

            }
        }

    }

}