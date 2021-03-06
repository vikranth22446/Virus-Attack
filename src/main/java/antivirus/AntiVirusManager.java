package antivirus;

import java.awt.*;
import java.util.ArrayList;


/**
 * Contains all the AntiViruses and keeps track of their movements
 *
 * @author Melissa Wei
 * @author Period: 6
 * @author Assignment: APCS-Final-Project
 * @author Sources: n/a
 * @version May 28, 2016
 */
public class AntiVirusManager {
    /**
     * An ArrayList of main.antivirus.AntiVirus
     */
    public static ArrayList<AntiVirus> anti;


    /**
     * Constructs an ArrayList of AntiViruses
     */
    public AntiVirusManager() {
        anti = new ArrayList<>();
    }


    /**
     * Calls the draw method of all the AntiViruses
     *
     * @param g       the graphics of the Canvas
     * @param xOffset the xOffset of the window. This allows window to move.
     * @param yOffset the yOffset of the window. This allows window to move.
     */
    public void draw(Graphics g, int xOffset, int yOffset) {
        for (AntiVirus n : anti) {
            n.draw(g, xOffset, yOffset);
        }
    }


    /**
     * Calls the update Location of all the antiViruses
     *
     * @param g       the graphics of the Canvas
     * @param xOffset the xOffset of the window. This allows window to move.
     * @param yOffset the yOffset of the window. This allows window to move.
     */
    public void updateLocation(Graphics g, int xOffset, int yOffset) {
        for (AntiVirus av : anti) {
            av.update(g, xOffset, yOffset);
        }
    }


    /**
     * Adds an antivirus to the antivirus arrayList
     *
     * @param x the x position of the antivirus to add.
     * @param y the y position of the antivirus to add.
     */
    public static void addAnti(int x, int y) {
        anti.add(new AntiVirus(x, y));
    }

    /**
     * Returns a string with the antiVirus ArrayList
     *
     * @return a string with an main.antivirus.AntiVirus ArrayList
     */
    public String toString() {
        return "main.AntiVirusManagerTests[anti: " + anti.toString() + "]";

    }

}