import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class ScoreBoard {
    private int totalScore = 0;
    private int time;
    private int minutes;
    private int initalTime;
    private int whiteCellsKilled;
    private int redCellsConquered;

    public ScoreBoard() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("mm");
        SimpleDateFormat seconds = new SimpleDateFormat("ss");
        initalTime = Integer.parseInt(sdf.format(cal.getTime())) * 60 + Integer.parseInt(seconds.format(cal.getTime()));
    }

    public String everTurn() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("mm");
        SimpleDateFormat seconds = new SimpleDateFormat("ss");
        int newTime = Integer.parseInt(sdf.format(cal.getTime())) * 60 + Integer.parseInt(seconds.format(cal.getTime()));
        time = newTime - initalTime - minutes * 60;
        if (time == 60) {
            minutes++;
            time = time % 60;
        }
        if ((minutes + "").length() == 1) return 0 + "" + minutes + ":" + time;
        else return minutes + ":" + time;
    }

    public String isGameOver(VirusGroupManager virusGroupManager,CellManager cellManager) {
        ArrayList<Integer> sizes = cellManager.returnSizes();
        int size = 0;
          for (int n:virusGroupManager.getKeys()){
              size+=virusGroupManager.getGroups().get(n).getAllViruses().size();
          }
        if(size==0){
            return "lost";
        }
        //No Sick Cells Left
        if(sizes.get(0)==0){
            return "lost";
        }
        //No Red Cells or White Cells Left
        else if(sizes.get(1)==0 || sizes.get(2)==0){
            return "won";
        }
        return "Neither";
    }

    public void increaseWhiteDeaths() {
        whiteCellsKilled++;
        totalScore+=10;
    }

    public void increaseRedCellsDead() {
        redCellsConquered++;
        totalScore+=5;
    }

    public String getTime() {
        return minutes+":"+time;
    }

    public int getWhiteCellsKilled() {
        return whiteCellsKilled;
    }

    public int getRedCellsConquered() {
        return redCellsConquered;
    }

    public int getTotalScore() {
        return totalScore;
    }
}
