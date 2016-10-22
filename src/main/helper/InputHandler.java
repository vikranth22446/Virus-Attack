package main.helper;

import main.gui.World;
import main.virus.VirusGroupManager;

import java.awt.event.*;


/**
 * Handles all the inputs, key inputs, mouse inputs, and mouse movement when shifting the screen.
 *
 * @author Alex M
 */
public class InputHandler implements MouseListener, MouseMotionListener, KeyListener {

    /**
     * Holds the virus group manager object created on start
     */
    private final VirusGroupManager vgm;

    /**
     * the portion of the map to render
     */
    private static int xOffset, yOffset;

    /**
     * variable to hold the amount the screen shifted
     */
    private int xChange, yChange;

    /**
     * portion of the screen that can be moved on to, in order to move the screen
     */
    private final int allow;

    /**
     * amount to move the screen by per tick, ticks 25 times per second
     */
    private final int move;

    /**
     * whether the merge function is to be used
     */
    private boolean merge = false;

    /**
     * creates all the variables
     *
     * @param vgm from the world to access vgm actions
     */
    public InputHandler(VirusGroupManager vgm) {
        this.vgm = vgm;

        xOffset = 0;
        yOffset = 0;

        xChange = 0;
        yChange = 0;
        allow = 50;

        move = 3;
    }

    /**
     * the getter method for the screen x offset
     *
     * @return the x offset
     */
    public static int getXOffset() {
        return xOffset;
    }

    /**
     * the getter method for the screen y offset
     *
     * @return the y offset
     */
    public static int getYOffset() {
        return yOffset;
    }


    /**
     * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
     */
    public void keyPressed(KeyEvent e) {
        // S to split
        if (e.getKeyCode() == KeyEvent.VK_S) {
            if (vgm.groupNum() <= 6) {
                vgm.split();
            }
        }
        // A to Merge, the click group to merge
        if (e.getKeyCode() == KeyEvent.VK_A) {
            merge = true;
        }

        //Different Groups
        if (e.getKeyCode() == KeyEvent.VK_1) {
            if (vgm.hasKey(1)) {
                if (merge) {
                    vgm.merge(1);
                    merge = false;
                } else vgm.changeCurrent(1);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_2) {
            if (vgm.hasKey(2)) {
                if (merge) {
                    vgm.merge(2);
                    merge = false;
                } else vgm.changeCurrent(2);
            }

        }
        if (e.getKeyCode() == KeyEvent.VK_3 || e.getKeyCode() == KeyEvent.VK_4) {
            if (vgm.hasKey(3)) {
                if (merge) {
                    vgm.merge(3);
                    merge = false;
                } else vgm.changeCurrent(3);
            }

        }
        if (e.getKeyCode() == KeyEvent.VK_5) {
            if (vgm.hasKey(5)) {
                if (merge) {
                    vgm.merge(5);
                    merge = false;
                } else vgm.changeCurrent(5);
            }

        }
        if (e.getKeyCode() == KeyEvent.VK_6) {
            if (vgm.hasKey(6)) {
                if (merge) {
                    vgm.merge(6);
                    merge = false;
                } else vgm.changeCurrent(6);
            }
        }
    }


    /**
     * moves the screen
     *
     * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
     */
    public void mouseMoved(MouseEvent e) {
        int x = e.getX() + xOffset;
        int y = e.getY() + yOffset;

        if (x <= (xOffset + allow) && xOffset > 0) {
            xChange -= move;
        } else if (x >= (xOffset + World.getWidth - allow) && xOffset < World.GAME_WIDTH) {
            xChange += move;
        } else if (y <= (yOffset + allow) && yOffset > 0) {
            yChange -= move;
        } else if (y >= (yOffset + World.getHeight - allow) && yOffset < World.GAME_HEIGHT) {
            yChange += move;
        }

        xOffset += xChange;
        yOffset += yChange;
        xChange = 0;
        yChange = 0;
    }


    /**
     * selects new location for viruses to move to
     *
     * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
     */
    public void mousePressed(MouseEvent e) {
        vgm.updateCoordinate(e.getX() + xOffset, e.getY() + yOffset);

    }


    public void mouseDragged(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void mouseClicked(MouseEvent arg0) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

}