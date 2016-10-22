package main;

/**
 * main.Locatable used by almost ever where. For example in classes
 * like main.Virus, main.AntiVirus, and main.Cell.
 *
 * @author Vikranth Srivatsa
 */
public interface Locatable {
    /**
     * Returns the x position of the locatable
     *
     * @return the x position of he locatable
     */
    public int getX();

    /**
     * Returns the y position of the locatable
     *
     * @return the y position of the location
     */
    public int getY();

    /**
     * Calculates the distance between 2 locatable. Usually calculated
     * using the distance formula.
     *
     * @param other the other locatable to find distance between
     * @return the distance between the 2 locatable
     */
    public double getDistance(Locatable other);
}