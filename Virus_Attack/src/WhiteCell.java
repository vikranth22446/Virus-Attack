

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;


/**
 * An implementation of the abstract class cell. This class is controlled by an
 * AI. It attacks all the sick cells. It also goes through mitosis, to make the
 * game harder.
 *
 * @author Melissa Wei
 * @author Period: 6
 * @author Assignment: APCS-Final-Project
 * @author Sources: n/a
 * @version May 27, 2016
 */
public class WhiteCell extends Cell {
    /**
     * The ticks used to keep track of time
     */
    private int ticks = 0;

    /**
     * Keeps track of time in order to split
     */
    private int splitTime;

    /**
     * Variable to increase x with each tick
     */
    private int vx = 0;

    /**
     * Variable to increase y with each tick
     */
    private int vy = 0;

    /**
     * Keeps track whether the cell is healing or not
     */
    private boolean healing;

    /**
     * Speed of cells
     */
    private final int speed;

    /**
     * Ticks needed for a random drift directions to break up cell movement
     */
    private int drift = 50;

    /**
     * The attack strength
     */
    private final int attack;

    /**
     * Whether current cell is being attacked
     */
    private boolean beingAttacked = false;

    /**
     * True if cell is following a Virus
     */
    private boolean tracking = false;

    /**
     * Vision radius of white cells
     */
    private int sightRadius;

    /**
     * Attacking radius of white cells
     */
    private int attackRadius;


    /**
     * Initializes the x coordinate, y coordinate, and health.
     *
     * @param x      the x coordinate
     * @param y      the y coordinate
     * @param health the initial health of the cell
     */
    WhiteCell(int x, int y, int health) {
        super(x, y, health);

        speed = 5;
        attack = 1;
        healing = false;
        drift = 50;
        sightRadius = 500;
        attackRadius = 80;
    }


    /**
     * Creates 2 cells from the current x position. the both are positions at
     * (int) (getX() + Math.random() * 67);
     *
     * @param w the arrayList of cells.
     */
    public void split(ArrayList<Cell> w) {
        int t1 = (int) (getX() + Math.random() * 67);
        int t2 = (int) (getY() + Math.random() * 67);

        w.add(new WhiteCell(t1, t2, 100));
        splitTime = 0;
    }


    /**
     * Draws white cells and their health bars to the canvas
     *
     * @param g       Graphics of the canvas
     * @param xOffset The offset of of graphics on x-axis
     * @param yOffset The offset of of graphics on x-axis
     */
    @Override
    public void draw(Graphics g, int xOffset, int yOffset) {
        super.draw(g, xOffset, yOffset);
        BufferedImage in;
        try {
            String currentString = WelcomeScreen.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            currentString = currentString.substring(1,currentString.indexOf("/out"))+"/Virus_Attack/src/images/pixelwhite.png";
            in = ImageIO.read(new File(currentString));
            g.drawImage(in, getX() - xOffset, getY() - yOffset, null);
        } catch (IOException e) {
            System.out.println("Cannot Read Image for whiteImage");
        }

    }


    /**
     * Produces antiviruses/antibodies that attack viruses
     */
    @Override
    public void produceUnit() {
        /*
         * The time at which the ticks generate
         */
        int generateAt = 250;
        if (ticks >= generateAt) {
            AntiVirusManager.addAnti(getX() + getRadius(), getY() + getRadius());
            ticks = 0;
        }
        ticks++;
    }


    /**
     * Sets the attacked variable of cell
     *
     * @param attacked whether cell is being attacked
     */
    public void setAttacked(boolean attacked) {
        beingAttacked = attacked;
    }

    /**
     * Gives the attacked variable of cell
     */
    public boolean isAttacked() {
        return beingAttacked;
    }


    /**
     * Gives the time state in the splitting process
     *
     * @return splitTime the current state of time
     */
    public int getTime() {
        return splitTime;
    }


    /**
     * Increments the split time of the cell
     */
    public void updateTime() {
        splitTime++;
    }


    /**
     * Sets a coordinate destination for cell.
     *
     * @param nx the new x to set to
     * @param ny the new y to set to
     */
    private void setCoordinate(int nx, int ny) {

        double hyp = Math.sqrt((nx - getX()) * (nx - getX()) + (ny - getY()) * (ny - getY()));
        double scale = speed / hyp;
        vx = (int) ((nx - getX()) * scale / 2.0);
        vy = (int) ((ny - getY()) * scale / 2.0);
    }


