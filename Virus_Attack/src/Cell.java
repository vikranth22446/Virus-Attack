package src;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


/**
 *
 * Abstract class which defines a cell. All cells can be attacked by Viruses,
 * but different cells have different functions. This class contains very basic
 * methods that pertain to all cells, as well as several abstract methods such
 * as update(), setHealth(), etc.
 *
 * @author Melissa Wei
 * @version May 6, 2016
 * @author Period: 6
 * @author Assignment: APCSFinalProject
 *
 * @author Sources: n/a
 */
public abstract class Cell implements Locatable
{
    /**
     * The x-value on coordinate grid
     */
    private int x;

    /**
     * The y-value on coordinate grid
     */
    private int y;

    /**
     * Radius value for cell
     */
    private int radius;

    /**
     * Health value for cell
     */
    private double health;
    private ArrayList<Virus> viruses;


    /**
     * Constructs a new cell object.
     *
     * @param x
     * @param y
     * @param health
     */
    public Cell( int x, int y, int health)
    {
        this.x = x;
        this.y = y;
        this.health = health;
        viruses = new ArrayList<Virus>();
        radius = 50;
    }

    public ArrayList<Virus> getViruses()
    {
        return viruses;
    }
    public void addVirus(Virus e)
    {
        viruses.add( e );
    }
    public int numViruses()
    {
        return viruses.size();
    }

    public int getRadius()
    {
        return radius;
    }


    public int getX()
    {
        return x;
    }


    public int getY()
    {
        return y;
    }


    public void setX( int x )
    {
        this.x = x;
    }


    public void setY( int y )
    {
        this.y = y;
    }


    public void setHealth( double health )
    {
        this.health = health;
    }


    public abstract void produceValues();
    public abstract void updateViruses(VirusGroup group);



    public double getDistance( Locatable other )
    {
        double dist = Math.sqrt(
                ( ( (x + radius/2)- other.getX() ) * ( x - other.getX() ) + ( y - other.getY() ) * ( y - other.getY() ) ) );
        return dist;
    }


    // public abstract boolean canEnemyHurt( int enemyX, int enemyY, int ableRadius );


    public abstract void produceUnit();

    public void draw(Graphics g)
    {
       // Graphics g = canvas.getGraphics();
        g.setColor( new Color( 19, 255, 240 ) );
        g.fillOval( x, y, 50, 50 );

    }


    public abstract void decrementHealth( int down );


    public abstract void increaseHealth( int up );

    public abstract int getIndex();
    public double getHealth()
    {
        return health;
    }

}