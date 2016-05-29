


import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;


class WelcomeScreen extends JFrame {
    JLabel backgroundGif;
    private JButton firstButton;
    private JButton secondButton;

    void createWelcomeScreen() {

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

    private void createButtons(int width, int height, String title) {
        JButton jButton = new JButton();
        jButton.setSize(120, 30);
        jButton.setLocation(width, height);
        jButton.setText(title);
        jButton.setBackground(new Color(20, 99, 182));
        jButton.setForeground(Color.WHITE);
        jButton.setFocusPainted(false);
        jButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        if (title.equalsIgnoreCase("Start Game")) {
            jButton.addActionListener(e -> run());
            firstButton = jButton;
        } else {
            jButton.addActionListener(e -> loadDirections());
            secondButton = jButton;
        }
        add(jButton);
    }

    public void createBackgroundGif() {
        URL url = null;
        try {
            url = new URL("file:giphy.gif");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        assert url != null;
        Icon icon = new ImageIcon(url);
        backgroundGif = new JLabel(icon);
        backgroundGif.setSize(World.WIDTH * 2 + 300, World.HEIGHT * 2 + 300);
    }

    private void loadDirections() {
        createFirstMessage();
        createSickAndVirusMessage();
        createWhiteAndAntiVirusMessage();
        createRedCellMessage();
        createMapMessage();
    }

    private void createRedCellMessage() {
        String pt1 = "<html><body width='";
        String pt2 =
                "'><h1>Instructions</h1>" +
                        "<h2>Red Cell</h2>" +
                        "<img  height='50' width='50'" +
                        "src='https://goo.gl/nYI0cS' />" +
                        "<br />" + "" +
                        "These are the Cells you want to Conquer" +
                        "<br />When Viruses attack this Cell it looses health<br />" +
                        " When health becomes 0 it turns into a Sick Cell ";
        int width = 400;
        String s = pt1 + width + pt2;

        JOptionPane.showMessageDialog(null, s);
    }
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
                        "Conquer all the redCells"+
                        "<h2>Have Fun!!!</h2>";
        int width = 400;
        String s = pt1 + width + pt2;

        JOptionPane.showMessageDialog(null, s);
    }

    private void createWhiteAndAntiVirusMessage() {
        String pt1 = "<html><body width='";
        String pt2 =
                "'><h1>Instructions</h1>" +
                        "<h2>White Cell</h2>" +
                        "<img  height='50' width='50'" +
                        "src='https://goo.gl/G3tSWo' />" +
                        "<br />" + "" +
                        "These are the main opponents of the game." +
                        "<br /> The can Move around and attack Virus.<br />" +
                        " They can also heal sick cells to become Red Cells " +
                        "<br />When they go to 0 health they die" +
                        "<br />They have this special ability of going through mitosis and splitting<br />" +
                        "<em><strong>The Cells Produce AntiViruses</strong></em>" +
                        "<h2>AntiVirus</h2><br /> " +
                        "<br />" +
                        "AntiViruses slow down and damage the viruses "
                        + "<br /> They stay in one spot till a virus comes in their range and then attacks" +
                        "<br />They have very little health";
        int width = 400;
        String s = pt1 + width + pt2;

        JOptionPane.showMessageDialog(null, s);
    }

    private void createSickAndVirusMessage() {
        String pt1 = "<html><body width='";
        String pt2 =
                "'><h1>Instructions</h1>" +
                        "<h2>Sick Cell</h2>" +
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
                        "Without viruses you cannot win the game. "
                        + "<br />These Viruses can move around and attack your opponents" +
                        "<ul>" +
                        "<li>White Cell</li>" +
                        "<li>Red Cell</li>" +
                        "<li>AntiVirus</li>" +
                        "</ul>";
        int width = 400;
        String s = pt1 + width + pt2;

        JOptionPane.showMessageDialog(null, s);
    }

    private void createFirstMessage() {
        String pt1 = "<html><body width='";
        String pt2 =
                "'><h1>Instructions</h1>" +
                        "<h3>Than you for choosing to play our game!!!</h3>" +
                        "This Game Contains 5 main game peices" +
                        "<br/>" +
                        "<ul>" +
                        "<li>Sick Cell</li>" +
                        "<li>Red Cell</li>" +
                        "<li>White Cell</li>" +
                        "<li>Virus</li>" +
                        "<li>AntiVirus</li>" +
                        "</ul>" +
                        "<h3>What do these pieces do?<h3>" +
                        "";

        int width = 400;
        String s = pt1 + width + pt2;

        JOptionPane.showMessageDialog(null, s);
    }

    private void run() {
        World world = new World();
        world.start();
        dispose();
    }


}