    /**
     * Updates the location of the cell to move towards destination
     */
    public void move() {
        setX(getX() + vx);
        setY(getY() + vy);
    }


    /**
     * This method finds Sick cells and Viruses and represents the white cells'
     * locating intelligence. It sets the cell to follow certain targets around,
     * and also includes drifting motions to break up white cell groups. Curing
     * sick cells are a priority, and then killing viruses.
     *
     * @param g       the Graphics component for the canvas
     * @param xOffset the x fix for the canvas
     * @param yOffset the y fix for the canvas
     */
    public void findElement(Graphics g, int xOffset, int yOffset) {
        boolean attacking = false;
        int closest = Integer.MAX_VALUE;
        healSickCells(g, xOffset, yOffset);

        for (Entry<Integer, VirusGroup> integerVirusGroupEntry : VirusGroupManager.virusGroupMap().entrySet()) {
            VirusGroup pair = integerVirusGroupEntry.getValue();
            int add;
            if (!healing || beingAttacked) {
                for (int i = 0; i < pair.size(); i += add) {
                    Virus v = pair.getVirus(i);
                    add = 1;
                    if (getDistance(v) <= sightRadius && getDistance(v) < closest) {
                        setCoordinate(v.getX(), v.getY());
                        tracking = true;
                    } else {

                        if ((int) (Math.random() * 1000) == 50) {
                            setCoordinate(v.getX(), v.getY());
                            tracking = true;
                        }
                    }

                    if (getDistance(v) <= attackRadius && (!healing || beingAttacked)) {
                        attacking = true;
                        v.reduceHealth(attack);
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
                    if (!attacking && tracking) {
                        int chance = (int) (Math.random() * 500);

                        if (chance == 50) {
                            setCoordinate(v.getX(), v.getY());

                            tracking = false;
                        }
                    } else if (!attacking) {
                        drift();

                    }

                }

            }
        }
        beingAttacked = false;

    }


    /**
     * Helper method for findElement; it finds and heals sick cells
     *
     * @param g       the Graphics component for the canvas
     * @param xOffset the x fix for the canvas
     * @param yOffset the y fix for the canvas
     */
    private void healSickCells(Graphics g, int xOffset, int yOffset) {
        int closestSick = Integer.MAX_VALUE;

        for (int j = 0; j < CellManager.sickValues.size(); j++) {
            Cell c = CellManager.sickValues.get(j);
            if (getDistance(c) <= sightRadius && getDistance(c) < closestSick) {
                setCoordinate(c.getX(), c.getY());
                healing = true;

            }
            if (getDistance(c) <= attackRadius) {
                c.increaseHealth(attack);
                g.setColor(Color.green);
                g.drawLine(getX() + getRadius() - xOffset,
                        getY() + getRadius() / 2 - yOffset,
                        c.getX() + c.getRadius() - xOffset,
                        c.getY() + c.getRadius() / 2 - yOffset);
                if (c.getHealth() >= 0) {
                    CellManager.convertCell(c);
                    healing = false;
                }
            }
        }
    }


    /**
     * Drifts the White cells for more random movements, helper method for
     * findElement
     */
    private void drift() {
        drift++;
        if (drift > 50) {
            int moveX = (int) (Math.random() * 2);
            if (moveX == 0) {
                setCoordinate(getX() + 50, getY() + 50);
            } else if (moveX == 1) {
                setCoordinate(getX() + 50, getY() - 50);
            } else if (moveX == 2) {
                setCoordinate(getX() - 50, getY() - 50);
            } else {
                setCoordinate(getX() - 50, getY() + 50);
            }
            drift = 0;
        }
    }

    //Testing purposes
    public int vx() {
        return vx;
    }

    public int vy() {
        return vy;
    }

    public void setvx(int vx) {
        this.vx = vx;
    }

    public void setvy(int vy) {
        this.vy = vy;
    }

    public void setTicks(int ticks) {
        this.ticks = ticks;
    }

    public String toString() {
        return "WhiteCell[x: " + getX() + " y: " + getY() + "radius: " + getRadius() + "health: " + getHealth() + "speed: " + speed
                + "attack: " + attack + "]";

    }


}