import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

import static org.easymock.EasyMock.createMock;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * This class tests all the classes of the project.
 * The are tested in the order
 * Virus
 * VirusGroupManager
 *
 * @author Vikrnath Srivatsa, Alex Minooka, Melissa Wei
 */
public class JUnit {
    /**
     * Virus JUnit Test Methods:
     * virusSetUp()
     * virusConstructor()
     * virusToString()
     * virusMoving()
     * virusUpdate()
     * virusReduceHealth()
     * virusIdleMovement()
     *
     * @author Alex M
     */
    private int xCoord;

    private int yCoord;

    private int groupNum = 3;

    private Graphics g;

    @Before
    public void virusSetUp() {
        xCoord = 123;
        yCoord = 123;
        groupNum = 3;
    }

    @Test
    public void virusConstructor() {
        Virus v = new Virus(xCoord, yCoord);
        String s = v.toString();

        assertTrue("<<Invalid Virus Constructor>>",
                s.contains("Virus[") && s.contains("x:123") && s.contains("y:123"));
    }


    @Test
    public void virusToString() {
        Virus v = new Virus(xCoord, yCoord);
        assertNotNull(v.toString());
    }


    @Test
    public void virusMoving() {
        Virus v = new Virus(xCoord, yCoord);
        v.setCoordinate(xCoord + 100, yCoord + 100);
        assertTrue("<<Coordinate not set properly>>", v.isMoving());
    }


    @Test
    public void virusUpdate() {
        Virus v = new Virus(xCoord, yCoord);
        v.setCoordinate(xCoord + 100, yCoord + 100);
        v.update();
        assertTrue("<<Did not Update>>", (v.getX() != xCoord) && (v.getY() != yCoord));
    }


    @Test
    public void virusReduceHealth() {
        Virus v = new Virus(xCoord, yCoord);
        int initialHp = v.getCuurentHealth();
        v.reduceHealth(10);
        assertTrue("<<Health not reduced>>", (initialHp - 10) == v.getCuurentHealth());
    }


    @Test
    public void virusIdleMovement() {
        Virus v = new Virus(xCoord, yCoord);
        v.setCoordinate(xCoord, yCoord);
        v.update();
        assertTrue("<<Idle not functioning>>", v.isMoving());
    }


    /**
     * Virus Group Tests:
     * virusGroupConstructor()
     * virusGroupOtherConstructor()
     * virusGroupAddVirus()
     * virusGroupRemove()
     * virusGroupGetVirus()
     * virusGroupSetCoord()
     *
     * @author Alex M
     */

    @Test
    public void virusGroupConstructor() {
        VirusGroup vg = new VirusGroup(new Virus(xCoord, yCoord));
        String s = vg.toString();
        assertTrue("<<Constuctor Incorrect>>", s.contains("Viruses:2"));
    }


    @Test
    public void virusGroupOtherConstructor() {
        Virus[] a = new Virus[]{new Virus(0, 0), new Virus(0, 0)};
        VirusGroup vg = new VirusGroup(a);
        String s = vg.toString();
        assertTrue("<<Overloaded Constructor Not Working>>", s.contains("Viruses:2"));
    }


    @Test
    public void virusGroupAddVirus() {
        VirusGroup vg = new VirusGroup(new Virus(xCoord, yCoord));
        vg.addVirus(new Virus(0, 0));
        String s = vg.toString();
        assertTrue("<<Did not add correctly>>", s.contains("Viruses:3"));
    }


    @Test
    public void virusGroupRemove() {
        VirusGroup vg = new VirusGroup(new Virus(xCoord, yCoord));
        vg.remove(0);
        String s = vg.toString();
        assertTrue("<<Did not remove correctly>>", s.contains("Viruses:1"));
    }


    @Test
    public void virusGroupGetVirus() {
        VirusGroup vg = new VirusGroup(new Virus(xCoord, yCoord));
        Virus v = vg.getVirus(0);
        assertTrue("<<Not Equal>>", vg.remove(0).equals(v));
    }


    @Test
    public void virusGroupSetCoord() {
        VirusGroup vg = new VirusGroup(new Virus(xCoord, yCoord));
        vg.getVirus(0).setCoordinate(100, 100);
        assertTrue("<<Coord not set correctly>>", vg.getVirus(0).isMoving());
    }


