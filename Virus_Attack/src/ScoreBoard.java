import java.text.SimpleDateFormat;
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

    boolean changed = false;

    public String everTurn() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("mm");
        SimpleDateFormat seconds = new SimpleDateFormat("ss");
        int newTime = Integer.parseInt(sdf.format(cal.getTime())) * 60 + Integer.parseInt(seconds.format(cal.getTime()));
        time = newTime - initalTime - minutes * 60;
        if (time == 60 && !changed) {
            minutes++;
            time = time % 60;
            reduceScore();
            changed = true;
        }
        if (time == 30 && !changed) {
            reduceScore();
            changed = true;
        }
        if (time == 31 || time == 1) {
            changed = false;
        }
        if ((minutes + "").length() == 1) return 0 + "" + minutes + ":" + time;
        else return minutes + ":" + time;
    }

    private void reduceScore() {
        System.out.println(totalScore);
        totalScore = totalScore - 1;
        System.out.println(totalScore);
    }


    public void increaseWhiteDeaths() {
        whiteCellsKilled++;
        totalScore += 10;
    }

    public void increaseRedCellsDead() {
        redCellsConquered++;
        totalScore += 5;
    }

    public String getTime() {
        return minutes + ":" + time;
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
