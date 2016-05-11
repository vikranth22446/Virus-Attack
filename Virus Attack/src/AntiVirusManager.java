package v2;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public class AntiVirusManager {

    public static ArrayList<AntiVirus> anti;

    public AntiVirusManager() {
        anti = new ArrayList<AntiVirus>();
        anti.add(new AntiVirus(300, 300));
        anti.add(new AntiVirus(310, 310));

    }

    public void update(Canvas canvas) {
        for (AntiVirus n : anti) {
            n.draw(canvas);
            n.update();
        }
    }

    public void updateCoord(int current, int newX, int newY) {
        for (AntiVirus n : anti) {
            n.setCoord(newX, newY);;
        }
    }

    public void checkDead(Canvas canvas){
        int add= 0;
        for(int i = 0; i < anti.size(); i+=add){
            add =1;
            if(anti.get( i ).isDead()){
                Graphics g = canvas.getGraphics();
                Color c= World.BCOLOR ;
                g.setColor(c);
                AntiVirus v = anti.get( i );
                g.fillRect( v.getX(), v.getY(), v.getWidth(), v.getHeight() );
                anti.remove(v);
                add =0;

            }
        }
    }

    public void addVirus(AntiVirus av) {
        anti.add(av);
    }

    public ArrayList<AntiVirus> getAnti(){
        return anti;
    }

}