    /**
     * Virus Group Manager Tests:
     * virusGroupManagerConstructor()
     * virusGroupManagerHasKey()
     * virusGroupManagerAddVirus()
     * virusGroupManagerRemove()
     * virusGroupManagerSplit()
     * antiVirusManagerConstructor()
     * antiVirusToString()
     * antiVirusAddAnti()
     *
     * @author Alex M
     */

    @Test
    public void virusGroupManagerConstructor() {
        VirusGroupManager vgm = new VirusGroupManager();
        String s = vgm.toString();
        assertTrue("<<Incorrect Constructor>>", s.contains("numGroups:1") && s.contains("current group:1"));

    }

    @Test
    public void virusGroupManagerHasKey() {
        VirusGroupManager vgm = new VirusGroupManager();
        assertTrue("<<Doesn't Have Key>>", vgm.hasKey(1));
    }

    @Test
    public void virusGroupManagerAddVirus() {
        VirusGroupManager vgm = new VirusGroupManager();
        vgm.addVirus(0, 0);
        assertTrue("<<Virus Not Added>>", vgm.groups.get(1).size() == 3);
    }

    @Test
    public void virusGroupManagerRemove() {
        VirusGroupManager vgm = new VirusGroupManager();
        vgm.remove(vgm.groups.get(1).getVirus(0));
        assertTrue("<<Virus not removed>>", vgm.groups.get(1).size() == 1);
    }

    @Test
    public void virusGroupManagerSplit() {
        VirusGroupManager vgm = new VirusGroupManager();
        vgm.split();
        assertTrue("<<Did not Split>>", vgm.hasKey(1) && vgm.hasKey(2));
    }

    @Test
    public void antiVirusManagerConstructor() {
        AntiVirusManager avm = new AntiVirusManager();
        String s = avm.toString();
        assertTrue("<<Invalid AntiVirusManager constructor>>",
                s.contains("AntiVirusManager[") && s.contains("anti: " + AntiVirusManager.anti.toString()));
    }

    @Test
    public void antiVirusManagerToString() {
        AntiVirusManager avm = new AntiVirusManager();
        String s = avm.toString();
        assertNotNull(s);
    }

    @Test
    public void antiVirusManagerAddAnti() {
        AntiVirusManager.addAnti(0, 0);
        assertTrue("<<AntiVirus not added to anti>>", AntiVirusManager.anti.size() != 0);


    }

    @Test
    public void antiVirusManagerDraw() {
        Graphics graphics = createMock(Graphics.class);

        AntiVirusManager antiVirusManager = new AntiVirusManager();
        antiVirusManager.addAnti(30, 20);
        boolean causesError = true;
        try {
            antiVirusManager.draw(graphics, 20, 20);
        } catch (Exception e) {
            causesError = false;
        }
        assertTrue("<<There is an error with the Draw method>>", causesError);

    }

    @Test
    public void updateLocationAntiVirus() {
        Graphics graphics = createMock(Graphics.class);

        AntiVirusManager antiVirusManager = new AntiVirusManager();
        antiVirusManager.addAnti(30, 20);
        boolean causesError = true;
        try {
            antiVirusManager.updateLocation(graphics, 20, 20);
        } catch (Exception e) {
            causesError = false;
        }
        assertTrue("<<There is an error with the updateMethod method>>", causesError);
    }

    /**
     * Cell Tests:
     * cellToString()
     * cellConstructor()
     * cellMax()
     * cellGetX()
     * cellGetY()
     * cellSetX()
     * cellSetY()
     * cellGetDistance()
     * cellDecrementHealth()
     * cellIncreaseHealth()
     *
     * @author Melissa
     */
    @Test
    public void cellToString() {
        Cell wc = new WhiteCell(0, 0, 100);
        assertNotNull(wc.toString());
    }


    @Test
    public void cellConstructor() {
        Cell wc = new RedCell(0, 0, 100);
        String s = wc.toString();
        assertTrue("<<Invalid Cell Constructor>>",
                s.contains("RedCell[") && s.contains("x: 0") && s.contains("y: 0")
                        && s.contains("health: " + 100));
    }


    @Test
    public void cellMax() {
        Cell wc = new RedCell(0, 0, 100);
        assertTrue("<<No Max Variable Stored>>", wc.max() == 100);

    }


    @Test
    public void cellGetX() {
        Cell wc = new RedCell(0, 0, 100);
        assertTrue("<<No X Variable Stored>>", wc.getX() == 0);
    }


