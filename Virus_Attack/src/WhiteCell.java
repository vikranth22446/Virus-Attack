

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;


public class WhiteCell extends Cell implements AI {
    private static String stuff = "C:\\Users\\viks\\Documents\\APCS-Final-Project\\Virus_Attack";

    private int ticks;

    private int generateAt;

    private int index;

    private int splitTime;

    private int vx, xL;

    private int vy, yL;

    private boolean attacking, healing;

    private int speed;

    private int sightRadius = 300;

    private int drift = 50;

    private int attackRadius = 80;

    private int attack;

    public boolean beingAttacked = false;

    public boolean tracking = false;


    public WhiteCell(int x, int y, int health, int index) {
        super(x, y, health);
        this.index = index;
        ticks = 0;
        generateAt = 200;
        vx = 0;
        vy = 0;
        speed = 5;
        attack = 1;
        attacking = false;
        healing = false;
        drift = 50;
    }


    public void die(Canvas canvas) {
        Graphics g = canvas.getGraphics();
        g.setColor(World.BCOLOR);
        g.fillOval(getX(), getY(), 50, 50);
        g.drawOval(getX(), getY(), 50, 50);

    }


    public void split(ArrayList<Cell> w) {
        int t1 = (int) (getX() + Math.random() * 67);
        int t2 = (int) (getY() + Math.random() * 67);

        w.add(new WhiteCell(t1, t2, 100, w.size()));
        splitTime = 0;
    }


    @Override
    public void draw(Graphics g, int xOffset, int yOffset) {
        // Graphics g = canvas.getGraphics();
        g.setColor(new Color(255, 0, 0));
        // File img = new File("pixelred.png");
        BufferedImage in;
        try {
           in = ImageIO.read( new File(stuff + "\\pixelwhite.png") );
    //        in = ImageIO.read(new File( "pixelwhite.png"));
            BufferedImage newImage = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_ARGB);
            // Graphics2D g1 = newImage.createGraphics();
            g.drawImage(in, getX() - xOffset, getY() - yOffset, null);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        HealthBar healthBar = new HealthBar(this);
        healthBar.draw(g, xOffset, yOffset);

    }


    public boolean beingAttacked() {
        return beingAttacked;
    }


    public void setAttacked(boolean attacked) {
        beingAttacked = attacked;
    }


    public void decrementHealth(int decreaseBy) {
        setHealth(getHealth() - decreaseBy);

    }


    public void increaseHealth(int increaseBy) {
        setHealth(getHealth() + increaseBy);

    }


    public boolean canEnemyHurt(int enemyX, int enemyY, int ableRadius) {
        double hyp = Math
                .sqrt(Math.pow(Math.abs(getX() - enemyX), 2) + Math.pow(Math.abs(getY() - enemyY), 2));
        if (ableRadius > hyp) {
            return false;
        }
        return true;

    }


    public int getTime() {
        return splitTime;
    }


    public void updateTime() {
        splitTime++;
        // System.out.println( splitTime );
    }


    public void setCoord(int nx, int ny) {

        xL = nx;
        yL = ny;
        double hyp = Math.sqrt((xL - getX()) * (xL - getX()) + (yL - getY()) * (yL - getY()));
        double scale = speed / hyp;
        vx = (int) ((xL - getX()) * scale / 2.0);
        vy = (int) ((yL - getY()) * scale / 2.0);
    }


    public void move() {
        setX(getX() + vx);
        setY(getY() + vy);
        // int moveX = (int) (Math.random() * 2);
        // if (moveX == 1)
        // {
        // setX(getX()+5);
        // }
        // else
        // {
        // setX(getX()-5);
        // }
        // int moveY = (int) (Math.random() * 2);
        // if (moveY == 1)
        // {
        // setY(getY()+5);
        // }
        // else
        // {
        // setY(getY()-5);
        // }
    }


    public void produceUnit() {
        if (ticks >= generateAt) {
            AntiVirusManager.addAnti(getX() + getRadius(), getY() + getRadius());
            ticks = 0;
        }
        ticks++;
    }


