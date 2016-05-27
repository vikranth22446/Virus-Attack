package RegularClasses;

/**
 * Class with the main method that calls RegularClasses.WelcomeScreen.createWelcomeScreen();
 */
public class VirusGame {
    /**
     * Constructs a RegularClasses.WelcomeScreen. Also calls createWelcomeScreen().
     *
     * @param args command line argument not used.
     */
    public static void main(String[] args) {
        WelcomeScreen worldScreen = new WelcomeScreen();
        worldScreen.createWelcomeScreen();

    }
}
