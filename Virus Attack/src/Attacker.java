/**
 * Created by Vikranth on 5/4/2016.
 */
public abstract class Attacker implements Locatable {
    private int targetNumber;

    public Attacker(int targetNumber) {
        this.targetNumber = targetNumber;
    }
}
