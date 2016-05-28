

import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

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


    // Cell - Melissa
    @Test
    public void cellToString()
    {
        Cell wc = new WhiteCell( 0, 0, 100 );
        assertNotNull( wc.toString() );
    }
    // RedCell - Melissa
    // SickCell - Melissa


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
        assertTrue( "<<Cells not Spliting>>", w.size() == 2);
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
        
    }
    // CellManager - Melissa

}
