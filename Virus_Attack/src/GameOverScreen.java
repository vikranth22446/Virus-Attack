import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Vikranth on 5/29/2016.
 */
public class GameOverScreen extends JFrame {
    JLabel backgroundGif;

    void createScreen(String title, ScoreBoard scoreBoard) {

        createBackgroundGif(title);

        String buttonToDisplay =
                "Total Time : " + scoreBoard.getTime() + "\n "
                + "Total White Cells Conquered: " + scoreBoard.getWhiteCellsKilled() + "\n" +
                "Total Red Cells Conquered: " + scoreBoard.getRedCellsConquered() + "\n"
                + "Total Score: " + scoreBoard.getTotalScore();
        createButtons(120, 30, World.WIDTH / 4 * World.SCALE, World.HEIGHT * World.SCALE / 4 + 130, "Try Again", false);
        createButtons(320, 130, World.WIDTH / 4 * World.SCALE - 100, World.HEIGHT * World.SCALE / 4 - 20, "<html>" + buttonToDisplay.replaceAll("\\n", "<br>") + "</html>", true);
        setTitle("Virus Attack");
        getContentPane().add(backgroundGif);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

    }

    private void createButtons(int sizeWidth, int sizeHeight, int width, int height, String title, boolean isClickable) {
        JButton jButton = new JButton();
        jButton.setSize(sizeWidth, sizeHeight);
        jButton.setLocation(width, height);
        jButton.setText(title);
        jButton.setBackground(new Color(20, 99, 182));
        jButton.setForeground(Color.WHITE);
        jButton.setFocusPainted(false);
        jButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        jButton.addActionListener(e -> newGame());
        add(jButton);
    }

    private void newGame() {
        WelcomeScreen welcomeScreen = new WelcomeScreen();
        welcomeScreen.createWelcomeScreen();
        dispose();
    }

    public void createBackgroundGif(String title) {
        URL url = null;
        try {
            if (title.equalsIgnoreCase("lost")) {
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


    private void run() {
        World world = new World();
        world.start();
        dispose();
    }
}
