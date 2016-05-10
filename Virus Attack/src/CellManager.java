import java.awt.*;
import java.util.ArrayList;


public class CellManager {
    private ArrayList<Cell> values;
    //using id numbers as positions
    public CellManager() {
        values = new ArrayList<>();
    }
    public void addSetOfValues(ArrayList<Cell> arrayList){
        values.addAll(arrayList);
    }

    public void addCell(Cell x){
        values.add(x);
    }
    public void removeCell(int id){
        values.remove(id);
    }
    public void updateNumViruses(){
        for (Cell x: values) {
            x.produceValues();
        }
    }
    public ArrayList<Cell> getValues() {
        return values;
    }

    public void updateDrawing(Canvas canvas) {
        for(int i =0; i < values.size();i++){
            values.get(i).draw(canvas,i);
        }
    }
}
