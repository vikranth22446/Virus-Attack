
package src;

import org.junit.Test;

import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class JUnitVirusAttackTest
{

    private int xCoord = 123;

    private int yCoord = 123;

    private int groupNum = 3;

    private Graphics g;


    // Virus - Alex
    @Test
    public void virusConstuctor()
    {
        Virus v = new Virus( xCoord, yCoord );
        String s = v.toString();

        assertTrue( "<<Invalid Virus Constructor>>",
            s.contains( "Virus[" ) && s.contains( "x:123" ) && s.contains( "y:123" ) );
    }


    @Test
    public void virusToString()
    {
        Virus v = new Virus( xCoord, yCoord );
        assertNotNull( v.toString() );
    }


    @Test
    public void virusMoving()
    {
        Virus v = new Virus( xCoord, yCoord );
        v.setCoordinate( xCoord + 100, yCoord + 100 );
        assertTrue( "<<Coordinate not set properly>>", v.isMoving() );
    }


    @Test
    public void virusUpdate()
    {
        Virus v = new Virus( xCoord, yCoord );
        v.setCoordinate( xCoord + 100, yCoord + 100 );
        v.update();
        assertTrue( "<<Did not Update>>", ( v.getX() != xCoord ) && ( v.getY() != yCoord ) );
    }


    @Test
    public void virusReduceHealth()
    {
        Virus v = new Virus( xCoord, yCoord );
        int initialHp = v.getCuurentHealth();
        v.reduceHealth( 10 );
        assertTrue( "<<Health not reduced>>", ( initialHp - 10 ) == v.getCuurentHealth() );
    }


    @Test
    public void virusIdleMovement()
    {
        Virus v = new Virus( xCoord, yCoord );
        v.setCoordinate( xCoord, yCoord );
        v.update();
        assertTrue( "<<Idle not functioning>>", v.isMoving() );
    }


    // VirusGroup -Alex

    @Test
    public void virusGroupConstructor()
    {
        VirusGroup vg = new VirusGroup( new Virus( xCoord, yCoord ) );
        String s = vg.toString();
        assertTrue( "<<Constuctor Incorrect>>", s.contains( "Viruses:2" ) );
    }


    @Test
    public void virusGroupOtherConstructor()
    {
        Virus[] a = new Virus[] { new Virus( 0, 0 ), new Virus( 0, 0 ) };
        VirusGroup vg = new VirusGroup( a );
        String s = vg.toString();
        assertTrue( "<<Overloaded Constructor Not Working>>", s.contains( "Viruses:2" ) );
    }


    @Test
    public void virusGroupAddVirus()
    {
        VirusGroup vg = new VirusGroup( new Virus( xCoord, yCoord ) );
        vg.addVirus( new Virus( 0, 0 ) );
        String s = vg.toString();
        assertTrue( "<<Did not add correctly>>", s.contains( "Viruses:3" ) );
    }


    @Test
    public void virusGroupRemove()
    {
        VirusGroup vg = new VirusGroup( new Virus( xCoord, yCoord ) );
        vg.remove( 0 );
        String s = vg.toString();
        assertTrue( "<<Did not remove correctly>>", s.contains( "Viruses:1" ) );
    }


    @Test
    public void virusGroupGetVirus()
    {
        VirusGroup vg = new VirusGroup( new Virus( xCoord, yCoord ) );
        Virus v = vg.getVirus( 0 );
        assertTrue( "<<Not Equal>>", vg.remove( 0 ).equals( v ) );
    }


    @Test
    public void virusGroupSetCoord()
    {
        VirusGroup vg = new VirusGroup( new Virus( xCoord, yCoord ) );
        vg.getVirus( 0 ).setCoordinate( 100, 100 );
        assertTrue( "<<Coord not set correctly>>", vg.getVirus( 0 ).isMoving() );
    }


    // VirusGroupManager -Alex

    @Test
    public void virusGroupManagerConstructor()
    {
        VirusGroupManager vgm = new VirusGroupManager();
    }


    // AntiVirusManager -Melissa
    @Test
    public void antiVirusManagerConstructor()
    {
        AntiVirusManager avm = new AntiVirusManager();
        String s = avm.toString();
        assertTrue( "<<Invalid AntiVirusManager constructor>>",
            s.contains( "AntiVirusManager[" ) && s.contains( "anti: " + AntiVirusManager.anti.toString() ) );
    }
    @Test
    public void antiVirusToString()
    {
        AntiVirusManager avm = new AntiVirusManager();
        String s = avm.toString();
        assertNotNull(s);        
    }
    @Test 
    public void antiVirusAddAnti()
    {
        AntiVirusManager.addAnti( 0, 0 );
        assertTrue( "<<AntiVirus not added to anti>>",AntiVirusManager.anti.size() != 0);
        

    }


    // Cell - Melissa
    @Test
    public void cellToString()
    {
        Cell wc = new WhiteCell( 0, 0, 100 );
        assertNotNull( wc.toString() );
    }


    @Test
    public void cellConstructor()
    {
        Cell wc = new RedCell( 0, 0, 100 );
        String s = wc.toString();
        assertTrue( "<<Invalid Cell Constructor>>",
            s.contains( "RedCell[" ) && s.contains( "x: 0" ) && s.contains( "y: 0" )
                && s.contains( "health: " + 100 ) );
    }


    @Test
    public void cellMax()
    {
        Cell wc = new RedCell( 0, 0, 100 );
        assertTrue( "<<No Max Variable Stored>>", wc.max() == 100 );

    }


    @Test
    public void cellGetX()
    {
        Cell wc = new RedCell( 0, 0, 100 );
        assertTrue( "<<No X Variable Stored>>", wc.getX() == 0 );
    }


    @Test
    public void cellGetY()
    {
        Cell wc = new RedCell( 0, 0, 100 );
        assertTrue( "<<No Y Variable Stored>>", wc.getY() == 0 );
    }


    @Test
    public void cellSetX()
    {
        Cell wc = new RedCell( 0, 0, 100 );
        wc.setX( 10 );
        assertTrue( "<<No X Variable Changed>>", wc.getX() == 10 );
    }


    @Test
    public void cellSetY()
    {
        Cell wc = new RedCell( 0, 0, 100 );
        wc.setY( 10 );
        assertTrue( "<<No Y Variable Changed>>", wc.getY() == 10 );
    }


    @Test
    public void cellGetDistance()
    {
        Cell wc = new RedCell( 0, 0, 100 );
        Cell wc1 = new RedCell( 24, 7, 100 );
        assertTrue( "<<Distance incorrect>>", wc.getDistance( wc1 ) == 5 );

    }


    @Test
    public void cellDecrementHealth()
    {
        Cell wc = new RedCell( 0, 0, 100 );
        wc.decrementHealth( 20 );
        assertTrue( "<<Health decremented incorrectly>>", wc.getHealth() == 80 );

    }


    @Test
    public void cellIncreaseHealth()
    {
        Cell wc = new RedCell( 0, 0, 100 );
        wc.increaseHealth( 20 );
        assertTrue( "<<Health increased incorrectly>>", wc.getHealth() == 120 );
    }


    // RedCell - Melissa
    @Test
    public void redCellConstructor()
    {
        RedCell wc = new RedCell( 0, 0, 100 );
        String s = wc.toString();
        assertTrue( "<<Invalid Cell Constructor>>",
            s.contains( "RedCell[" ) && s.contains( "x: 0" ) && s.contains( "y: 0" )
                && s.contains( "health: " + 100 ) );
    }


    @Test
    public void redCellToString()
    {
        RedCell wc = new RedCell( 0, 0, 100 );
        assertNotNull( wc.toString() );
    }


    // SickCell - Melissa
    @Test
    public void SickCellConstructor()
    {
        SickCell wc = new SickCell( 0, 0, 100 );
        String s = wc.toString();
        assertTrue( "<<Invalid Cell Constructor>>",
            s.contains( "SickCell[" ) && s.contains( "x: 0" ) && s.contains( "y: 0" ) && s.contains( "health: " + 100 )
                && s.contains( "ticks: " + 0 ) );
    }


    @Test
    public void sickCellToString()
    {
        SickCell wc = new SickCell( 0, 0, 100 );
        assertNotNull( wc.toString() );
    }


    @Test
    public void sickCellProduceUnit()
    {
        SickCell wc = new SickCell( 0, 0, 100 );
        VirusGroupManager vgm = new VirusGroupManager();
        Collection<VirusGroup> al = VirusGroupManager.groups.values();
        int a = 0;
        for ( VirusGroup vg : al )
        {
            a += vg.size();
        }
        int b = 0;

        wc.setTicks( 300 );
        wc.produceUnit();
        for ( VirusGroup vg : al )
        {
            b += vg.size();
        }

        assertTrue( "<<AntiVirus Production error from Sick Cells>>", a + 1 == b );

    }


    // WhiteCell - Melissa
    @Test
    public void whiteCellConstructor()
    {
        WhiteCell wc = new WhiteCell( 0, 0, 100 );
        String s = wc.toString();
        assertTrue( "<<Invalid WhiteCell Constructor>>",
            s.contains( "WhiteCell[" ) && s.contains( "x: 0" ) && s.contains( "y: 0" )
                && s.contains( "health: " + 100 ) );
    }


    @Test
    public void WhiteCellToString()
    {
        WhiteCell wc = new WhiteCell( 0, 0, 100 );
        assertNotNull( wc.toString() );
    }


    @Test
    public void WhiteCellSplit()
    {
        ArrayList<Cell> w = new ArrayList<Cell>();
        WhiteCell wc = new WhiteCell( 0, 0, 100 );
        w.add( wc );
        wc.split( w );
        assertTrue( "<<Cells not Spliting>>", w.size() == 2 );
    }


    @Test
    public void WhiteCellProduceUnit()
    {
        WhiteCell wc = new WhiteCell( 0, 0, 100 );
        wc.produceUnit();
        assertNotNull( wc );

    }


    @Test
    public void WhiteCellSetAttacked()
    {
        WhiteCell wc = new WhiteCell( 0, 0, 100 );
        wc.setAttacked( true );
        assertTrue( "<<White Cell Attack not set>>", wc.isAttacked() );
    }


    @Test
    public void WhiteCellIsAttacked()
    {
        WhiteCell wc = new WhiteCell( 0, 0, 100 );
        wc.setAttacked( true );
        assertTrue( "<<White Cell Attack not set>>", wc.isAttacked() );
    }


    @Test
    public void WhiteCellGetTime()
    {
        WhiteCell wc = new WhiteCell( 0, 0, 100 );
        assertTrue( "<<White Cell Time not set>>", wc.getTime() == 0 );
    }


    @Test
    public void WhiteCellUpdateTime()
    {
        WhiteCell wc = new WhiteCell( 0, 0, 100 );
        wc.updateTime();
        wc.updateTime();
        assertTrue( "<<White Cell Time not set>>", wc.getTime() == 2 );
    }


    @Test
    public void WhiteCellMove()
    {
        WhiteCell wc = new WhiteCell( 0, 0, 100 );
        wc.setvx( 5 );
        wc.setvy( 6 );
        wc.move();
        wc.updateTime();
        assertTrue( "<<White Cell not Moving>>", wc.vx() == 5 && wc.vy() == 6 );
    }


    // CellManager - Melissa
    @Test
    public void CellManagerConstructor()
    {
        CellManager cm = new CellManager();
        String s = cm.toString();
        assertTrue( "<<Invalid CellManager Constructor>>",
            s.contains( "CellManager[" ) && s.contains( "whiteValues: " + CellManager.whiteValues.toString() )
                && s.contains( "redValues: " + CellManager.redValues.toString() )
                && s.contains( "sickValues: " + CellManager.sickValues.toString() ) );
    }


    @Test
    public void CellManagerCreateCellsInPositions()
    {
        CellManager cm = new CellManager();
        cm.createCellsInPositions();

        assertTrue( "<<Cells not Created>>",
            CellManager.whiteValues.size() == 3 && CellManager.sickValues.size() == 2
                && CellManager.redValues.size() == 20 );
    }


    @Test
    public void CellManagerConvertSick()
    {
        CellManager cm = new CellManager();
        CellManager.redValues.clear();
        CellManager.sickValues.clear();
        Cell c = new RedCell( 0, 0, 100 );
        CellManager.redValues.add( c );
        CellManager.convertCell( c );
        assertTrue( "<<Cell did not convert to Sick>>",
            CellManager.redValues.size() == 0 && CellManager.sickValues.size() == 1 );

    }


    @Test
    public void CellManagerRemoveCell()
    {
        CellManager cm = new CellManager();
        CellManager.whiteValues.clear();
        CellManager.whiteValues.add( new WhiteCell( 0, 0, 200 ) );
        CellManager.removeCell( 0 );
        assertTrue( "<<Cell did not get removed>>", CellManager.whiteValues.isEmpty() );

    }


    @Test
    public void cellManagerToString()
    {
        CellManager cm = new CellManager();
        assertNotNull( cm.toString() );
    }

}
