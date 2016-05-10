import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;


public class Virus implements Locatable, Attacker
{

    private int x, y; // current loc

    private int prex, prey;

    private int speed, attack, health; // stats

    private int vx, vy;

    private int xL, yL; // location it is headed to

    private int width, height;

    private int attackRadius = 80;

    private int num;
    ArrayList<Line> lines;


    // construct a virus
    public Virus( int x, int y )
    {

        this.x = x;
        prex = x;
        this.y = y;
        prey = y;
        speed = 5;
        attack = 2;
        health = 6;

        width = 30;
        height = 30;
        lines = new ArrayList<Line>();

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


    // update the status of the virus
    // Also checks if anything is in attack radius
    public void update( Canvas canvas )
    {
        Graphics g = canvas.getGraphics();
        Color c = ( World.BCOLOR );

        // c=new Color(255, 0, 0, 254 );
        g.setColor( c );
        g.fillRect( x, y, width, height );
        x += vx;
        y += vy;
        g.setColor( World.BCOLOR );
        for (Line l : lines)
        {
            g.drawLine( l.virusx() + width / 4,
                    l.virusy() + height / 4,
                    l.linex() + l.av().getWidth() / 4,
                    l.liney() + l.av().getHeight() / 4 );
        }
        lines.clear();
        for ( int i = 0; i < AntiVirusManager.anti.size(); i++ )
        {
            AntiVirus av = AntiVirusManager.anti.get( i );
            if ( getDistance( av ) <= attackRadius )
            {

                //System.out.println( "here" );
                av.reduceHealth( attack );
                g = canvas.getGraphics();
                g.setColor( Color.black );
                g.drawLine( x + width / 4,
                        y + height / 4,
                        av.getX() + av.getWidth() / 4,
                        av.getY() + av.getHeight() / 4 );
                lines.add( new Line(x, y, av.getX(), av.getY(), av) );
                //if ( av.isDead() )


            }

        }
    }


    public void draw( Canvas canvas )
    {
        Graphics g = canvas.getGraphics();
        g.setColor( new Color( 122, 122, 0 ) );
        g.fillRect( x, y, width, height );
        g.setColor( World.BCOLOR );
        g.drawRect( x + width / 4, y + height / 4, width / 2, height / 2 );
    }


    @Override
    public int getX()
    {
        return x;
    }


    @Override
    public int getY()
    {
        // TODO Auto-generated method stub
        return y;
    }


    @Override
    public double getDistance( Locatable other )
    {
        // TODO Auto-generated method stub
        double distance = ( x - other.getX() ) * ( x - other.getX() ) + ( y - other.getY() ) * ( y - other.getY() );
        distance = Math.sqrt( distance );
        return distance;
    }

    public int getAttackRadius()
    {
        return attackRadius;
    }
}
