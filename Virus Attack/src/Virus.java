import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

/**
 *    Virus is the tiny dot that is used to conquer other nodes
 */
public class Virus{

    private int x, y; //current loc
    private int  attack = 2, health = 6;

    //stats
    private int velocityX, velocityY;
    private int newXLocation, newYLocation; //location it is headed to

    //construct a virus
    public Virus(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //set a new coordinate for the virus to head to
    public void setCoord(int newXLocation, int newYLocation){
        this.newXLocation = newXLocation;
        this.newYLocation = newYLocation;
        double hyp = calculateHypotenuse(this.newXLocation, this.newYLocation);
        double scale = 5/hyp;

        velocityX = (int) ((newXLocation - x) * scale);
        velocityY = (int) ((newYLocation - y) * scale);
    }
    public double calculateHypotenuse(int xLocation, int yLocation){
          return Math.sqrt( Math.pow(xLocation - getX(), 2) + Math.pow(yLocation - getY(),2));
    }
    //update the status of the virus
    public void update(){
        x += velocityX;
        y += velocityY;
    }

    public void draw(Canvas canvas){
        Graphics g = canvas.getGraphics();
        g.setColor(new Color(255, 0, 0));
        g.fillRect(x, y, 10, 10);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getNewXLocation() {
        return newXLocation;
    }

    public int getNewYLocation() {
        return newYLocation;
    }


}