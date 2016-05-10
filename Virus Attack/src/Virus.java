import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class Virus{

    private int x, y; //current loc
    private int speed, attack, health; //stats
    private int vx, vy;
    private int xL, yL; //location it is headed to

    //construct a virus
    public Virus(int x, int y) {

        this.x = x;
        this.y = y;
        speed = 5;
        attack = 2;
        health = 6;

    }

    //set a new coordinate for the virus to head to
    public void setCoord(int nx, int ny){

        xL = nx;
        yL = ny;
        double hyp = Math.sqrt( (xL - x )*(xL - x) + (yL - y) * (yL - y) );
        double scale = speed/hyp;
        vx = (int) ((xL - x) * scale);
        vy = (int) ((yL - y) * scale);
    }

    //update the status of the virus
    public void update(){
        x += vx;
        y += vy;
    }

    public void draw(Canvas canvas){
        Graphics g = canvas.getGraphics();
        g.setColor(new Color(255, 0, 0));
        g.fillRect(x, y, 10, 10);
    }

}
