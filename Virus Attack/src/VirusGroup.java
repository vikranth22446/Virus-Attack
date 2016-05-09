
import java.awt.Canvas;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;


public class VirusGroup{

    ArrayList<Virus> viruses;
    private int groupNum;

    public VirusGroup(){
        viruses = new ArrayList<Virus>();
    }

    public VirusGroup(ArrayList<Virus> viruses, int n){
        this.viruses = new ArrayList<Virus>();
        this.viruses.addAll(viruses);
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
