

import java.awt.*;
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

   public VirusGroup addVirusSet(VirusGroup v){
        viruses.addAll(v.getViruses());
        return this;
    }


    public void draw(Graphics g, int xOffset, int yOffset){
        for(Virus v : viruses){
            v.draw(g, xOffset, yOffset);
        }
    }

    public void update(Graphics g, int xOffset, int yOffset){
        for(Virus v : viruses){
            v.update(g, xOffset, yOffset);
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

    public ArrayList<Virus> getViruses() {
        return viruses;
    }

    public Virus remove(int n){
        return viruses.remove(n);
    }

    public void addVirus(Virus virus) {
        viruses.add(virus);
    }
}