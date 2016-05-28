package src;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * VirusGroup that holds its own viruses, it receives commands from the manager
 * and then delegates them to the viruses
 *
 * @author Alex M
 */
class VirusGroup {

    /**
     * Array List holding all the viruses
     */
    private final ArrayList<Virus> viruses;

    /**
     * creates a virus group
     *
     * @param v the starting virus
     */
    VirusGroup(Virus v) {
        viruses = new ArrayList<>();
        viruses.add(v);
        viruses.add(new Virus(150, 200));

    }

    /**
     * overloaded constructor, has a array list of viruses to add and assigns a
     * group number typically called when splitting groups
     *
     * @param toAdd array of viruses to add
     */
    VirusGroup(Virus[] toAdd) {
        viruses = new ArrayList<>();
        Collections.addAll(viruses, toAdd);
    }

    /**
     * calls the draw method of all the viruses it has
     *
     * @param g       the canvas to be drawn on
     * @param xOffset the x offset
     * @param yOffset the y offset
     */
    public void draw(Graphics g, int xOffset, int yOffset) {
        for (Virus v : viruses) {
            v.draw(g, xOffset, yOffset);
        }
    }

    /**
     * updates the position of all the viruses
     *
     * @param g       the canvas
     * @param xOffset the x offset
     * @param yOffset the y offset
     */
    void update(Graphics g, int xOffset, int yOffset) {
        for (Virus v : viruses) {
            v.checkAttackRadius(g, xOffset, yOffset);
            v.update();
        }
    }

    /**
     * sets a new coordinate for the viruses to head to
     *
     * @param x the new x coordinate
     * @param y the new y coordinate
     */
    void setCoordinate(int x, int y) {
        for (Virus v : viruses) {
            v.setCoordinate(x, y);
        }
    }

    /**
     * number of viruses this group has
     *
     * @return the size of the array list
     */
    int size() {
        return viruses.size();
    }

    /**
     * gets the i-th virus
     *
     * @param i the index of the virus
     * @return returns the reference of the i-th virus
     */
    Virus getVirus(int i) {
        return viruses.get(i);
    }

    /**
     * removes the n-th virus from this group
     *
     * @param n index of virus
     * @return returns the removed virus
     */
    Virus remove(int n) {
        return viruses.remove(n);
    }

    /**
     * add a virus to this group
     *
     * @param virus hte virus to be added
     */
    void addVirus(Virus virus) {
        viruses.add(virus);
    }
    
    /**
	 * Testing purposes
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return "VirusGroup[" + "Viruses:" + viruses.size()+"]";
	}
	public boolean contains(Virus v)
	{
	    return viruses.contains( v );
	}
	public void removeVirus(Virus v)
	{
	    if (viruses.indexOf( v ) >=0)
	    viruses.remove( viruses.indexOf( v ) );
	}
}