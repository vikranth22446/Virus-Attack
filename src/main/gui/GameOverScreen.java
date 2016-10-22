package main.gui;

import main.helper.ScoreBoard;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Displays to the user a you win or you loose Gif and then displays stats and try again on blue buttons.
 * When they click the button they restart
 *
 * @author Vikranth Srivatsa
 */
public class GameOverScreen extends JFrame {
    JLabel backgroundGif;

    /**
     * Creates the screen by using the title to choose the right image either won or lost. Then uses the scoreboard to
     * get stats on this game. Then creates a button called Try again
     * Then using html add multiline text to the button
     * Then adds the backgroundGif
     * Then initializes the parts of the screen
     *
     * @param title      the title of the current screen. Either you loose or you win
     * @param scoreBoard the scoreboard used to get stats
     */
    public void createScreen(String title, ScoreBoard scoreBoard) {

        createBackgroundGif(title);

        String buttonToDisplay =
                "Total Time : " + scoreBoard.getTime() + "\n "
                        + "Total White Cells Conquered: " + scoreBoard.getWhiteCellsKilled() + "\n" +
                        "Total Red Cells Conquered: " + scoreBoard.getRedCellsConquered() + "\n"
                        + "Total Score: " + scoreBoard.getTotalScore();
        createButtons(120, 30, World.WIDTH / 4 * World.SCALE, World.HEIGHT * World.SCALE / 4 + 130, "Try Again");
        createButtons(320, 130, World.WIDTH / 4 * World.SCALE - 100, World.HEIGHT * World.SCALE / 4 - 20, "<html>" + buttonToDisplay.replaceAll("\\n", "<br>") + "</html>");
        setTitle("main.virus.Virus Attack");
        getContentPane().add(backgroundGif);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

    }

    /**
     * Creates the Button to display by using the custom size and position. Also set's the title. Then sets the background
     * to a shade of Blue
     * to a certian shade of blue.
     *
     * @param sizeWidth  the custom size to set
     * @param sizeHeight the custom size to set
     * @param x          the x location to set
     * @param y          the y location to set
     * @param title      the title to set
     */
    private void createButtons(int sizeWidth, int sizeHeight, int x, int y, String title) {
        JButton jButton = new JButton();
        jButton.setSize(sizeWidth, sizeHeight);
        jButton.setLocation(x, y);
        jButton.setText(title);
        jButton.setBackground(new Color(20, 99, 182));
        jButton.setForeground(Color.WHITE);
        jButton.setFocusPainted(false);
        jButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        jButton.addActionListener(e -> newGame());
        add(jButton);
    }

    /**
     * Creates a new game by calling main.gui.WelcomeScreen again
     */
    private void newGame() {
        WelcomeScreen welcomeScreen = new WelcomeScreen();
        welcomeScreen.createWelcomeScreen();
        dispose();
    }

    /**
     * Uses the title to get the lost or won Gif. Then puts that url in a JLabel. The sets the size to
     * (main.gui.World.WIDTH * 2 + 300, main.gui.World.HEIGHT * 2 + 300);
     *
     * @param title the title to set
     */
    public void createBackgroundGif(String title) {
        URL url = null;
        try {
            if (title.equals("lost")) {
                url = new URL("file:LostGif.gif");
            } else {
                url = new URL("file:WonGif.gif");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        assert url != null;
        Icon icon = new ImageIcon(url);
        backgroundGif = new JLabel(icon);
        backgroundGif.setSize(World.WIDTH * 2 + 300, World.HEIGHT * 2 + 300);
    }

}
