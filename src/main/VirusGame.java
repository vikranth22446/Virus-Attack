package main;


/**
 * Class with the main method that calls main.WelcomeScreen.createScreen();
 *
 * @author Vikranth Srivatsa
 */
class VirusGame {
    /**
     * Constructs a main.WelcomeScreen. Also calls createScreen().
     *
     * @param args command line argument not used.
     */
    public static void main(String[] args) {
        WelcomeScreen welcomeScreen = new WelcomeScreen();
        welcomeScreen.createWelcomeScreen();
    }
}
