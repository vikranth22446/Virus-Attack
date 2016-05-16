import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;



public class VirusGroupManager {

    public static HashMap<Integer, VirusGroup> groups; //hashmap holding all the groups
    ArrayList<Integer> keys; //keys of hashmap

    public static int currentGroup; //current group being controled
    int groupNum;

    public VirusGroupManager(){
        groups = new HashMap<Integer, VirusGroup>();
        groups.put(1, new VirusGroup(new Virus(100, 100)));

        keys = new ArrayList<Integer>();
        keys.add(1);

        currentGroup = 1;
        groupNum = 1;
    }

    public void draw(Canvas canvas){
        for(int n : keys){
            groups.get(n).draw(canvas);
        }
    }

    public void updateLocation(Canvas canvas){
        for(int n: keys){
            groups.get(n).update(canvas);
        }
    }

    public void changeCurrent(int current){
        currentGroup = current;
    }

    public void updateCoord(int newX, int newY){
        groups.get(currentGroup).setCoord(newX, newY);
    }

    public static void addVirus(int x, int y){
        groups.get(currentGroup).addVirus(new Virus(x, y));
    }

    public void split(){
        Virus[] newGroup = new Virus[groups.get(currentGroup).size()/2];
        for(int i=0; i<newGroup.length; i++){
            newGroup[i] = groups.get(currentGroup).remove(i);
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


    public void combine() {

        //TODO IMPLEMENT IT
    }
}