    @Test
    public void cellGetY() {
        Cell wc = new RedCell(0, 0, 100);
        assertTrue("<<No Y Variable Stored>>", wc.getY() == 0);
    }


    @Test
    public void cellSetX() {
        Cell wc = new RedCell(0, 0, 100);
        wc.setX(10);
        assertTrue("<<No X Variable Changed>>", wc.getX() == 10);
    }


    @Test
    public void cellSetY() {
        Cell wc = new RedCell(0, 0, 100);
        wc.setY(10);
        assertTrue("<<No Y Variable Changed>>", wc.getY() == 10);
    }


    @Test
    public void cellGetDistance() {
        Cell wc = new RedCell(0, 0, 100);
        Cell wc1 = new RedCell(24, 7, 100);
        assertTrue("<<Distance incorrect>>", wc.getDistance(wc1) == 5);

    }


    @Test
    public void cellDecrementHealth() {
        Cell wc = new RedCell(0, 0, 100);
        wc.decrementHealth(20);
        assertTrue("<<Health decremented incorrectly>>", wc.getHealth() == 80);

    }


    @Test
    public void cellIncreaseHealth() {
        Cell wc = new RedCell(0, 0, 100);
        wc.increaseHealth(20);
        assertTrue("<<Health increased incorrectly>>", wc.getHealth() == 120);
    }


    /**
     * Red Cell Tests:
     * redCellConstructor()
     * redCellToString()
     *
     * @author Melissa
     */
    @Test
    public void redCellConstructor() {
        RedCell wc = new RedCell(0, 0, 100);
        String s = wc.toString();
        assertTrue("<<Invalid Cell Constructor>>",
                s.contains("RedCell[") && s.contains("x: 0") && s.contains("y: 0")
                        && s.contains("health: " + 100));
    }


    @Test
    public void redCellToString() {
        RedCell wc = new RedCell(0, 0, 100);
        assertNotNull(wc.toString());
    }


    /**
     * Sick Cell Tests:
     * sickCellConstructor()
     * sickCellToString()
     * sickCellProduceUnit()
     *
     * @author Melissa
     */
    @Test
    public void sickCellConstructor() {
        SickCell wc = new SickCell(0, 0, 100);
        String s = wc.toString();
        assertTrue("<<Invalid Cell Constructor>>",
                s.contains("SickCell[") && s.contains("x: 0") && s.contains("y: 0") && s.contains("health: " + 100)
                        && s.contains("ticks: " + 0));
    }


    @Test
    public void sickCellToString() {
        SickCell wc = new SickCell(0, 0, 100);
        assertNotNull(wc.toString());
    }


    @Test
    public void sickCellProduceUnit() {
        SickCell wc = new SickCell(0, 0, 100);
        VirusGroupManager vgm = new VirusGroupManager();
        Collection<VirusGroup> al = VirusGroupManager.groups.values();
        int a = 0;
        for (VirusGroup vg : al) {
            a += vg.size();
        }
        int b = 0;

        wc.setTicks(300);
        wc.produceUnit();
        for (VirusGroup vg : al) {
            b += vg.size();
        }

        assertTrue("<<AntiVirus Production error from Sick Cells>>", a + 1 == b);

    }


    /**
     * White Cell Tests:
     * whiteCellConstructor()
     * whiteCellToString()
     * whiteCellSplit()
     * whiteCellProduceUnit()
     * whiteCellSetAttacked()
     * whiteCellIsAttacked()
     * whiteCellGetTime()
     * whiteCellUpdateTime()
     * whiteCellMove()
     *
     * @author Melissa
     */
    @Test
    public void whiteCellConstructor() {
        WhiteCell wc = new WhiteCell(0, 0, 100);
        String s = wc.toString();
        assertTrue("<<Invalid WhiteCell Constructor>>",
                s.contains("WhiteCell[") && s.contains("x: 0") && s.contains("y: 0")
                        && s.contains("health: " + 100));
    }


    @Test
    public void whiteCellToString() {
        WhiteCell wc = new WhiteCell(0, 0, 100);
        assertNotNull(wc.toString());
    }


    @Test
    public void whiteCellSplit() {
        ArrayList<Cell> w = new ArrayList<Cell>();
        WhiteCell wc = new WhiteCell(0, 0, 100);
        w.add(wc);
        wc.split(w);
        assertTrue("<<Cells not Spliting>>", w.size() == 2);
    }


