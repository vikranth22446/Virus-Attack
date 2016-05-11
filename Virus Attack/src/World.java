import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class World extends Canvas implements Runnable {

    private static final int HEIGHT = 400;
    public static final Color BCOLOR = Color.green;
    private static final int WIDTH = 400;
    private static final int SCALE = 4;
    private static final String NAME = "Virus";

    private JFrame frame;
    private JPanel panel;

    Graphics g;

    private CellManager cellManager;
    private AntiVirusManager avm;
    public VirusGroupManager vgm;
    public InputHandler input;


    public World() {
        setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        frame = new JFrame(NAME);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(this, BorderLayout.CENTER);
        frame.pack();

        panel = new JPanel();
        Container c = frame.getContentPane();
        c.add(panel);

        g = getGraphics();
        g.setColor(Color.white);
        setBackground(Color.WHITE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        vgm = new VirusGroupManager();
        avm = new AntiVirusManager();
        cellManager = new CellManager();
        cellManager.createCellsInPositions();

        input = new InputHandler(vgm);
        addMouseListener(input);
        addKeyListener(input);


    }


    public void run() {
        while (true) {

            g.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);


            vgm.update(this);
            avm.update(this);
            avm.checkDead(this);
            cellManager.updateViruses(vgm, this);
            cellManager.updateDrawing(this);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void start() {
        new Thread(this).start();
    }

}