    @Override
    public void sendSignal() {
        // TODO Auto-generated method stub

    }


    // @Override
    public void findVirus(Graphics g, int xOffset, int yOffset) {
        // TODO Auto-generated method stub
        boolean attacking = false;
        int closest = Integer.MAX_VALUE;
        int closestSick = Integer.MAX_VALUE;
        Iterator<Entry<Integer, VirusGroup>> it = VirusGroupManager.virusGroupMap().entrySet().iterator();
        while (it.hasNext()) {
            VirusGroup pair = it.next().getValue();
            int add = 0;
            for (int j = 0; j < CellManager.sickValues.size(); j++) {
                Cell c = CellManager.sickValues.get(j);
                // System.out.println( c.getHealth() + "hhi" );
                if (getDistance(c) <= sightRadius && getDistance(c) < closestSick) {
                    setCoord(c.getX(), c.getY());
                    healing = true;

                }
                if (getDistance(c) <= attackRadius) {
                    c.increaseHealth(attack);
                    g.setColor(Color.green);
                    g.drawLine(getX() + getRadius() - xOffset,
                            getY() + getRadius() / 2 - yOffset,
                            c.getX() + c.getRadius() - xOffset,
                            c.getY() + c.getRadius() / 2 - yOffset);
                    // System.out.println( c.getHealth() );
                    if (c.getHealth() >= 0) {
                        CellManager.convertSick(c);
                        healing = false;
                    }
                    break;
                }

                // healing = false;

                if (attacking) {
                    // return;
                }
            }
            if (!healing) {
                for (int i = 0; i < pair.size(); i += add) {
                    Virus v = pair.getVirus(i);
                    add = 1;
                    if (getDistance(v) <= sightRadius && getDistance(v) < closest) {
                        setCoord(v.getX(), v.getY());
                        tracking = true;
                    } else {
                        int chance = (int) (Math.random() * 1000);

                        if (chance == 50) {
                            setCoord(v.getX(), v.getY());
                            tracking = true;
                        }
                    }

                    if (getDistance(v) <= attackRadius && (!healing || beingAttacked)) {
                        attacking = true;
                        v.reduceHealth(attack);
                        // g = canvas.getGraphics();
                        g.setColor(Color.red);
                        g.drawLine(getX() + getRadius() - xOffset,
                                getY() + getRadius() / 2 - yOffset,
                                v.getX() + v.getWidth() / 2 - xOffset,
                                v.getY() + v.getHeight() / 2 - yOffset);
                        if (v.isDead()) {
                            pair.remove(i);
                            add = 0;
                            attacking = false;
                            tracking = false;
                        }

                    }
                    boolean apple = true;
                    if (attacking || tracking) { // break;
                        apple = false;
                        if (!attacking && tracking) {
                            int chance = (int) (Math.random() * 500);

                            if (chance == 50) {
                                setCoord(v.getX(), v.getY());

                                tracking = false;
                                apple = true;
                            }
                        }

                    } else if (apple) {
                        drift++;
                        if (drift > 50) {
                            // System.out.println( "mew" );
                            int moveX = (int) (Math.random() * 2);
                            if (moveX == 0) {
                                setCoord(getX() + 50, getY() + 50);
                            } else if (moveX == 1) {
                                setCoord(getX() + 50, getY() - 50);
                            } else if (moveX == 2) {
                                setCoord(getX() - 50, getY() - 50);
                            } else {
                                setCoord(getX() - 50, getY() + 50);
                            }
                            drift = 0;
                        }

                    }

                }

                // it.remove(); // avoids a ConcurrentModificationException
            }
        }
        // healing = false;
        // attacking = false;
        // tracking = false;

    }


    @Override
    public void callHelp() {
        // TODO Auto-generated method stub

    }


    @Override
    public boolean needHelp() {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public boolean canGiveHelp() {
        // TODO Auto-generated method stub
        return false;
    }

}