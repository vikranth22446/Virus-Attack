/**
 * Locatable used by almost ever where. For example in classes
 * like Virus, AntiVirus, and Cell.
 */
interface Locatable {
    /**
     * Returns the x position of the locatable
     * @return the x position of he locatable
     */
    int getX();

    /**
     * Returns the y position of the locatable
     * @return the y position of the location
     */
    int getY();

    /**
     * Calculates the distance between 2 locatable. Usually calculated
     * using the distance formula.
     * @param other the other locatable to find distance between
     * @return the distance between the 2 locatable
     */
    double getDistance(Locatable other);
}