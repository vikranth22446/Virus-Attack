package src;
import java.awt.*;

/**
 * Created by vikranth on 5/13/2016.
 */
public class HealthBar {
    Cell cell;

    public HealthBar() {
    }

    public HealthBar(Cell cell) {
        this.healthValue = cell.getHealth();
        this.cell = cell;
    }

    private double healthValue;
    public void updateHealth(){
       healthValue = cell.getHealth();
    }
    public void draw(Graphics graph,Cell c){
        cell = c;
        if(cell instanceof SickCell){
           // Graphics graph = canvas.getGraphics();
            int x = 100;
            graph.setColor( new Color(255, 26, 41) );
            graph.fillRect(cell.getX()-25,cell.getY()-20,(int)Math.abs(cell.getHealth()),10);
            x+=(int)cell.getHealth();
            drawOtherHalf(graph,x);
        }
        else {
           // Graphics graph = canvas.getGraphics();
            int x = 100;
            graph.setColor( new Color(0, 255, 59) );
            graph.fillRect(cell.getX()-25,cell.getY()-20,(int)Math.abs(cell.getHealth()),10);
            x-=(int)cell.getHealth();
            drawOtherHalf(graph,x);
        }
    }
    public void drawOtherHalf(Graphics g, int x){
       // Graphics graph = canvas.getGraphics();
        g.setColor(new Color(255, 26, 41));
        g.fillRect(cell.getX()-25+(int)Math.abs(cell.getHealth()),cell.getY()-20,Math.abs(x),10);
    }
}
