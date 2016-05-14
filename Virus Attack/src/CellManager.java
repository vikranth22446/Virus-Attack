
import java.awt.*;
import java.util.ArrayList;

public class CellManager {
    public static ArrayList<Cell> redValues;
    public static ArrayList<Cell> whiteValues;
    public static ArrayList<Cell> sickValues;

    private int[][] whitePoints = {{1000, 400}, {900, 700},
            {1400, 600}};
    ArrayList<Point> redPoints = new ArrayList<>();

    private int[][] sickPoints = {
            {100, 300}, {500, 100}, {300, 600}
    };

    // using id numbers as positions
    public CellManager() {
        redValues = new ArrayList<>();
        whiteValues = new ArrayList<>();
        sickValues = new ArrayList<>();

    }

    public void createCellsInPositions() {
        for (int i = 0; i < whitePoints.length; i++) {
            Cell cell;
            cell = new WhiteCell(whitePoints[i][0], whitePoints[i][1], 100, i);
            addWhiteCell(cell);
        }
        for (int i = 0; i < 15; i++) {
            redPoints.add(new Point((int)(Math.random()*1000),(int)(Math.random()*1000)));
        }
        for (int i = 0; i < redPoints.size(); i++) {

            Point point = redPoints.get(i);
            Cell cell = new RedCell(point.getX(),point.getY(), 100, i);

            addRedCell(cell);
        }
        for (int i = 0; i < sickPoints.length; i++) {
            Cell cell = new SickCell(sickPoints[i][0], sickPoints[i][1], -100, i);

            addSickCell(cell);
        }
    }

    private void addSickCell(Cell cell) {
        sickValues.add(cell);
    }

    public void addSetOfValues(ArrayList<Cell> arrayList) {
        redValues.addAll(arrayList);
    }

    public void addRedCell(Cell x) {
        redValues.add(x);
    }

    public void addWhiteCell(Cell x) {
        whiteValues.add(x);
    }

    public static void convertSick(Cell c) {
        if (c instanceof RedCell) {
            // redValues.set( redValues.indexOf( x ) , new SickCell(x.getX(),
            // x.getY(), -10, 0) );
            redValues.remove(c);
            sickValues.add(new SickCell(c.getX(), c.getY(), -100, 0));


        } else if (c instanceof WhiteCell) {
            whiteValues.remove(c);
        }

    }

    public static void removeCell(int id) {
        whiteValues.remove(id);
    }

//	public void updateViruses(VirusGroupManager vgm, Canvas c) {
//		for (int i = 0; i < redValues.size(); i++) {
//			redValues.get(i).updateViruses(vgm.currentGroup());
//
//			if (redValues.get(i).getHealth() <= 0) {
//				convertSick(redValues.get(i));
//
//			}
//		}
//		for (int i = 0; i < whiteValues.size(); i++) {
//			whiteValues.get(i).updateViruses(vgm.currentGroup());
//
//			if (whiteValues.get(i).getHealth() <= 0) {
//				WhiteCell w = (WhiteCell) whiteValues.get(i);
//				convertSick(whiteValues.get(i));
//				w.die(c);
//
//			}
//		}
//	}


    public ArrayList<Cell> getValues() {
        return redValues;
    }

    public void draw(Canvas canvas) {
        HealthBar healthBar = new HealthBar();
        for (Cell c : redValues) {
            c.draw(canvas);
            healthBar.draw(canvas, c);
        }
        for (Cell c : whiteValues) {
            c.draw(canvas);
            healthBar.draw(canvas, c);
        }
        for (Cell c : sickValues) {
            c.draw(canvas);
            healthBar.draw(canvas, c);
        }
    }

    public void produce() {
        for (Cell c : whiteValues) {
            c.produceUnit();
        }
        for (Cell c : sickValues) {
            c.produceUnit();
        }
    }
}