package RegularClasses;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * RegularClasses.VirusGroup that holds its own viruses, it receives commands from the manager
 * and then delegates them to the viruses
 *
 * @author Alex M
 */
public class VirusGroup {

    /**
     * Array List holding all the viruses
     */
    private final ArrayList<Virus> viruses;

    /**
     * creates a virus group
     *
     * @param v the starting virus
     */
    public VirusGroup(Virus v) {
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
    public VirusGroup(Virus[] toAdd) {
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
    public void update(Graphics g, int xOffset, int yOffset) {
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
    public void setCoordinate(int x, int y) {
        for (Virus v : viruses) {
            v.setCoordinate(x, y);
        }
    }

    /**
     * number of viruses this group has
     *
     * @return the size of the array list
     */
    public int size() {
        return viruses.size();
    }

    /**
     * gets the i-th virus
     *
     * @param i the index of the virus
     * @return returns the reference of the i-th virus
     */
    public Virus getVirus(int i) {
        return viruses.get(i);
    }

    /**
     * removes the n-th virus from this group
     *
     * @param n index of virus
     * @return returns the removed virus
     */
    public Virus remove(int n) {
        return viruses.remove(n);
    }

    /**
     * add a virus to this group
     *
     * @param virus hte virus to be added
     */
    public void addVirus(Virus virus) {
        viruses.add(virus);
    }
    
    /**
	 * Testing purposes
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return "RegularClasses.VirusGroup[" + "Viruses:" + viruses.size()+"]";
	}
}