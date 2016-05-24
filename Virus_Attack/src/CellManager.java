
import java.awt.*;
import java.util.ArrayList;

public class CellManager {
    public static ArrayList<Cell> redValues;
    public static ArrayList<Cell> whiteValues;
    public static ArrayList<Cell> sickValues;

    private int[][] whitePoints = {{1000, 400}, {900, 700},{400, 400}};
    private ArrayList<Point> redPoints = new ArrayList<>();

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
            cell = new WhiteCell(whitePoints[i][0], whitePoints[i][1], 500, i);
            addWhiteCell(cell);
        }
        for (int i = 0; i < 15; i++) {
            redPoints.add(new Point((int)(Math.random()*1000),(int)(Math.random()*1000)));
        }
        for (int i = 0; i < redPoints.size(); i++) {

            Point point = redPoints.get(i);
            Cell cell = new RedCell(point.getX(),point.getY(), 200, i);

            addRedCell(cell);
        }
        for (int i = 0; i < sickPoints.length; i++) {
            Cell cell = new SickCell(sickPoints[i][0], sickPoints[i][1], -100, i);

            addSickCell(cell);
        }
    }
    public void mitosis()
    {
     for (int i = 0 ; i < whiteValues.size(); i++)   
     {
        WhiteCell w = (WhiteCell)whiteValues.get( i );
        w.updateTime();
        if(w.getTime() > 150 && whiteValues.size()<20)
        {
            w.split( whiteValues );
        }
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
        else if (c instanceof SickCell)
        {
            sickValues.remove(c);
            redValues.add(new RedCell(c.getX(), c.getY(), 100, 0));
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
    public void moveWhiteCells(Graphics g, int xOffset, int yOffset) {
        for (int i = 0; i < whiteValues.size(); i++)
        {
            WhiteCell w = (WhiteCell)whiteValues.get( i );
            w.findVirus( g, xOffset, yOffset );
            w.move();
        }
    }

    public void toDraw(int xOffset, int yOffset, Graphics g){
    	ArrayList<Cell> toDraw = new ArrayList<Cell>();
    	
    	for(Cell c : redValues){
    		if(inRange(c.getX(), c.getY() , xOffset, yOffset)){
    			toDraw.add(c);
    		}
    	}
    	for(Cell c: whiteValues){
    		if(inRange(c.getX(), c.getY() , xOffset, yOffset)){
    			toDraw.add(c);
    		}
    	}
    	for(Cell c: sickValues){
    		if(inRange(c.getX(), c.getY() , xOffset, yOffset)){
    			toDraw.add(c);
    		}
    	}
    	
    	draw(g, toDraw, xOffset, yOffset);
    	
    }
    
    public boolean inRange(int Cellx, int Celly, int xOffset, int yOffset ){
    	return Cellx >= xOffset && Cellx <= xOffset + World.WIDTH*World.SCALE 
    			&& Celly >= yOffset && Celly <= yOffset + World.HEIGHT*World.SCALE; 	
    }


    public void draw(Graphics g, ArrayList<Cell> toDraw, int xOffset, int yOffset) {
        mitosis();
        moveWhiteCells(g, xOffset,  yOffset);
        //HealthBar healthBar = new HealthBar();

        for(Cell c : toDraw){
        	c.draw(g, xOffset, yOffset);
        //	healthBar.draw(g, c);
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