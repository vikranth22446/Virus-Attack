import java.awt.*;

public abstract class Cell implements Locatable {
    private int x;
    private int y;
    private int health;
    private int index;
    public Cell(int x, int y, int health,  int index) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.index = index;
    }
    public void draw(Canvas canvas,int position){
        Graphics g = canvas.getGraphics();
        g.setColor(new Color(19, 255, 240));
        g.fillOval(x, y, 50, 50);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }


    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public abstract void produceValues();

    public abstract boolean canEnemeyHurt(int enemyX, int enemyY, int ableRadius);
}
