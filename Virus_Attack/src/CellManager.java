
import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * CellManager contains redValues,whiteValues, and sickValues.
 */
public class CellManager {
    /**
     * An ArrayList of all the redCells
     */
    public static ArrayList<Cell> redValues;
    /**
     * An ArrayList of the whiteCells.
     */
    public static ArrayList<Cell> whiteValues;
    /**
     * An ArrayList of all the sickCells.
     */
    public static ArrayList<Cell> sickValues;
    /**
     * The list of white point used when constructing later.
     */
    private int[][] whitePoints = {{1000, 400}, {900, 700}, {400, 400}};
    /**
     * An ArrayList of redPoints. This is an empty aerialist to be added later
     */
    private ArrayList<Point> redPoints = new ArrayList<>();
    /**
     * A set of sick points used to initialize later.
     */
    private int[][] sickPoints = {
            {100, 300}, {500, 100}, {300, 600}
    };

    /**
     * constructs the 3 arraylist: redValues,whiteValues, and SickValues.
     */
    public CellManager() {
        redValues = new ArrayList<>();
        whiteValues = new ArrayList<>();
        sickValues = new ArrayList<>();

    }

    /**
     * gets the value from whitePoints and redPoints and uses them to create red Cells
     * White Cells. Then randomly redCells are put all over the map.
     */
    public void createCellsInPositions() {
        for (int i = 0; i < whitePoints.length; i++) {
            Cell cell;
            cell = new WhiteCell(whitePoints[i][0], whitePoints[i][1], 500);
            addWhiteCell(cell);
        }
        for (int i = 0; i < 15; i++) {
            redPoints.add(new Point((int) (Math.random() * 1000), (int) (Math.random() * 1000)));
        }
        for (int i = 0; i < redPoints.size(); i++) {

            Point point = redPoints.get(i);
            Cell cell = new RedCell(point.getX(), point.getY(), 200);

            addRedCell(cell);
        }
        for (int i = 0; i < sickPoints.length; i++) {
            Cell cell = new SickCell(sickPoints[i][0], sickPoints[i][1], -100);

            addSickCell(cell);
        }
    }

    /**
     * Every certain seconds the white cells will split.
     */
    public void mitosis() {
        for (int i = 0; i < whiteValues.size(); i++) {
            WhiteCell w = (WhiteCell) whiteValues.get(i);
            w.updateTime();
            if (w.getTime() > 150 && whiteValues.size() < 20) {
                w.split(whiteValues);
            }
        }
    }

    /**
     * Adds the cell to the sickValues ArrayList
     *
     * @param cell the cell to add
     */
    private void addSickCell(Cell cell) {
        sickValues.add(cell);
    }

    /**
     * Adds the cell to the redValues ArrayList
     *
     * @param x the cell to add
     */
    public void addRedCell(Cell x) {
        redValues.add(x);
    }

    /**
     * Adds the cell to the whiteValues ArrayList
     *
     * @param x the cell to add
     */
    public void addWhiteCell(Cell x) {
        whiteValues.add(x);
    }

    /**
     * Converts the cell depending on what type of cell it is
     * RedCell
     * -converts to sick
     * WhiteValeus
     * -removes
     * SickCell
     * -converts to red Cell
     *
     * @param c the cell to convert
     */
    public static void convertCell(Cell c) {
        if (c instanceof RedCell) {
            redValues.remove(c);
            sickValues.add(new SickCell(c.getX(), c.getY(), -100));
        } else if (c instanceof WhiteCell) {
            whiteValues.remove(c);
        } else if (c instanceof SickCell) {
            sickValues.remove(c);
            redValues.add(new RedCell(c.getX(), c.getY(), 100));
        }

    }

    /**
     * Removes a certain
     *
     * @param id the id to remove.
     */
    public static void removeCell(int id) {
        whiteValues.remove(id);
    }

    /**
     * Moves the white cells to a different position
     *
     * @param g       the graphics of the window
     * @param xOffset the xOffset of the window. This is used to move the Window,
     * @param yOffset the yOffset of the window. This is used to move the Window,
     */
    public void moveWhiteCells(Graphics g, int xOffset, int yOffset) {
        for (int i = 0; i < whiteValues.size(); i++) {
            WhiteCell w = (WhiteCell) whiteValues.get(i);
            w.findVirus(g, xOffset, yOffset);
            w.move();
        }
    }

    /**
     * Calls the draw method of the red,white,sick values.
     * The calls the draw method to draw all the values.
     *
     * @param xOffset the xOffset of the window. This allows window to move.
     * @param yOffset the yOffset of the window. This allows window to move.
     * @param g       the graphics of canvas
     */
    public void toDraw(int xOffset, int yOffset, Graphics g) {
        ArrayList<Cell> toDraw = redValues.stream().filter(c -> inRange(c.getX(), c.getY(), xOffset, yOffset)).collect(Collectors.toCollection(ArrayList::new));
        toDraw.addAll(whiteValues.stream().filter(c -> inRange(c.getX(), c.getY(), xOffset, yOffset)).collect(Collectors.toList()));
        toDraw.addAll(sickValues.stream().filter(c -> inRange(c.getX(), c.getY(), xOffset, yOffset)).collect(Collectors.toList()));

        draw(g, toDraw, xOffset, yOffset);

    }

    /**
     * Checks if the values is within the world
     *
     * @param Cellx   the cell's x position
     * @param Celly   the cell's y position
     * @param xOffset the xOffset of the window. This allows window to move.
     * @param yOffset the yOffset of the window. This allows window to move.
     * @return a boolean of if it is in range.
     */
    public boolean inRange(int Cellx, int Celly, int xOffset, int yOffset) {
        return Cellx >= xOffset && Cellx <= xOffset + World.WIDTH * World.SCALE
                && Celly >= yOffset && Celly <= yOffset + World.HEIGHT * World.SCALE;
    }

    /**
     * Iteartes through the toDraw method and calls the draw method.
     *
     * @param g       the graphics passed through the canvas
     * @param toDraw  the arraylist of cells to draw.
     * @param xOffset the xOffset of the window. This allows window to move.
     * @param yOffset the yOffset of the window. This allows window to move.
     */
    public void draw(Graphics g, ArrayList<Cell> toDraw, int xOffset, int yOffset) {
        mitosis();
        moveWhiteCells(g, xOffset, yOffset);
        for (Cell c : toDraw) {
            c.draw(g, xOffset, yOffset);
        }
    }

    /**
     * Calls the produce for all the white Values and sick Values.
     */
    public void produce() {
        whiteValues.forEach(Cell::produceUnit);
        sickValues.forEach(Cell::produceUnit);
    }
}