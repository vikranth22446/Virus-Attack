import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Keeps track of the seconds elapsed, and the scores: totalScore,whiteCellsKilled,and redCellsConquered.
 */
public class ScoreBoard {
    /**
     * The total score the game
     */
    private int totalScore = 0;
    /**
     * The total time seconds portion
     */
    private int seconds;
    /**
     * The total time minutes portion
     */
    private int minutes;
    /**
     * The time the game started
     */
    private int initalTime;
    /**
     * The total white Cells killed
     */
    private int whiteCellsKilled;
    /**
     * The total redCellsConquered
     */
    private int redCellsConquered;

    /**
     * Uses the calender and SimpleDateFormat and get's the current time  mmss in seconds.
     */
    public ScoreBoard() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("mm");
        SimpleDateFormat seconds = new SimpleDateFormat("ss");
        initalTime = Integer.parseInt(sdf.format(cal.getTime())) * 60 + Integer.parseInt(seconds.format(cal.getTime()));
    }

    /**
     * In order to subtract one point every 30 seconds this variable is used to see if it has been subtracted
     */
    private boolean changed = false;

    /**
     * Gets the new time mmss in seconds and feeds the change and puts that in seconds.
     * If the seconds is 60 and it is not changed, increase the minutes,then %60 the secondes
     * The call reduces score, and set changed to true
     * If the seconds is 30 and it is not changed
     * call reduces score() and set changed to true
     * if the seconds is 31 or 1 set the changed to false.
     * Then return a string that has minutes:seconds
     *
     * @return a string with minutes:seconds
     */
    public String everTurn() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("mm");
        SimpleDateFormat seconds = new SimpleDateFormat("ss");
        int newTime = Integer.parseInt(sdf.format(cal.getTime())) * 60 + Integer.parseInt(seconds.format(cal.getTime()));
        this.seconds = newTime - initalTime - minutes * 60;
        /**
         * If it is 60 seconds then minutes has to increase and score has to reduce         */
        if (this.seconds == 60 && !changed) {
            minutes++;
            this.seconds = this.seconds % 60;
            reduceScore();
            changed = true;
        }
        /**
         * Every 30 seconds score has to reduce thus this if statement does that
         */
        if (this.seconds == 30 && !changed) {
            reduceScore();
            changed = true;
        }
        /**
         * If the score already has been decremented, this allows for changed set to false so that
         * in the next 30 seconds reduceScore can again be called
         */
        if (this.seconds == 31 || this.seconds == 1) {
            changed = false;
        }
        /**
         * If the minutes is single digits add a 0
         */
        if ((minutes + "").length() == 1) return 0 + "" + minutes + ":" + this.seconds;
        /**
         * Returns minutes:seconds
         */
        else return minutes + ":" + this.seconds;
    }

    /**
     * Reduces totalScore by 1
     */
    private void reduceScore() {
        totalScore = totalScore - 1;
    }

    /**
     * Increase white Deaths by 1 and increment total score by 10
     */
    public void increaseWhiteDeaths() {
        whiteCellsKilled++;
        totalScore += 10;
    }

    /**
     * Increase red cells conquered by 1 and increment total score by 5
     */
    public void increaseRedCellsDead() {
        redCellsConquered++;
        totalScore += 5;
    }

    /**
     * Used in test to return initial time
     *
     * @return the initialTime
     */
    public int getInitalTime() {
        return initalTime;
    }

    /**
     * Returns a string with minutes:seconds
     *
     * @return a string with minutes:seconds
     */
    public String getTime() {
        return minutes + ":" + seconds;
    }

    /**
     * Returns the whiteCellsKilled field
     *
     * @return the whiteCellsKilled field
     */
    public int getWhiteCellsKilled() {
        return whiteCellsKilled;
    }

    /**
     * Returns the redCellsConquered field
     *
     * @return the redCellsConquered field
     */
    public int getRedCellsConquered() {
        return redCellsConquered;
    }

    /**
     * Returns the redCellsConquered field
     *
     * @return the redCellsConquered field
     */
    public int getTotalScore() {
        return totalScore;
    }

    /**
     * Returns a String with all the fields
     *
     * @return a string with all the fields
     */
    @Override
    public String toString() {
        return "ScoreBoard{" +
                "totalScore=" + totalScore +
                ", seconds=" + seconds +
                ", minutes=" + minutes +
                ", initalTime=" + initalTime +
                ", whiteCellsKilled=" + whiteCellsKilled +
                ", redCellsConquered=" + redCellsConquered +
                ", changed=" + changed +
                '}';
    }
}
