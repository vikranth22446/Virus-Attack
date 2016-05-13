
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class AntiVirus implements Locatable, Attacker {

    private int x, y, xLocation, yLocation;
    private int speed = 5, attack = 2, health = 20; //stats
    private int vx, vy; //location it is headed to
    private boolean move;
    private int width = 50, height = 50;

    public AntiVirus(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //set a new coordinate for the virus to head to
    public void setCoord(int nx, int ny) {

        xLocation = nx;
        yLocation = ny;
        double hyp = Math.sqrt((xLocation - x) * (xLocation - x) + (yLocation - y) * (yLocation - y));
        double scale = speed / hyp;

        vx = (int) ((xLocation - x) * scale);
        vy = (int) ((yLocation - y) * scale);
    }

    public void stop() {
        move = false;
    }

    public void start() {
        move = true;
    }

    //update the status of the virus
    public void update() {
        if (!move) return;
        x += vx;
        y += vy;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void draw(Canvas canvas) {
        Graphics g = canvas.getGraphics();
        g.setColor(Color.blue);
        g.fillRect(x, y, width, height);
    }


    public void reduceHealth(int reduce) {
        health -= reduce;
    }

    public boolean isDead() {
        return health <= 0;
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {

        return y;
    }

    @Override
    public double getDistance(Locatable other) {
        double distance = (x - other.getX()) * (x - other.getX()) +
                (y - other.getY()) * (y - other.getY());
        distance = Math.sqrt(distance);
        return distance;
    }


}