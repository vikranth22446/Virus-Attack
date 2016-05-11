package v2;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;


public class CellManager {
    public static ArrayList<Cell> redValues;
    public static ArrayList<Cell> whiteValues;


    private int [][] setOfValues = {
            {100,200},{300,400},{400,700},{600,800}
    };
    private int [][] set2 = {
            {150,250},{350,450},{450,750},{650,850}
    };
    //using id numbers as positions
    public CellManager() {
        redValues = new ArrayList<>();
        whiteValues = new ArrayList<>();

    }
    public void createCellsInPositions()
    {
        for (int i = 0; i <setOfValues.length; i++) {
            Cell cell;
            cell = new RedCell(setOfValues[i][0],setOfValues[i][1],100,i);
            addRedCell(cell);
        }
        for (int i = 0; i <set2.length; i++) {
            Cell cell=new WhiteCell(set2[i][0],set2[i][1],100,i);

            addWhiteCell(cell);
        }
    }
    public void addSetOfValues(ArrayList<Cell> arrayList){
        redValues.addAll(arrayList);
    }

    public void addRedCell(Cell x)
    {
        redValues.add(x);
    }
    public void addWhiteCell (Cell x)
    {
        whiteValues.add(x);
    }
    public static void convertSick (Cell x)
    {
        if (x instanceof RedCell)
        {
            redValues.set( redValues.indexOf( x ) , new SickCell(x.getX(), x.getY(), -10, 0) );

        }
        else if (x instanceof WhiteCell)
        {
            whiteValues.remove(x);
        }

    }
    public static void removeCell(int id){
        whiteValues.remove(id);
    }
    
    
    public void updateViruses(VirusGroupManager vgm, Canvas c){
        for (int i =0 ; i < redValues.size(); i++)
        {
            redValues.get( i ).updateViruses(vgm.currentGroup());

            if (redValues.get( i ).getHealth() <=0)
            {
                convertSick(redValues.get( i ));

            }
        }
        for (int i =0 ; i < whiteValues.size(); i++)
        {
            whiteValues.get( i ).updateViruses(vgm.currentGroup());

            if (whiteValues.get( i ).getHealth() <=0)
            {
                WhiteCell w = (WhiteCell)whiteValues.get( i );
                convertSick(whiteValues.get( i ));
                w.die(c);

            }
        }


    }
    public ArrayList<Cell> getValues() {
        return redValues;
    }

    public void updateDrawing(Canvas canvas) {
        for(int i =0; i < redValues.size();i++){
            redValues.get(i).draw(canvas,i);
        }
        int i = 0;
        for (Cell c: whiteValues)
        {
            c.draw( canvas, i++ );
        }
    }
}