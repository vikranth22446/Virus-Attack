package main.gui;

import main.antivirus.AntiVirusManager;
import main.cell.CellManager;
import main.helper.InputHandler;
import main.helper.ScoreBoard;
import main.virus.VirusGroupManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * The Game, the running thread that runs the game begins here
 * <p>
 * creates the all the graphics objects which are retrieved from the canvas, holds runnable to create a new
 * thread to run the game functions
 *
 * @author Alex M
 */
public class World implements Runnable {

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
    private static final String NAME = "main.virus.Virus";

    private BufferedImage bg;
    /**
     * the width and height of the screen
     */
    public static int getWidth;
    public static int getHeight;
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

        /*
      the JFrame
     */
        frame = new JFrame(NAME);
        frame.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        frame.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        frame.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
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
            URL url = getClass().getResource("images/background/background.png");
            File f;
            try {
                f = new File(url.toURI());
            } catch(URISyntaxException e) {
                f = new File(url.getPath());
            }
            bg = ImageIO.read(f);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        Graphics g = frame.getGraphics();
        g.setColor(Color.white);
        frame.setBackground(Color.WHITE);
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
        frame.addMouseListener(input);
        frame.addMouseMotionListener(input);
        frame.addKeyListener(input);

        getWidth = frame.getWidth();
        getHeight = frame.getHeight();
        GAME_OVER = false;
        gameOver = "";
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
            String val = isGameOver();
            if(val.equals("win") || val.equals("lost")){
            	gameOver = val;
            	GAME_OVER = true;
            }
            
            if (GAME_OVER) {
                render(time);
                GameOverScreen gameOverScreen = new GameOverScreen();
                gameOverScreen.createScreen(gameOver, scoreBoard);
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
        }
    }

    String gameOver;

    /**
     * double buffers
     */
    private void render() {

        BufferStrategy bs = frame.getBufferStrategy();
        if (bs == null) {
            frame.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.drawImage(bg, -InputHandler.getXOffset() / 4, -InputHandler.getYOffset() / 4, null);

        vgm.draw(g, InputHandler.getXOffset(), InputHandler.getYOffset());
        avm.draw(g, InputHandler.getXOffset(), InputHandler.getYOffset());

        cellManager.toDraw(InputHandler.getXOffset(), InputHandler.getYOffset(), g);

        vgm.updateLocation(g, InputHandler.getXOffset(), InputHandler.getYOffset(), scoreBoard);
        avm.updateLocation(g, InputHandler.getXOffset(), InputHandler.getYOffset());
        g.setColor(Color.black);
        g.setFont(new Font("Tahoma", Font.BOLD, 19));
        g.drawString("Score: " + scoreBoard.getTotalScore(), WIDTH * 12 / 8, HEIGHT / 4 - 30);
        g.drawString("Time: " + scoreBoard.everTurn(), WIDTH * 12 / 8, HEIGHT / 4 - 10);


        bs.show();
    }
    
    public String isGameOver(){
    	if(CellManager.whiteValues.size() == 0){
    		return "win";
    	}
    	else if(CellManager.sickValues.size() == 0 ){
    		for(int n : vgm.getKeys()){
    			if(VirusGroupManager.groups.get(n).size() != 0){
    				return "neither";
    			}
    		}
    		return "lost";
    	}
    	return "neither";
    }
    private Thread thread;
    /**
     * the start method run from main
     */
    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
    }



}