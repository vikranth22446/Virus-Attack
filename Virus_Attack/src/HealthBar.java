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
    public void draw(Graphics graph,Cell c,int xOffset, int yOffset){
        cell = c;
        if(cell instanceof SickCell){
           // Graphics graph = canvas.getGraphics();
            int x = 100;
            graph.setColor( new Color(255, 26, 41) );
            graph.fillRect(cell.getX()-25 - xOffset,cell.getY()-20-yOffset,(int)Math.abs(cell.getHealth()),10);
            x+=(int)cell.getHealth();
            drawOtherHalf(graph,x,xOffset,yOffset);
        }
        else {
           // Graphics graph = canvas.getGraphics();
            int x = 100;
            graph.setColor( new Color(0, 255, 59) );
            graph.fillRect(cell.getX()-25-xOffset,cell.getY()-20-yOffset,(int)Math.abs(cell.getHealth()),10);
            x-=(int)cell.getHealth();
          //  System.out.println( xOffset + " " + yOffset );
            drawOtherHalf(graph,x,xOffset,yOffset);
        }
    }
    public void drawOtherHalf(Graphics g, int x, int xOffset,int yOffset){
       // Graphics graph = canvas.getGraphics();
        g.setColor(new Color(255, 26, 41));
        g.fillRect(cell.getX()-25+(int)Math.abs(cell.getHealth()) -xOffset,cell.getY()-20 -yOffset,Math.abs(x),10);
    }
}