    @Test
    public void whiteCellProduceUnit() {
        WhiteCell wc = new WhiteCell(0, 0, 100);
        wc.produceUnit();
        assertNotNull(wc);

    }


    @Test
    public void whiteCellSetAttacked() {
        WhiteCell wc = new WhiteCell(0, 0, 100);
        wc.setAttacked(true);
        assertTrue("<<White Cell Attack not set>>", wc.isAttacked());
    }


    @Test
    public void whiteCellIsAttacked() {
        WhiteCell wc = new WhiteCell(0, 0, 100);
        wc.setAttacked(true);
        assertTrue("<<White Cell Attack not set>>", wc.isAttacked());
    }


    @Test
    public void whiteCellGetTime() {
        WhiteCell wc = new WhiteCell(0, 0, 100);
        System.out.println(wc.getTime());
        assertTrue("<<White Cell Time not set>>", wc.getTime() == 0);
    }


    @Test
    public void whiteCellUpdateTime() {
        WhiteCell wc = new WhiteCell(0, 0, 100);
        wc.updateTime();
        wc.updateTime();
        assertTrue("<<White Cell Time not set>>", wc.getTime() == 2);
    }


    @Test
    public void whiteCellMove() {
        WhiteCell wc = new WhiteCell(0, 0, 100);
        wc.setvx(5);
        wc.setvy(6);
        wc.move();
        wc.updateTime();
        assertTrue("<<White Cell not Moving>>", wc.vx() == 5 && wc.vy() == 6);
    }


    /**
     * Cell Manager Tests:
     * cellManagerConstructor()
     * cellManagerCreateCellsInPositions()
     * cellManagerConvertSick()
     * cellManagerRemoveCell()
     * cellManagerToString()
     *
     * @author Melissa
     */
    @Test
    public void cellManagerConstructor() {
        CellManager cm = new CellManager();
        String s = cm.toString();
        assertTrue("<<Invalid CellManager Constructor>>",
                s.contains("CellManager[") && s.contains("whiteValues: " + CellManager.whiteValues.toString())
                        && s.contains("redValues: " + CellManager.redValues.toString())
                        && s.contains("sickValues: " + CellManager.sickValues.toString()));
    }


    @Test
    public void cellManagerCreateCellsInPositions() {
        CellManager cm = new CellManager();
        cm.createCellsInPositions();

        assertTrue("<<Cells not Created>>",
                CellManager.whiteValues.size() == 3 && CellManager.sickValues.size() == 2
                        && CellManager.redValues.size() == 20);
    }


    @Test
    public void cellManagerConvertSick() {
        CellManager cm = new CellManager();
        CellManager.redValues.clear();
        CellManager.sickValues.clear();
        Cell c = new RedCell(0, 0, 100);
        CellManager.redValues.add(c);
        CellManager.convertCell(c);
        assertTrue("<<Cell did not convert to Sick>>",
                CellManager.redValues.size() == 0 && CellManager.sickValues.size() == 1);

    }


    @Test
    public void cellManagerRemoveCell() {
        CellManager cm = new CellManager();
        CellManager.whiteValues.clear();
        CellManager.whiteValues.add(new WhiteCell(0, 0, 200));
        CellManager.removeCell(0);
        assertTrue("<<Cell did not get removed>>", CellManager.whiteValues.isEmpty());

    }


    @Test
    public void cellManagerToString() {
        CellManager cm = new CellManager();
        assertNotNull(cm.toString());
    }

    /**
     * AntiVirus Test Classes
     * antiVirusConstructor()
     * antiVirusUpdate()
     * antiVirusDraw()
     * antiVirusToString()
     *
     * @author Vikranth Srivatsa
     */
    @Test
    public void antiVirusConstructor() {
        AntiVirus antiVirus = new AntiVirus(20, 30);
        String test = antiVirus.toString();
        assertTrue("<<Invalid Construction Of objects>>", test.equals("AntiVirus{currentFollowVirus=null, positionOfVirus=0, follow=false}"));
    }

    @Test
    public void antiVirusUpdate() {
        Graphics graphics = createMock(Graphics.class);
        VirusGroupManager virusGroupManager = new VirusGroupManager();
        Virus virus = new Virus(30, 30);
        virusGroupManager.addVirus(virus);
        AntiVirus antiVirus = new AntiVirus(20, 30);
        antiVirus.update(graphics, 20, 20);
        assertTrue("<<AntiVirus Did not Attack Correctly>>", 59 == virus.getHealth());
    }

