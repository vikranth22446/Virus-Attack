
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
    public void draw(Graphics graph,Cell c,int xOffset, int yOffset, int cellHealth){
        cell = c;
        if(cell instanceof SickCell){
           // Graphics graph = canvas.getGraphics();
            int x = cellHealth;
            graph.setColor( new Color(255, 26, 41) );
            graph.fillRect(cell.getX()-25 - xOffset,cell.getY()-20-yOffset,(int)(Math.abs(cell.getHealth()/cellHealth *100)),10);
        //    System.out.println( (Math.abs(cell.getHealth()/cellHealth *100)));
            x+=(int)cell.getHealth();
           // drawOtherHalfSick(graph,x,xOffset,yOffset, cellHealth);
        }
        else {
           // Graphics graph = canvas.getGraphics();
            int x = cellHealth;

            graph.setColor( new Color(0, 255, 59) );
            graph.fillRect(cell.getX()-25-xOffset,cell.getY()-20-yOffset,(int)(Math.abs(cell.getHealth())/cellHealth *100),10);
            x-=(int)cell.getHealth();
          //  System.out.println( xOffset + " " + yOffset );
            drawOtherHalf(graph,x,xOffset,yOffset, cellHealth);
        }
    }
    public void drawOtherHalf(Graphics g, int x, int xOffset,int yOffset, int cellHealth){
       // Graphics graph = canvas.getGraphics();

        x = (int)(x /((double)cellHealth)*100.0);
        //System.out.println( x );

        g.setColor(new Color(255, 26, 41));
        g.fillRect((int)(cell.getX()-25)+(int)(Math.abs(cell.getHealth()/cellHealth *100)) -xOffset,cell.getY()-20 -yOffset,Math.abs(x),10);
    }
   

}