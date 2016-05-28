package src;


/**
 * Class with the main method that calls WelcomeScreen.createWelcomeScreen();
 */
class VirusGame {
    /**
     * Constructs a WelcomeScreen. Also calls createWelcomeScreen().
     *
     * @param args command line argument not used.
     */
    public static void main(String[] args) {
        WelcomeScreen worldScreen = new WelcomeScreen();
        worldScreen.createWelcomeScreen();

    }
}