    @Test
    public void antiVirusDraw() {
        Graphics graphics = createMock(Graphics.class);

        AntiVirus antiVirus = new AntiVirus(20, 30);
        boolean causesError = true;
        try {
            antiVirus.draw(graphics, 20, 20);
        } catch (Exception e) {
            causesError = false;
        }
        assertTrue("<<There is an error with the Draw method>>", causesError);
    }

    @Test
    public void antiVirusToString() {
        AntiVirus antiVirus = new AntiVirus(20, 30);
        assertNotNull("<<The to String is not Null>>", antiVirus.toString());
    }

    /**
     * Attacker Test:
     * attackerConstructor()
     * attackerSetCoordinate()
     * attackerreduceHealth()
     * attackerGetAttackRadius()
     * attackerGetX()
     * attackerGetY()
     * attackerHealth()
     * attackerVelocityX()
     * attackerVelocityY()
     * attackerHeight()
     * attackerWidth()
     * attackerSpeed()
     * attackerXLocation()
     * attackerYLocation()
     * attackerSetX()
     * attackerSetY()
     * attackerSetVx()
     * attackerSetVy()
     * attackerIsDead()
     * attackerGetDistanceWithoutLocatable()
     * attackerToString();
     *
     * @author Vikranth Srivatsa
     */
    @Test
    public void attackerConstructor() {
        Attacker attacker = new Virus(30, 30);
        String testString = "Virus[x:30 y:30 speed:5 attack:1 height:30 width:30]";
        assertTrue("<<attacker to String is wrong>>", testString.equalsIgnoreCase(attacker.toString()));
    }

    @Test
    public void attackerSetCoordinate() {
        Attacker attacker = new Virus(30, 30);
        attacker.setCoordinate(40, 40);
        assertTrue("<<The coordinate is set wrong>>", 3 == attacker.getVx());
    }

    @Test
    public void attackerreduceHealth() {
        Attacker attacker = new AntiVirus(30, 30);
        attacker.reduceHealth(30);
        assertTrue("<<The health is not reduced right>>",
                Constants.ANTIVIRUS_HEALTH - 30 == attacker.getHealth());
    }

    @Test
    public void attackerGetAttackRadius() {
        Attacker attacker = new Virus(30, 30);
        assertTrue("<<The attacker get Radius>>", Constants.VIRUS_ATTACK_RADIUS == attacker.getAttackRadius());
    }

    @Test
    public void attackerGetX() {
        Attacker attacker = new Virus(30, 30);
        assertTrue("<<The attacker get Radius>>", attacker.getX() == 30);
    }

    @Test
    public void attackerGetY() {
        Attacker attacker = new Virus(30, 30);
        assertTrue("<<The attacker get Radius>>", attacker.getX() == 30);
    }

    @Test
    public void attackerHealth() {
        Attacker attacker = new Virus(30, 30);
        assertTrue("<<The attacker get Radius>>", Constants.VIRUS_HEALTH == attacker.getHealth());
    }

    @Test
    public void attackerVelocityX() {
        Attacker attacker = new Virus(30, 30);
        attacker.setCoordinate(40, 40);
        assertTrue("<<returns the wrong velocity x>>", 3 == attacker.getVx());
    }

    @Test
    public void attackerVelocityY() {
        Attacker attacker = new Virus(30, 30);
        attacker.setCoordinate(40, 40);
        assertTrue("<<returns the wrong velocity y>>", 3 == attacker.getVy());
    }

    @Test
    public void attackerHeight() {
        Attacker attacker = new AntiVirus(30, 30);
        assertTrue("<<returns the wrong height>>", Constants.ANTIVIRUS_HEIGHT == attacker.getHeight());
    }

    @Test
    public void attackerWidth() {
        Attacker attacker = new Virus(30, 30);
        assertTrue("<<returns the wrong width>>", Constants.VIRUS_WIDTH == attacker.getWidth());
    }

    @Test
    public void attackerSpeed() {
        Attacker attacker = new Virus(30, 30);
        assertTrue("<<returns the wrong speed>>", Constants.ANTIVIRUS_SPEED == attacker.getSpeed());
    }

