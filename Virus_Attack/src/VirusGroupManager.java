
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Manages the virus groups
 * 
 * knows which group is to be updated, controls splitting and merging groups, keeps tracks ono all the groups in a 
 * hash map and has another array list to access the keys   
 * 
 * @author alexm
 *
 */
public class VirusGroupManager {

    /**
     * hashmap holding all the virus groups
     */
    public static HashMap<Integer, VirusGroup> groups; 
    /**
     * array list that holds all the keys to the hash map
     */
    ArrayList<Integer> keys; 
    /**
     * the current group that is going to be updated
     */
    public static int currentGroup; 
    /**
     * the number of groups
     */
    int groupNum;

    /**
     * constructor for the manager, creates the first group, creates variales
     */
    public VirusGroupManager(){
        groups = new HashMap<Integer, VirusGroup>();
        groups.put(1, new VirusGroup(new Virus(100, 100)));

        keys = new ArrayList<Integer>();
        keys.add(1);

        currentGroup = 1;
        groupNum = 1;
    }

    /**
     * calls the draw methods of all the virus groups
     *  	
     * @param g the canvas object	
     * @param xOffset the x offset to draw at
     * @param yOffset the y offset to draw at
     */
    public void draw(Graphics g, int xOffset, int yOffset){
        for(int n : keys){
            groups.get(n).draw(g, xOffset, yOffset);
        }
    }

    /**
     * calls the update method for all the virus groups
     * 
     * @param g the canvas object
     * @param xOffset the x offset to draw at
     * @param yOffset the y offset to draw at
     */
    public void updateLocation(Graphics g, int xOffset, int yOffset){
        for(int n: keys){
            groups.get(n).update(g, xOffset, yOffset);
        }
    }

    /**
     * change the current being control group
     * 
     * @param current the  group to switch to
     */
    public void changeCurrent(int current){
        currentGroup = current;
    }

    /**
     * updates the coordinate of the current group
     * 
     * @param newX the new x coordinate
     * @param newY the new y coordinate
     */
    public void updateCoord(int newX, int newY){
        groups.get(currentGroup).setCoord(newX, newY);
    }

    /**
     * adds a virus to the current group
     * 
     * @param x virus starts at x
     * @param y virus starts at y
     */
    public static void addVirus(int x, int y){
        groups.get(currentGroup).addVirus(new Virus(x, y));
    }

    /**
     * splits the current group into 2 new groups
     */
    public void split(){
        Virus[] newGroup = new Virus[groups.get(currentGroup).size()/2];
        for(int i=0; i<newGroup.length; i++){
            newGroup[i] = groups.get(currentGroup).remove(i);
        }
        groupNum++;
        groups.put(groupNum, new VirusGroup(newGroup, groupNum));
        keys.add(groupNum);
    }
    
    /**
     * merges current group and group n
     * 
     * @param n the group to be merged
     */
    public void merge(int n){
    	for(int i=0; i<groups.get(n).size(); i++){
    		groups.get(currentGroup).addVirus(groups.get(n).remove(i));
    	}
    	keys.remove(n);
    	groupNum--;
    }
    
    /**
     * checks if the key "n" exists
     * 
     * @param n the key to be check for
     * @return true if does contain the key false if not
     */
    public boolean hasKey(int n){
    	return keys.contains(n);
    }
    /**
     * 
     * the number of groups
     * @return the number of groups
     */
    public int groupNum(){
        return groupNum;
    }
    /**
     * returns the reference of the current group
     * @return the reference of the current group
     */
    public VirusGroup currentGroup()
    {
        return groups.get( groupNum );
    }
    /**
     * the hash map getter method
     * 
     * @return the reference of the hash map
     */
    public static HashMap<Integer, VirusGroup> virusGroupMap()
    {
        return groups;
    }




}