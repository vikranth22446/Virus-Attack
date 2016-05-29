



import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * Manages the virus groups
 * <p>
 * knows which group is to be updated, controls splitting and merging groups,
 * keeps tracks ono all the groups in a hash map and has another array list to
 * access the keys
 *
 * @author Alex M
 */
class VirusGroupManager
{

    /**
     * HashMap holding all the virus groups
     */
    static HashMap<Integer, VirusGroup> groups;

    /**
     * array list that holds all the keys to the hash map
     */
    private final ArrayList<Integer> keys;

    /**
     * the current group that is going to be updated
     */
    public static int currentGroup;

    /**
     * the number of groups
     */
    private int groupNum;


    /**
     * constructor for the manager, creates the first group, creates variables
     */
    VirusGroupManager()
    {
        groups = new HashMap<>();
        groups.put( 1, new VirusGroup( new Virus( 100, 100 ) ) );

        keys = new ArrayList<>();
        keys.add( 1 );

        currentGroup = 1;
        groupNum = 1;
    }


    /**
     * calls the draw methods of all the virus groups
     *
     * @param g
     *            the canvas object
     * @param xOffset
     *            the x offset to draw at
     * @param yOffset
     *            the y offset to draw at
     */
    public void draw( Graphics g, int xOffset, int yOffset )
    {
        int counter = 0;
        for ( int n = 1; n <= 6; n++ )
        {
            Color c = new Color( 122, 122, 0 );
            if ( n == 1 )
                c = new Color( 122, 122, 0 );

            else if ( n == 2 )
                c = new Color( 0, 122, 122 );

            else if ( n == 3 )
                c = new Color( 122, 0, 122 );

            else if ( n == 4 )
                c = new Color( 146, 25, 95 );

            else if ( n == 5 )
                c = new Color( 25, 95, 146 );

            else if ( n == 6 )
                c = new Color( 95, 146, 25 );
            if ( groups.get( n ) != null )
            {
                groups.get( n ).setColor( c );
                groups.get( n ).setNum( n );
                if ( groups.get( n ) != null )
                    groups.get( n ).draw( g, xOffset, yOffset );
            }
        }
    }


    /**
     * calls the update method for all the virus groups
     *
     * @param g
     *            the canvas object
     * @param xOffset
     *            the x offset to draw at
     * @param yOffset
     *            the y offset to draw at
     */
    void updateLocation( Graphics g, int xOffset, int yOffset )
    {
        for ( int n : keys )
        {
            groups.get( n ).update( g, xOffset, yOffset );
        }
    }


    /**
     * change the current being control group
     *
     * @param current
     *            the group to switch to
     */
    void changeCurrent( int current )
    {
        currentGroup = current;
    }


    /**
     * updates the coordinate of the current group
     *
     * @param newX
     *            the new x coordinate
     * @param newY
     *            the new y coordinate
     */
    void updateCoordinate( int newX, int newY )
    {
        groups.get( currentGroup ).setCoordinate( newX, newY );
    }


    /**
     * adds a virus to the current group
     *
     * @param x
     *            virus starts at x
     * @param y
     *            virus starts at y
     */
    static void addVirus( int x, int y )
    {
        groups.get( currentGroup ).addVirus( new Virus( x, y ) );
    }

    /**
     * adds a virus to the current group given a virus
     *
     * @param virus the passed virus
     */
    void addVirus(Virus virus )
    {
        groups.get( currentGroup ).addVirus( virus );
    }
    /**
     * splits the current group into 2 new groups
     */
    void split()
    {
        Virus[] newGroup = new Virus[groups.get( currentGroup ).size() / 2];
        for ( int i = 0; i < newGroup.length; i++ )
        {
            newGroup[i] = groups.get( currentGroup ).remove( i );
        }
        groupNum++;
        groups.put( groupNum, new VirusGroup( newGroup ) );
        keys.add( groupNum );
    }


    /**
     * merges current group and group n
     *
     * @param n
     *            the group to be merged
     */
    void merge( int n )
    {
        for ( int i = 0; i < groups.get( n ).size(); i++ )
        {
            groups.get( currentGroup ).addVirus( groups.get( n ).remove( i ) );
        }
        keys.remove( n );
        groupNum--;
    }


    /**
     * checks if the key "n" exists
     *
     * @param n
     *            the key to be check for
     * @return true if does contain the key false if not
     */
    boolean hasKey( int n )
    {
        return keys.contains( n );
    }


    /**
     * the number of groups
     *
     * @return the number of groups
     */
    int groupNum()
    {
        return groupNum;
    }


    /**
     * the hash map getter method
     *
     * @return the reference of the hash map
     */
    static HashMap<Integer, VirusGroup> virusGroupMap()
    {
        return groups;
    }


    /**
     * remove virus v from the selected virus group
     * 
     * @param v the virus to be removed
     */
    public static void remove( Virus v )
    {
        Iterator<Map.Entry<Integer, VirusGroup>> iterator = groups.entrySet().iterator();

        while ( iterator.hasNext() )
        {
            VirusGroup vg = iterator.next().getValue();
            if ( vg.contains( v ) )
            {
                vg.removeVirus( v );
            }
            // You can remove elements while iterating.
            // iterator.remove();
        }

    }
    
    public String toString(){
    	return "VirusGroupManager[numGroups:" + groupNum + " current group:" + currentGroup+"]";
    }

}