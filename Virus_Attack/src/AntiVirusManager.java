package src;
import java.awt.*;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class AntiVirusManager {

    public static ArrayList<AntiVirus> anti;

    public AntiVirusManager() {
        anti = new ArrayList<AntiVirus>();
    }

    public void draw(Graphics g, int xOffset, int yOffset) {
        for (AntiVirus n : anti) {
            n.draw(g, xOffset, yOffset);
        }
    }

    public void updateLocation(Graphics g, int xOffset, int yOffset){
        for(AntiVirus av : anti){
            av.update(g, xOffset, yOffset);
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

    public static void addAnti(int x, int y) {
        anti.add(new AntiVirus(x, y));
    }

    public ArrayList<AntiVirus> getAnti(){
        return anti;
    }

}