    @Test
    public void attackerXLocation() {
        Attacker attacker = new Virus(30, 30);
        attacker.setCoordinate(40, 40);
        assertTrue("<<returns the wrong new x Location>>", 40 == attacker.getXL());
    }

    @Test
    public void attackerYLocation() {
        Attacker attacker = new Virus(30, 30);
        attacker.setCoordinate(40, 40);
        assertTrue("<<returns the  wrong new y Location>>", 40 == attacker.getYL());
    }

    @Test
    public void attackerSetX() {
        Attacker attacker = new Virus(30, 30);
        attacker.setX(50);
        assertTrue("<<sets the wrong  x location>>", 50 == attacker.getX());
    }

    @Test
    public void attackerSetY() {
        Attacker attacker = new Virus(30, 30);
        attacker.setY(50);
        assertTrue("<<sets the wrong  y location>>", 50 == attacker.getY());
    }

    @Test
    public void attackerSetVx() {
        Attacker attacker = new Virus(30, 30);
        attacker.setVx(50);
        assertTrue("<<sets the wrong x velocity>>", 50 == attacker.getVx());
    }

    @Test
    public void attackerSetVy() {
        Attacker attacker = new Virus(30, 30);
        attacker.setVy(50);
        assertTrue("<<sets the wrong y velocity>>", 50 == attacker.getVy());
    }

    @Test
    public void attackerIsDead() {
        Attacker attacker = new Virus(30, 30);
        assertTrue("<<does not check if virus is dead right>>", !attacker.isDead());
    }

    @Test
    public void attackerGetDistance() {
        Attacker attacker = new Virus(30, 30);
        double value = attacker.getDistance(new Virus(20, 20));
        assertTrue("<<does not find the right distance>>", 14 == (int) value);
    }

    @Test
    public void attackerGetDistanceWithoutLocatable() {
        Attacker attacker = new Virus(30, 30);
        double value = attacker.getDistance(20, 20);
        assertTrue("<<does not find the right distance>>", 14 == (int) value);
    }

    @Test
    public void attackerToString() {
        Attacker attacker = new Virus(30, 30);
        assertNotNull(attacker.toString());
    }

    /**
     * Health Bar tests:
     * healthBarConctructor()
     * healthBarDraw()
     *
     * @author Vikranth Srivatsa
     */
    @Test
    public void healthBarConctructor() {
        HealthBar healthBar = new HealthBar(createMock(Cell.class));
        String test = "HealthBar{cell=EasyMock for class Cell}";
        assertTrue("<<The Cell is contructed wrong>>", test.equalsIgnoreCase(healthBar.toString()));
    }

    @Test
    public void healthBarDraw() {
        Graphics graphics = createMock(Graphics.class);
        HealthBar healthBar = new HealthBar(new WhiteCell(39, 39, 199));
        boolean test = true;
        try {
            healthBar.draw(graphics, 20, 20);
        } catch (Exception e) {
            test = false;
            e.printStackTrace();
        }
        assertTrue("<<There is an error with the draw method>>", test);
    }

    /**
     * Locatable Tests:
     * locatableGetX()
     * locatableDistance();
     *
     * @author Vikranth Srivatsa
     */
    @Test
    public void locatableGetX() {
        Locatable locatable = createMock(Locatable.class);
        boolean test = true;
        try {
            locatable.getX();
        } catch (Exception e) {
            test = false;
        }
        assertTrue("<<Locatable is not working>>", test);
    }

    @Test
    public void locatableGetY() {
        Locatable locatable = createMock(Locatable.class);
        boolean test = true;
        try {
            locatable.getX();
        } catch (Exception e) {
            test = false;
        }
        assertTrue("<<Locatable is not working>>", test);
    }

    @Test
    public void locatableDistance() {
        Locatable locatable = createMock(Locatable.class);
        boolean test = true;
        try {
            locatable.getDistance(new Virus(30, 39));
        } catch (Exception e) {
            test = false;
        }
        assertTrue("<<Locatable is not working>>", test);
    }

    /**
     * ScoreBoard Tests:
     * scoreBoardtoStringTest()
     * scoreBoardContructorTest()
     * everyTurnTest()
     * increaseWhiteDeaths()
     * increaseRedCellsDead()
     * getSeconds()
     * getWhiteCellsKilled()
     * getRedCellsConquered()
     * getTotalScore();
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
