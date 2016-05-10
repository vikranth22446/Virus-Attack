import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.*;

public class World extends Canvas implements Runnable {
    private int [][] setOfValues = {
            {100,200},{300,400},{400,700},{600,800}
    };
    private static final int HEIGHT = 300;
    private static final int WIDTH = 400;
    private static final int SCALE = 4;
    private static final String NAME = "Virus";

    private JFrame frame;
    private JPanel panel;

    private CellManager cellManager;
    Graphics g;

    public VirusGroupManager vgm;
    public InputHandler input;

    public World() {
        setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        frame = new JFrame(NAME);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
        input = new InputHandler(vgm);
        cellManager = new CellManager();
        createCellsInPositions();
        addMouseListener(input);
        addKeyListener(input);

    }
    public void createCellsInPositions(){
        for (int i = 0; i <setOfValues.length; i++) {
            Cell cell;
            if(i<(setOfValues.length+1)/2){
                cell = new SickCells(setOfValues[i][0],setOfValues[i][1],100,i);
            }
            else{
               cell = new WhiteCell(setOfValues[i][0],setOfValues[i][1],100,i);
            }
            cellManager.addCell(cell);
        }
    }
    public void run() {
        while (true) {
            g.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
            vgm.update(this);
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