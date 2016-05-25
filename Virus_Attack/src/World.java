
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * The Game, the running thread that runs the game begins here
 * 
 * creates the all the graphics objects which are retrieved from the canvas, holds runnable to create a new 
 * thread to run the game functions
 * 
 * @author Alex M
 *
 */
public class World extends Canvas implements Runnable {

    /**
     * the color green
     */
    public static final Color BCOLOR = Color.green;
    /**
     * the partial width
     */
    public static final int WIDTH = 400;
    /**
     * the partial height
     */
    public static final int HEIGHT = WIDTH/12 * 9;
    /**
     * the actual width of the game
     */
    public static final int GAME_WIDTH = WIDTH * 2 + 300;
    /**
     * the actual height of the game
     */
    public static final int GAME_HEIGHT = HEIGHT * 2 + 200;
    
    /**
     * the width and height of the screen
     */
    public static int getWidth, getHeight;
    /**
     * the partial width and height scaled up by this value
     */
    public static final int SCALE = 2;
    /**
     * the name of the frame
     */
    private static final String NAME = "Virus";
    
    /**
     * game thread stops if true false if otherwise
     */
    public static boolean GAME_OVER = false;

    /**
     * the Jframe
     */
    private JFrame frame;
    /**
     * the panel that contains the canvas
     */
    private JPanel panel;

    /**
     * the graphics
     */
    Graphics g;

    /**
     * the cell manager that holds the cells
     */
    private CellManager cellManager;
    /**
     * the anti virus manager that holds the anti viruses
     */
    private AntiVirusManager avm;
    /**
     * the virus group manager that holds he virus groups
     */
    public VirusGroupManager vgm;
    /**
     * the object that handles all the inputs
     */
    public InputHandler input;


    /**
     * creates all the objects, get the canvas, sets frame size, adds listening to input handler 
     */
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
        addMouseMotionListener(input);
        addKeyListener(input);
        
        getWidth = getWidth();
        getHeight = getHeight();
    }
    

    
    /**
     * @see java.lang.Runnable#run()
     */
    public void run() {
        while (true) {
            render();
            cellManager.produce();
            
            if(GAME_OVER) break;

            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * double buffers
     */
    public void render(){
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());

        vgm.draw(g, input.getXOffset(), input.getYOffset());
        avm.draw(g, input.getXOffset(), input.getYOffset());
        //cellManager.draw(g);
        cellManager.toDraw(input.getXOffset(), input.getYOffset(), g);

        vgm.updateLocation(g, input.getXOffset(), input.getYOffset());
        avm.updateLocation(g, input.getXOffset(), input.getYOffset());

        bs.show();
    }

    /**
     * the start method run from main
     */
    public synchronized void start() {
        new Thread(this).start();
    }


}