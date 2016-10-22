package tests.helper;

import main.helper.ScoreBoard;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by vikranth on 10/21/2016.
 */
public class ScoreBoardTests {
    /**
     * main.helper.ScoreBoard Tests:
     * scoreBoardtoStringTest(): Checks if the toString is Not null
     * scoreBoardContructorTest(): Checks if the Constructor is right
     * everyTurnTest(): Checks if the right string is returned every turn
     * increaseWhiteDeaths(): Checks if the whiteCellDeath is increased
     * increaseRedCellsDead(): Checks if the redCellConquered is increased
     * getSeconds(): Checks if the right seconds is returned
     * getWhiteCellsKilled(): Checks if the right whiteCellsKilled is returned
     * getRedCellsConquered(): Checks if the right RedCellsConquered is returned
     * getTotalScore(): Checks if the right totalScore is returned
     *
     * @author Vikranth Srivatsa
     */
    @Test
    public void scoreBoardtoStringTest() {
        ScoreBoard scoreBoard = new ScoreBoard();
        assertNotNull("<<The fields are set wrong>>", scoreBoard.toString() != null);
    }

    @Test
    public void scoreBoardContructorTest() {
        ScoreBoard scoreBoard = new ScoreBoard();
        assertTrue("<<Initial time is 0>>", scoreBoard.getInitalTime() != 0);
    }

    @Test
    public void everyTurnTest() {
        ScoreBoard scoreBoard = new ScoreBoard();
        String changeInTime = scoreBoard.everTurn();
        assertTrue("<<It does not check change in time right>>", "00:0".equalsIgnoreCase(changeInTime));
    }

    @Test
    public void increaseWhiteDeaths() {
        ScoreBoard scoreBoard = new ScoreBoard();
        scoreBoard.increaseWhiteDeaths();
        assertTrue("<<the number white cells is not 1>>", scoreBoard.getWhiteCellsKilled() == 1);
    }

    @Test
    public void increaseRedCellsDead() {
        ScoreBoard scoreBoard = new ScoreBoard();
        scoreBoard.increaseRedCellsDead();
        assertTrue("<<the number white cells is not 1>>", scoreBoard.getRedCellsConquered() == 1);

    }

    @Test
    public void getSeconds() {
        ScoreBoard scoreBoard = new ScoreBoard();
        assertTrue("<<Initial time is 0>>", scoreBoard.getTime().equalsIgnoreCase("0:0"));
    }

    @Test
    public void getWhiteCellsKilled() {
        ScoreBoard scoreBoard = new ScoreBoard();
        scoreBoard.increaseWhiteDeaths();
        assertTrue("<<The Red cells is not returned right>>", scoreBoard.getWhiteCellsKilled() == 1);
    }

    @Test
    public void getRedCellsConquered() {
        ScoreBoard scoreBoard = new ScoreBoard();
        scoreBoard.increaseRedCellsDead();
        assertTrue("<<The Red cells is not returned right>>", scoreBoard.getRedCellsConquered() == 1);
    }

    @Test
    public void getTotalScore() {
        ScoreBoard scoreBoard = new ScoreBoard();
        scoreBoard.increaseWhiteDeaths();
        assertTrue("<<The score is not incremented right>>", scoreBoard.getTotalScore() == 10);
    }
}
