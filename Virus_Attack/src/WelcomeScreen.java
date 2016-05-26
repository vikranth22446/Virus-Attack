import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;


public class WelcomeScreen extends JFrame {

    public void createWelcomeScreen() {
        URL url = null;
        try {
            System.out.println(WelcomeScreen.class.getProtectionDomain().getCodeSource().getLocation().getPath());
            url = new URL("http://i.imgur.com/0JGPsWt.gif");
        } catch (MalformedURLException e) {
        }
        Icon icon = new ImageIcon(url);
        JLabel label = new JLabel(icon);
        label.setSize(World.WIDTH * 2 + 300, World.HEIGHT * 2 + 300);

        JButton saveData = new JButton();
        saveData.setSize(120, 30);
        saveData.setLocation(World.WIDTH / 4 * World.SCALE, World.HEIGHT * World.SCALE / 4 +20);
        saveData.setText("Log in");
        saveData.setBackground(new Color(20, 99, 182));
        saveData.setForeground(Color.WHITE);
        saveData.setFocusPainted(false);
        saveData.addActionListener(e -> loadDirections());
        saveData.setFont(new Font("Tahoma", Font.BOLD, 12));





        JButton jButton = new JButton();
        jButton.setSize(120, 30);
        jButton.setLocation(World.WIDTH / 4 * World.SCALE, World.HEIGHT * World.SCALE / 4 + 60);
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

        add(saveData);
        add(jButton);
        add(Directions);

        setTitle("Virus Attack");
        getContentPane().add(label);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

    }

    private void loadDirections() {

    }

    private void run() {
        World world = new World();
        world.start();
        dispose();
    }

}
