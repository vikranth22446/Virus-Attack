package main;

/**
 * A List of main.Constants used thought the classes
 *
 * @author Vikranth Srivatsa
 */
public class Constants {
    /**
     * Anti-main.Virus constants:
     * SPEED,HEALTH,ATTACK, WIDTH,HEIGHT,ATTACK_RADIUS
     */
    public static final int ANTIVIRUS_SPEED = 5;
    public static final int ANTIVIRUS_HEALTH = 45;
    public static final int ANTIVIRUS_ATTACK = 1;
    public static final int ANTIVIRUS_WIDTH = 15;
    public static final int ANTIVIRUS_HEIGHT = 15;
    public static final int ANTIVIRUS_ATTACK_RADIUS = 20;

    /**
     * main.Virus main.Constants:
     * SPEED,HEALTH,ATTACK,ATTACK_RADIUS,WIDTH,HEIGHT,IDLE_RANGE
     */
    public static final int VIRUS_SPEED = 5;
    public static final int VIRUS_HEALTH = 60;
    public static final int VIRUS_ATTACK = 1;
    public static final int VIRUS_ATTACK_RADIUS = 80;
    public static final int VIRUS_WIDTH = 30;
    public static final int VIRUS_HEIGHT = 30;
    public static final int VIRUS_IDLE_RANGE = 50;
    /**
     * main.Cell main.Constants:
     * CELL_RADIUS
     */
    public static final int CELL_RADIUS = 50;
    /**
     * Sick main.Cell main.Constants:
     * GENERATION_CORRECTION, GENERATION_RATE
     */
    public static final int VIRUS_GENERATION_CORRECTION_FACTOR = -20;
    public static final int SICK_CELL_VIRUS_GENERATE_RATE = 200;
    /**
     * Health Bar main.Constants:
     * HEIGHT, CORRECTION_FACTOR
     */
    public static final int HEALTH_BAR_HEIGHT = 10;
    public static final int HEIGHT_CORRECTION_FACTOR = 5;
}
