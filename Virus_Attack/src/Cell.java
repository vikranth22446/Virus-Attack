package Virus_Attack.src;

import java.awt.*;


/**
 *
 * Abstract class which defines a cell. All cells can be attacked by Viruses,
 * but different cells have different functions. This class contains very basic
 * methods that pertain to all cells, as well as several abstract methods such
 * as update(), setHealth(), etc.
 **
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
    private int radius = 50;

    /**
     * Health value for cell
     */
    private double health;
    /**
     * Constructs a new cell object.
     *
     * @param x
     * @param y
     * @param health
     */
    private final double maxHealth;
    public Cell( int x, int y, int health)
    {
        this.x = x;
        this.y = y;
        this.health = health;
        radius = 50;
        maxHealth = health;
    }
  public int getRadius()
    {
        return radius;
    }
  public double max()
  {
      return maxHealth;
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

    public abstract void draw(Graphics g, int xOffset, int yOffset);


    public abstract void decrementHealth( int down );


    public abstract void increaseHealth( int up );

    public abstract int getIndex();
    public double getHealth()
    {
        return health;
    }

}