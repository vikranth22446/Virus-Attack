
import java.awt.Canvas;
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
            groups.get(n).update();
            groups.get(n).draw(canvas);
        }
    }

    public void updateCoord(int current, int newX, int newY){
        groups.get(current).setCoord(newX, newY);
    }

    public void addVirus(int current){
        groups.get(current).addVirus(new Virus(300, 300));
    }

    public void split(int current){
        ArrayList<Virus> newGroup = new ArrayList<Virus>();
        for(int i=0; i<groups.get(current).size()/2; i++){
            newGroup.add(groups.get(current).remove(i));
        }
        groupNum++;
        groups.put(groupNum, new VirusGroup(newGroup, groupNum));
        keys.add(groupNum);
    }

    public int groupNum(){
        return groupNum;
    }




}
