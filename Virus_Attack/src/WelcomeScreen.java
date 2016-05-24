import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Vikranth on 5/24/2016.
 */
public class WelcomeScreen extends JFrame {
    ImageIcon guy = new ImageIcon("redBloodCellAnimation.gif");
    JLabel pn = new JLabel(guy);
    JPanel panel = new JPanel();

    public WelcomeScreen(){
        super("Virus Attack!!!");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(panel);
//        JLabel im = new JLabel(new ImageIcon("iconImage.gif"));
//        setIconImage(customIcon);
    //    panel.add(im);
        add(pn);
        setVisible(true);

    }

}
