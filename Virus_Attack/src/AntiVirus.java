package Virus_Attack.src;
import java.awt.*;

/**
 *
 */


public class AntiVirus implements Locatable
{

    private int x, y; // current loc

    private int speed, attack, health; // stats

    private int vx, vy;

    private int xL, yL; // location it is headed to

    private boolean move;

    private int width, height;

    private int num;

    private int attackRadius;


    // construct a virus
    public AntiVirus( int x, int y )
    {

        this.x = x;
        this.y = y;
        speed = 5;
        attack = 1;// 2
        health = 5;// 100

        width = 15;
        height = 15;
        move = true;

        attackRadius = 20;
    }


    // set a new coordinate for the virus to head to
    public void setCoord( int nx, int ny )
    {

        xL = nx;
        yL = ny;
        double hyp = Math.sqrt( ( xL - x ) * ( xL - x ) + ( yL - y ) * ( yL - y ) );
        double scale = speed / hyp;
        vx = (int)( ( xL - x ) * scale );
        vy = (int)( ( yL - y ) * scale );
    }


    public void stop()
    {
        move = false;
    }


    public void start()
    {
        move = true;
    }


    // update the status of the virus

    public void update( Graphics g, int xOffset, int yOffset )
    {
        if ( !move )
            return;
        x += vx;
        y += vy;

        boolean attacking = false;
        for ( int i = 0; i < VirusGroupManager.groups.get( VirusGroupManager.currentGroup ).size(); i++ )
        {
            Virus v = VirusGroupManager.groups.get( VirusGroupManager.currentGroup ).getVirus( i );
            if ( getDistance( v ) <= attackRadius )
            {
                v.reduceHealth( attack );
                // Graphics g = canvas.getGraphics();
                g.setColor( Color.green );
                g.drawLine( ( x + width / 2 ) - xOffset,
                    ( y + height / 2 ) - yOffset,
                    ( v.getX() + v.getWidth() / 2 ) - xOffset,
                    ( v.getY() + v.getHeight() / 2 ) - yOffset );
                if ( v.isDead() )
                    VirusGroupManager.groups.get( VirusGroupManager.currentGroup ).remove( i );
                attacking = true;
                break;
            }
        }
        if ( attacking )
            return;

        for ( int i = 0; i < CellManager.sickValues.size(); i++ )
        {
            Cell c = CellManager.sickValues.get( i );
          //  System.out.println( c.getHealth() + "hhi" );

            if ( getDistance( c ) <= attackRadius )
            {
                c.increaseHealth( attack );
                g.setColor( Color.green );
                g.drawLine( ( x + width / 2 ) - xOffset,
                    ( y + height / 2 ) - yOffset,
                    ( c.getX() + c.getRadius() / 2 ) - xOffset,
                    ( c.getY() + c.getRadius() / 2 ) - yOffset );
             //   System.out.println( c.getHealth() );
                if ( c.getHealth() >= 0 )
                    CellManager.convertSick( c );
                break;

            }
        }

    }


    public void draw( Graphics g, int xOffset, int yOffset )
    {
        // Graphics g = canvas.getGraphics();
        g.setColor( Color.blue );
        g.fillRect( x - xOffset, y - yOffset, width, height );
    }


    public void reduceHealth( int reduce )
    {
        health -= reduce;
    }


    public boolean isDead()
    {
        // System.out.println( health <= 0 );
        return health <= 0;
    }


    public int getWidth()
    {
        return width;
    }


    public int getHeight()
    {
        return height;
    }


    public int getX()
    {
        return x;
    }


    public int getY()
    {
        return y;
    }


    @Override
    public double getDistance( Locatable other )
    {
        double distance = ( x - other.getX() ) * ( x - other.getX() ) + ( y - other.getY() ) * ( y - other.getY() );
        distance = Math.sqrt( distance );
        return distance;
    }
}
