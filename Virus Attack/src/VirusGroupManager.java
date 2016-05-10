
import java.awt.Canvas;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;



public class VirusGroupManager {

    HashMap<Integer, VirusGroup> groups; //hashmap holding all the groups
    ArrayList<Integer> keys; //keys of hashmap

    int currentGroup; //current group being controled
    int groupNum;

    public VirusGroupManager(){
        groups = new HashMap<>();
        VirusGroup virusGroup = new VirusGroup();
        virusGroup.addVirus(new Virus(100,100));
        groups.put(1, virusGroup);

        keys = new ArrayList<>();
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
        ArrayList<Virus> virusList = new ArrayList<>();
        for(int i=0; i<groups.get(current).size()/2; i++){
            virusList.add(groups.get(current).remove(i));
        }
        groupNum++;
        groups.put(groupNum, new VirusGroup(virusList, groupNum));
        keys.add(groupNum);
    }

    public int groupNum(){
        return groupNum;
    }


}



