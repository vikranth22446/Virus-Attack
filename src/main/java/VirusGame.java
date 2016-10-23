import gui.WelcomeScreen;

/**
 * Class with the main method that calls main.gui.WelcomeScreen.createScreen();
 *
 * @author Vikranth Srivatsa
 */
class VirusGame {
    /**
     * Constructs a main.gui.WelcomeScreen. Also calls createScreen().
     *
     * @param args command line argument not used.
     */
    public static void main(String[] args) {
       WelcomeScreen welcomeScreen = new WelcomeScreen();
        welcomeScreen.createWelcomeScreen();
    }
}
