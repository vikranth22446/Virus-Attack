package src;



import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class SickCell extends Cell {

    private int generateAt;
    private int ticks;
    private int index;

    public int getIndex() {
        return index;
    }

    public SickCell(int x, int y, int health, int index) {
        super(x, y, health);
        this.index = index;
        ticks = 0;
        generateAt = 500;
    }


    @Override
    public void produceValues() {

    }


    @Override
    public void draw(Graphics g,int xOffset,int yOffset) {
    //    Graphics g = canvas.getGraphics();
        g.setColor(new Color(255, 0, 0));
        //File img = new File("pixelred.png");
        BufferedImage in;
        try
        {
            in = ImageIO.read(new File("pixelsick.png"));
            BufferedImage newImage = new BufferedImage(
                in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_ARGB);
            //Graphics2D g1 = newImage.createGraphics();
            g.drawImage( in, getX() - xOffset, getY() - yOffset, null );
          //  System.out.println( "hi" );
        }
        catch ( IOException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        HealthBar healthBar = new HealthBar(this);
        healthBar.draw(g,this, xOffset, yOffset, (int)max());
//        HealthBar healthBar = new HealthBar(this);
//        healthBar.draw(canvas);
    }


    public boolean isCured() {
        return getHealth() > 0;
    }


    public void decrementHealth(int decreaseBy) {
        setHealth(getHealth() - decreaseBy);

    }


    public void increaseHealth(int increaseBy) {
        setHealth(getHealth() + increaseBy);
    }


    @Override
    public void updateViruses(VirusGroup group) {
//
//        for ( int i = 0; i < group.size(); i++ )
//        {
//            if (getDistance( group.getVirus( i ) ) <= group.getVirus(i).getAttackRadius())
//            {
//                setHealth( getHealth() - 1 );
//                if (!getViruses().contains(group.getVirus( i )))
//                {
//                    addVirus(group.getVirus( i ));
//                }
//
//            }
//        }

    }

    public void produceUnit() {
        if (ticks >= generateAt) {
            VirusGroupManager.addVirus(-20+getX(), getY());
            ticks = 0;
        }
        ticks++;
    }

}