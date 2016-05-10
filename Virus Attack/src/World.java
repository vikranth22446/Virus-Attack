import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class World extends Canvas implements Runnable {

    private static final int HEIGHT = 300;
    public static final Color BCOLOR = Color.green;
    private static final int WIDTH = 400;
    private static final int SCALE = 2;
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
        setBackground( Color.green );
        g.setColor( Color.green );
        g.fillRect( 0, 0, WIDTH * SCALE, HEIGHT * SCALE);
        World w = this;
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                vgm.update(w);
                avm.update(w);
                avm.checkDead(w);
                cellManager.updateViruses( vgm, w );
                cellManager.updateDrawing(w);

            }
        };
        Timer timer = new Timer(100 ,taskPerformer);
        timer.setRepeats(true);
        timer.start();

        try
        {
            Thread.sleep(100);
        }
        catch ( InterruptedException e1 )
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

//        while (true) {
//
//           // g.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
//
//
//
//
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public synchronized void start() {
        new Thread(this).start();
    }

}
