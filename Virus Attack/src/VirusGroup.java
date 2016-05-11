
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;


public class VirusGroup{

    ArrayList<Virus> viruses;
    private int groupNum;

    public VirusGroup(Virus v){
        viruses = new ArrayList<Virus>();
        viruses.add(v);
        viruses.add(new Virus(150, 200));

    }

    public VirusGroup(Virus[] toAdd, int n){
        viruses = new ArrayList<Virus>();
        for(Virus v : toAdd){
            viruses.add(v);
        }
        groupNum = n;
    }

    public void addVirus(Virus v){
        viruses.add(v);
    }

    public void update(Canvas canvas){
        for(Virus v : viruses){
            v.update(canvas);
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
    public Virus getVirus(int i){
        return viruses.get( i );
    }


    public Virus remove(int n){
        return viruses.remove(n);
    }

}