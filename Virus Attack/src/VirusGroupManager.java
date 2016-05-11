package v2;

import java.awt.Canvas;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;



public class VirusGroupManager {

    HashMap<Integer, VirusGroup> groups; //hashmap holding all the groups
    ArrayList<Integer> keys; //keys of hashmap

    int currentGroup; //current group being controled
    int groupNum;

    public VirusGroupManager(){
        groups = new HashMap<Integer, VirusGroup>();
        groups.put(1, new VirusGroup(new Virus(100, 100)));

        keys = new ArrayList<Integer>();
        keys.add(1);

        currentGroup = 1;
        groupNum = 1;
    }

    public void update(Canvas canvas){
        for(int n : keys){
            groups.get(n).update(canvas);
        }
    }

    public void updateCoord(int current, int newX, int newY){
        groups.get(current).setCoord(newX, newY);
    }

    public void addVirus(int current){
        groups.get(current).addVirus(new Virus(300, 300));
    }

    public void split(int current){
        Virus[] newGroup = new Virus[groups.get(current).size()/2];
        for(int i=0; i<newGroup.length; i++){
            newGroup[i] = groups.get(current).remove(i);
        }
        groupNum++;
        groups.put(groupNum, new VirusGroup(newGroup, groupNum));
        keys.add(groupNum);
    }

    public int groupNum(){
        return groupNum;
    }
    public VirusGroup currentGroup()
    {
        return groups.get( groupNum );
    }




}