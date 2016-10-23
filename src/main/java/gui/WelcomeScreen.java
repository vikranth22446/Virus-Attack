package gui;


import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Creates a Welcome screen for user to click to start Game. If they click
 * directions a set of pop ups will appear explaining the game
 *
 * @author Vikranth Srivatsa
 */
public class WelcomeScreen extends JFrame {
    /**
     * The background gif.
     */
    JLabel backgroundGif;

    /**
     * calls createBackgroundGif();
     * Also calls Create Buttons 40 y from each other with titles directions and start game
     * initializes the frame by setting title adding gif, packing,setting it visible, and making sure it is also not resiable.
     * <p>
     * It also adds backgroundGif to the plane
     */
    public void createWelcomeScreen() {

        createBackgroundGif();

        createButtons(World.WIDTH / 4 * World.SCALE, World.HEIGHT * World.SCALE / 4 + 100, "Start Game");
        createButtons(World.WIDTH / 4 * World.SCALE, World.HEIGHT * World.SCALE / 4 + 60, "Directions");


        setTitle("Virus Attack");
        getContentPane().add(backgroundGif);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

    }

    /**
     * Create a button of a certian title,width and height. size 120.30
     * Set the background to a shade of blue and text to white. Add a action listener to run if the title is start game
     * or else call loadDirections
     * Then add the button
     *
     * @param x     the width to set
     * @param y     the height to set
     * @param title the titile to set
     */
    private void createButtons(int x, int y, String title) {
        JButton jButton = new JButton();
        jButton.setSize(120, 30);
        jButton.setLocation(x, y);
        jButton.setText(title);
        jButton.setBackground(new Color(20, 99, 182));
        jButton.setForeground(Color.WHITE);
        jButton.setFocusPainted(false);
        jButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        if (title.equalsIgnoreCase("Start Game")) {
            jButton.addActionListener(e -> run());
        } else {
            jButton.addActionListener(e -> loadDirections());
        }
        add(jButton);
    }

    /**
     * This get's the url from the file giphy.gif. Then it puts that into a JLabel
     * of size main.gui.World.WIDTH * 2 + 300, main.gui.World.HEIGHT * 2 + 300
     */
    public void createBackgroundGif() {
        String fileName = "images/background/giphy.gif";
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        URL url = classLoader.getResource(fileName);
        assert url != null;
        Icon icon = new ImageIcon(url);
        backgroundGif = new JLabel(icon);
        backgroundGif.setSize(World.WIDTH * 2 + 300, World.HEIGHT * 2 + 300);
    }

    /**
     * Calls the list of directions
     */
    private void loadDirections() {
        createFirstMessage();
        createSickAndVirusMessage();
        createWhiteAndAntiVirusMessage();
        createRedCellMessage();
        createMapMessage();
        createPointsSetUP();
    }

    /**
     * uses JOptionPane to give info on main.cell.RedCell
     */
    private void createRedCellMessage() {
        String pt1 = "<html><body width='";
        String pt2 =
                "'><h1>Instructions</h1>" +
                        "<h2>Red main.cell.Cell</h2>" +
                        "<img  height='50' width='50'" +
                        "src='https://goo.gl/nYI0cS' />" +
                        "<br />" + "" +
                        "These are the Cells you want to Conquer" +
                        "<br />When Viruses attack this main.cell.Cell it looses health<br />" +
                        " When health becomes 0 it turns into a Sick main.cell.Cell ";
        int width = 400;
        String s = pt1 + width + pt2;

        JOptionPane.showMessageDialog(null, s);
    }

    /**
     * uses JOptionPane to give info on Map
     */
    private void createMapMessage() {
        String pt1 = "<html><body width='";
        String pt2 =
                "'><h1>Instructions</h1>" +
                        "<h2>The Map</h2>" +
                        "<br />" + "" +
                        "When the game first starts you will be presented with a map" +
                        "<br /> This Map contains some sick cells and some white cells<br />" +
                        " The Red Cells will be randomly generated all over the map"
                        + "<h2>Objective</h2>" +
                        "<br />" + "" +
                        "To Defeat all the White Cells" +
                        "<br /> or <br />" +
                        "Conquer all the redCells<br />" +
                        "<h2>Also Get the Highest Points!!!</h2>" +
                        "<h2>How do points work?</h2>";
        int width = 400;
        String s = pt1 + width + pt2;

        JOptionPane.showMessageDialog(null, s);
    }

