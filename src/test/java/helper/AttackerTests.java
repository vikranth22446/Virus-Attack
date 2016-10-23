package helper;

import antivirus.AntiVirus;
import org.junit.Test;
import virus.Virus;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by vikranth on 10/21/2016.
 */
public class AttackerTests {
    /**
     * main.helper.Attacker Test:
     * attackerConstructor(): Checks if the attacker is the right constructor
     * attackerSetCoordinate(): Checks if the coordinate is set rght
     * attackerreduceHealth(): Checks if health is reduced
     * attackerGetAttackRadius(): Checks if attack radius is returned right
     * attackerGetX(): Checks if right x is returned
     * attackerGetY(): Checks if right y is returned
     * attackerHealth(): Checks if right health is returned
     * attackerVelocityX(): Checks if right velocity x is returned
     * attackerVelocityY(): Checks if right velocity y is returned
     * attackerHeight(): Checks if right height is returned
     * attackerWidth(): Checks if width height is returned
     * attackerSpeed(): Checks if speed height is returned
     * attackerXLocation(): Checks if x location is returned
     * attackerYLocation(): : Checks if y location is returned
     * attackerSetX(): Checks if x is set
     * attackerSetY(): Checks if y is set
     * attackerSetVx(): Checks if Vx is set
     * attackerSetVy(): Checks if Vy is set
     * attackerIsDead(): Checks if the attacker is dead
     * attackerGetDistanceWithoutLocatable(): Checks if the right distance is returned
     * attackerToString(); checks if the string is not null
     *
     * @author Vikranth Srivatsa
     */
    @Test
    public void attackerConstructor() {
        Attacker attacker = new Virus(30, 30);
        String testString = "main.virus.Virus[x:30 y:30 speed:5 attack:1 height:30 width:30]";
        assertTrue("<<attacker to String is wrong>>", testString.equalsIgnoreCase(attacker.toString()));
    }

    @Test
    public void attackerSetCoordinate() {
        Attacker attacker = new Virus(30, 30);
        attacker.setCoordinate(40, 40);
        assertTrue("<<The coordinate is set wrong>>", 2 == attacker.getVx());
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
}
