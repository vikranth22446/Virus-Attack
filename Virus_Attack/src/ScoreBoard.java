import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by vikranth on 5/29/2016.
 */
public class ScoreBoard {
    private int totalScore;
    private int time;
    private int minutes;
    private int initalTime;
    private int whiteCellsKilled;
    private int redCellsConquered;

    public ScoreBoard() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("ss");
        System.out.println(sdf.format(cal.getTime()));
        String orginal = sdf.format(cal.getTime());
        initalTime = Integer.parseInt(orginal);
    }

    public String everTurn() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("ss");
        int newTime = Integer.parseInt(sdf.format(cal.getTime()));
        time = newTime - initalTime;
        if(time >=60){
            minutes++;
            time = time-60;
        }
        System.out.println(minutes +" at "+time);
        return minutes+":"+time;
    }
}
