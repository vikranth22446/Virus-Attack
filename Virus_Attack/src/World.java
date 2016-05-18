package src;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class World extends Canvas implements Runnable {

    public static final Color BCOLOR = Color.green;
    public static final int WIDTH = 400;
    public static final int HEIGHT = WIDTH/12 * 9;

    public static final int SCALE = 2;
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
            render();
            cellManager.produce();

            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void render(){
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());

        vgm.draw(g);
        avm.draw(g);
        cellManager.draw(g);

        vgm.updateLocation(g);
        avm.updateLocation(g);

        bs.show();
    }

    public synchronized void start() {
        new Thread(this).start();
    }


}