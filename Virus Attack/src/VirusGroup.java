
import java.awt.Canvas;
import java.util.ArrayList;


public class VirusGroup{
    ArrayList<Virus> viruses;
    private int groupNum;

    public VirusGroup(){
        viruses = new ArrayList<>();
    }

    public VirusGroup(ArrayList<Virus> toAdd, int n){
        viruses.addAll(toAdd);
        groupNum = n;
    }

    public void addVirus(Virus v){
        viruses.add(v);
    }

    public void update(){
        for(Virus v : viruses){
            v.update();
        }
    }

    public void draw(Canvas canvas){
        for(Virus v : viruses){
            v.draw(canvas);
        }
    }

    public void setCoord(int x, int y){
        for(Virus v : viruses){
            v.setCoord(x, y);
        }
    }

    public int size(){
        return viruses.size();
    }

    public Virus remove(int n){
        return viruses.remove(n);
    }
}
