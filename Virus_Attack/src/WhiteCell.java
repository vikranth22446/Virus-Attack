

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * An implementation of the abstract class cell. This class is controlled by an AI. It attacks
 * all the sick cells. It also goes through mitosis, to make the game harder.
 */
public class WhiteCell extends Cell {
    /**
     * The ticks used to keep track of time
     */
    private int ticks = 0;
    /**
     * The time at which the ticks generate
     */
    private int generateAt = 200;

    private int splitTime;

    private int vx = 0, xL;

    private int vy = 0, yL;

    private boolean healing;

    private int speed;

    private int sightRadius = 300;

    private int drift = 50;

    private int attackRadius = 80;

    private int attack;

    public boolean beingAttacked = false;

    public boolean tracking = false;

    /**
     * Initializes the x coordinate, y coordinate, and health.
     *
     * @param x      the x coordinate
     * @param y      the y coordinate
     * @param health the initial health of the cell
     */
    public WhiteCell(int x, int y, int health) {
        super(x, y, health);

        speed = 5;
        attack = 1;
        healing = false;
        drift = 50;
    }

    /**
     * Creates 2 cells from the current x position.
     * the both are positions at (int) (getX() + Math.random() * 67);
     *
     * @param w the arrayList of cells.
     */
    public void split(ArrayList<Cell> w) {
        int t1 = (int) (getX() + Math.random() * 67);
        int t2 = (int) (getY() + Math.random() * 67);

        w.add(new WhiteCell(t1, t2, 100));
        splitTime = 0;
    }


    @Override
    public void draw(Graphics g, int xOffset, int yOffset) {
        super.draw(g, xOffset, yOffset);
        g.setColor(new Color(255, 0, 0));
        BufferedImage in;
        try {
            in = ImageIO.read(new File("pixelwhite.png"));
            g.drawImage(in, getX() - xOffset, getY() - yOffset, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void produceUnit() {
        if (ticks >= generateAt) {
            AntiVirusManager.addAnti(getX() + getRadius(), getY() + getRadius());
            ticks = 0;
        }
        ticks++;
    }


    public void setAttacked(boolean attacked) {
        beingAttacked = attacked;
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
                        CellManager.convertCell(c);
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

    }



}