


/**
 * Class with the main method that calls WelcomeScreen.createScreen();
 *
 * @author Vikranth Srivatsa
 */
class VirusGame {
    /**
     * Constructs a WelcomeScreen. Also calls createScreen().
     *
     * @param args command line argument not used.
     */
    public static void main(String[] args) {
        WelcomeScreen welcomeScreen = new WelcomeScreen();
        welcomeScreen.createWelcomeScreen();
    }
}
