
import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * CellManager contains redValues,whiteValues, and sickValues.
 */
class CellManager {
    /**
     * An ArrayList of all the redCells
     */
    static ArrayList<Cell> redValues;
    /**
     * An ArrayList of the whiteCells.
     */
    static ArrayList<Cell> whiteValues;
    /**
     * An ArrayList of all the sickCells.
     */
    static ArrayList<Cell> sickValues;
    /**
     * The list of white point used when constructing later.
     */
    private final int[][] whitePoints = {
            {1000, 400},
            {900, 700},
            {400, 400}};
    /**
     * An ArrayList of redPoints. This is an empty aerialist to be added later
     */
    private final ArrayList<Point> redPoints = new ArrayList<>();
    /**
     * A set of sick points used to initialize later.
     */
    private final int[][] sickPoints = {
            {100, 300}, {500, 100}, {300, 600}
    };

    /**
     * constructs the 3 ArrayList: redValues,whiteValues, and SickValues.
     */
    CellManager() {
        redValues = new ArrayList<>();
        whiteValues = new ArrayList<>();
        sickValues = new ArrayList<>();

    }

    /**
     * gets the value from whitePoints and redPoints and uses them to create red Cells
     * White Cells. Then randomly redCells are put all over the map.
     */
    void createCellsInPositions() {
        for (int[] whitePoint : whitePoints) {
            Cell cell;
            cell = new WhiteCell(whitePoint[0], whitePoint[1], 500);
            addWhiteCell(cell);
        }
        for (int i = 0; i < 15; i++) {
            redPoints.add(new Point((int) (Math.random() * 1000), (int) (Math.random() * 1000)));
        }
        for (Point point : redPoints) {

            Cell cell = new RedCell((int) point.getX(), (int) point.getY(), 200);

            addRedCell(cell);
        }
        for (int[] sickPoint : sickPoints) {
            Cell cell = new SickCell(sickPoint[0], sickPoint[1], -100);

            addSickCell(cell);
        }
    }

    /**
     * Every certain seconds the white cells will split.
     */
    private void mitosis() {
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
    private void addRedCell(Cell x) {
        redValues.add(x);
    }

    /**
     * Adds the cell to the whiteValues ArrayList
     *
     * @param x the cell to add
     */
    private void addWhiteCell(Cell x) {
        whiteValues.add(x);
    }

    /**
     * Converts the cell depending on what type of cell it is
     * RedCell
     * -converts to sick
     * White Values
     * -removes
     * SickCell
     * -converts to red Cell
     *
     * @param c the cell to convert
     */
    static void convertCell(Cell c) {
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
    static void removeCell(int id) {
        whiteValues.remove(id);
    }

    /**
     * Moves the white cells to a different position
     *
     * @param g       the graphics of the window
     * @param xOffset the xOffset of the window. This is used to move the Window,
     * @param yOffset the yOffset of the window. This is used to move the Window,
     */
    private void moveWhiteCells(Graphics g, int xOffset, int yOffset) {
        for (Cell whiteValue : whiteValues) {
            WhiteCell w = (WhiteCell) whiteValue;
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
    void toDraw(int xOffset, int yOffset, Graphics g) {
        ArrayList<Cell> toDraw = redValues.stream().filter(c -> inRange(c.getX(), c.getY(), xOffset, yOffset)).collect(Collectors.toCollection(ArrayList::new));
        toDraw.addAll(whiteValues.stream().filter(c -> inRange(c.getX(), c.getY(), xOffset, yOffset)).collect(Collectors.toList()));
        toDraw.addAll(sickValues.stream().filter(c -> inRange(c.getX(), c.getY(), xOffset, yOffset)).collect(Collectors.toList()));

        draw(g, toDraw, xOffset, yOffset);
    }

    /**
     * Checks if the values is within the world
     *
     * @param x       the cell's x position
     * @param y       the cell's y position
     * @param xOffset the xOffset of the window. This allows window to move.
     * @param yOffset the yOffset of the window. This allows window to move.
     * @return a boolean of if it is in range.
     */
    private boolean inRange(int x, int y, int xOffset, int yOffset) {
        return x >= xOffset && x <= xOffset + World.WIDTH * World.SCALE
                && y >= yOffset && y <= yOffset + World.HEIGHT * World.SCALE;
    }

    /**
     * Iterates through the toDraw method and calls the draw method.
     *
     * @param g       the graphics passed through the canvas
     * @param toDraw  the ArrayList of cells to draw.
     * @param xOffset the xOffset of the window. This allows window to move.
     * @param yOffset the yOffset of the window. This allows window to move.
     */
    private void draw(Graphics g, ArrayList<Cell> toDraw, int xOffset, int yOffset) {
        mitosis();
        moveWhiteCells(g, xOffset, yOffset);
        for (Cell c : toDraw) {
            c.draw(g, xOffset, yOffset);
        }
    }

    /**
     * Calls the produce for all the white Values and sick Values.
     */
    void produce() {
        whiteValues.forEach(Cell::produceUnit);
        sickValues.forEach(Cell::produceUnit);
    }
}