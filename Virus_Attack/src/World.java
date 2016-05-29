

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The Game, the running thread that runs the game begins here
 * <p>
 * creates the all the graphics objects which are retrieved from the canvas, holds runnable to create a new
 * thread to run the game functions
 *
 * @author Alex M
 */
class World extends Canvas implements Runnable {

    /**
     * the partial width
     */
    public static final int WIDTH = 400;
    /**
     * the partial height
     */
    public static final int HEIGHT = WIDTH / 12 * 9;
    /**
     * the actual width of the game
     */
    public static final int GAME_WIDTH = WIDTH * 2 + 300;
    /**
     * the actual height of the game
     */
    public static final int GAME_HEIGHT = HEIGHT * 2 + 200;
    /**
     * the partial width and height scaled up by this value
     */
    public static final int SCALE = 2;
    /**
     * the name of the frame
     */
    private static final String NAME = "Virus";

    private BufferedImage bg;
    /**
     * the width and height of the screen
     */
    static int getWidth, getHeight;
    /**
     * game thread stops if true false if otherwise
     */
    private static boolean GAME_OVER = false;
    /**
     * the virus group manager that holds he virus groups
     */
    private final VirusGroupManager vgm;
    /**
     * the cell manager that holds the cells
     */
    private final CellManager cellManager;
    /**
     * the anti virus manager that holds the anti viruses
     */
    private final AntiVirusManager avm;
    int time = 10;
    private static InputHandler input;
    JFrame frame;

    /**
     * creates all the objects, get the canvas, sets frame size, adds listening to input handler
     */
    World() {
        setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        /*
      the JFrame
     */
        frame = new JFrame(NAME);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(this, BorderLayout.CENTER);
        frame.pack();

        /*
      the panel that contains the canvas
     */
        JPanel panel = new JPanel();
        Container c = frame.getContentPane();
        c.add(panel);

        /*
      the graphics
     */
        try {
            bg = ImageIO.read(new File("background.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        Graphics g = getGraphics();
        g.setColor(Color.white);
        setBackground(Color.WHITE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        vgm = new VirusGroupManager();
        avm = new AntiVirusManager();
        cellManager = new CellManager();
        cellManager.createCellsInPositions();

        /*
      the object that handles all the inputs
     */
        input = new InputHandler(vgm);
        addMouseListener(input);
        addMouseMotionListener(input);
        addKeyListener(input);

        getWidth = getWidth();
        getHeight = getHeight();
    }

    ScoreBoard scoreBoard;

    /**
     * @see java.lang.Runnable#run()
     */
    public void run() {
        scoreBoard = new ScoreBoard();
        while (true) {
            render();
            cellManager.produce();

            if (GAME_OVER) {
                render(time);
                GameOverScreen gameOverScreen = new GameOverScreen();
                gameOverScreen.createScreen(gameOver,scoreBoard);
                frame.dispose();
                break;
            }

            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void render(int time) {
        for (int i = time; time > 0; i--) {
            render();
            try {
                Thread.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i == 0) {
                break;
            }
            System.out.println(i);
        }
    }

    String gameOver;

    /**
     * double buffers
     */
    private void render() {
        String value = scoreBoard.isGameOver(vgm, cellManager);
        if (value.equalsIgnoreCase("lost") || value.equalsIgnoreCase("won")) {
            gameOver = value;
            GAME_OVER = true;
            return;
        }
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        //g.setColor(Color.white);
        // g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(bg, -input.getXOffset() / 4, -input.getYOffset() / 4, null);

        vgm.draw(g, input.getXOffset(), input.getYOffset());
        avm.draw(g, input.getXOffset(), input.getYOffset());

        cellManager.toDraw(input.getXOffset(), input.getYOffset(), g);

        vgm.updateLocation(g, input.getXOffset(), input.getYOffset(), scoreBoard);
        avm.updateLocation(g, input.getXOffset(), input.getYOffset());
        g.setColor(Color.black);
        g.setFont(new Font("Tahoma", Font.BOLD, 19));
        g.drawString("Score: " + scoreBoard.getTotalScore(), WIDTH * 12 / 8, HEIGHT / 4 - 30);
        g.drawString("Time: " + scoreBoard.everTurn(), WIDTH * 12 / 8, HEIGHT / 4 - 10);


        bs.show();
    }

    /**
     * the start method run from main
     */
    synchronized void start() {
        new Thread(this).start();
    }


}