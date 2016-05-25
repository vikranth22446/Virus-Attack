import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


public class WelcomeScreen extends JFrame {

    public void createWelcomeScreen() {
        URL url = null;
        File directory = new File("Virus_Attack");
        try {
            url = new URL("file:\\" + directory.getAbsolutePath() + "\\redBloodCellAnimation.gif");
        } catch (MalformedURLException e) {
        }
        Icon icon = new ImageIcon(url);
        JLabel label = new JLabel(icon);

        JButton jButton = new JButton();
        jButton.setSize(120, 30);
        jButton.setLocation(World.WIDTH / 4 * World.SCALE, World.HEIGHT * World.SCALE / 4 +50);
        jButton.setText("Directions");
        jButton.setBackground(new Color(20, 99, 182));
        jButton.setForeground(Color.WHITE);
        jButton.setFocusPainted(false);
        jButton.addActionListener(e -> loadDirections());
        jButton.setFont(new Font("Tahoma", Font.BOLD, 12));

        JButton Directions = new JButton();
        Directions.setSize(120, 30);
        Directions.setLocation(World.WIDTH / 4 * World.SCALE, World.HEIGHT * World.SCALE / 4 + 100);
        Directions.setText("Start Game");
        Directions.setBackground(new Color(20, 99, 182));
        Directions.setForeground(Color.WHITE);
        Directions.setFocusPainted(false);
        Directions.addActionListener(e -> run());


        add(jButton);
        add(Directions);


        getContentPane().add(label);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void loadDirections() {

    }

    private void run() {
        World world = new World();
        world.start();
        dispose();
    }

}