    /**
     * uses JOptionPane to give info on the Point System
     */
    private void createPointsSetUP() {
        String pt1 = "<html><body width='";
        String pt2 =
                "'><h1>Instructions</h1>" +
                        "<h2>Points</h2>" +
                        "<br />" + "" +
                        "If you kill a white main.cell.Cell you gain 10 points" +
                        "<br />If you convert a red cell to sick you gain 5 points <br />" +
                        " " +
                        "Every 30 seconds you will loose 1 point"
                        + "<h2>Score Board</h2>" +
                        "<br />" + "" +
                        "The ScoreBroad displays the time" +
                        "<br />" +
                        "and The Total Points" +
                        "<h2>Have Fun!!!</h2>" +
                        "<br /> <h2>Get the Most Points!!!</h2>";
        int width = 400;
        String s = pt1 + width + pt2;

        JOptionPane.showMessageDialog(null, s);
    }

    /**
     * uses JOptionPane to give info on main.cell.WhiteCell and AntiViruses
     */
    private void createWhiteAndAntiVirusMessage() {
        String pt1 = "<html><body width='";
        String pt2 =
                "'><h1>Instructions</h1>" +
                        "<h2>White main.cell.Cell</h2>" +
                        "<img  height='50' width='50'" +
                        "src='https://goo.gl/G3tSWo' />" +
                        "<br />" + "" +
                        "These are the main opponents of the game." +
                        "<br /> The can Move around and attack main.virus.Virus.<br />" +
                        " They can also heal sick cells to become Red Cells " +
                        "<br />When they go to 0 health they die" +
                        "<br />They have this special ability of going through mitosis and splitting<br />" +
                        "<em><strong>The Cells Produce AntiViruses</strong></em>" +
                        "<h2>main.antivirus.AntiVirus</h2><br /> " +
                        "<br />" +
                        "AntiViruses slow down and damage the images.background.viruses "
                        + "<br /> They stay in one spot till a virus comes in their range and then attacks" +
                        "<br />They have very little health";
        int width = 400;
        String s = pt1 + width + pt2;

        JOptionPane.showMessageDialog(null, s);
    }

    /**
     * uses JOptionPane to give info on Sick main.cell.Cell and Viruses
     */
    private void createSickAndVirusMessage() {
        String pt1 = "<html><body width='";
        String pt2 =
                "'><h1>Instructions</h1>" +
                        "<h2>Sick main.cell.Cell</h2>" +
                        "<img  height='50' width='50'" +
                        "src='https://goo.gl/t5l0Si' />" +
                        "<br />" + "" +
                        "These are the main pieces on your team." +
                        "<br />When the game Starts there will be only a few of these " +
                        "but these<br /><em><strong> Sick Cells Can Produce Viruses.</strong></em><br /> " +
                        "<h2>Viruses</h2><br /> " +
                        "<img  height='50' width='50'" +
                        "src='https://goo.gl/zVM3Tm' />" +
                        "<br />" +
                        "Without images.background.viruses you cannot win the game. "
                        + "<br />These Viruses can move around and attack your opponents" +
                        "<ul>" +
                        "<li>White main.cell.Cell</li>" +
                        "<li>Red main.cell.Cell</li>" +
                        "<li>main.antivirus.AntiVirus</li>" +
                        "</ul>";
        int width = 400;
        String s = pt1 + width + pt2;

        JOptionPane.showMessageDialog(null, s);
    }

    /**
     * uses JOptionPane to give info on the preview of the game
     */
    private void createFirstMessage() {
        String pt1 = "<html><body width='";
        String pt2 =
                "'><h1>Instructions</h1>" +
                        "<h3>Than you for choosing to play our game!!!</h3>" +
                        "This Game Contains 5 main game peices" +
                        "<br/>" +
                        "<ul>" +
                        "<li>Sick main.cell.Cell</li>" +
                        "<li>Red main.cell.Cell</li>" +
                        "<li>White main.cell.Cell</li>" +
                        "<li>main.virus.Virus</li>" +
                        "<li>main.antivirus.AntiVirus</li>" +
                        "</ul>" +
                        "<h3>What do these pieces do?<h3>" +
                        "";

        int width = 400;
        String s = pt1 + width + pt2;

        JOptionPane.showMessageDialog(null, s);
    }

    /**
     * This is called by the start game button. It starts a new screen called world that runs the game.
     */
    private void run() {
        World world = new World();
        world.start();
        dispose();
    }


}
