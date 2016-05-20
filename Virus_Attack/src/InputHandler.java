package src;
import java.awt.event.*;


public class InputHandler implements MouseListener, MouseMotionListener, KeyListener{

    private int currentGroup;

    private VirusGroupManager vgm;

    public static int xOffset, yOffset;
    
    public static int xChange, yChange;
    
    public static int allow;

    public int move;
    
    public InputHandler(VirusGroupManager vgm){
        currentGroup = 1;
        this.vgm = vgm;
        
        xOffset = 0;
        yOffset = 0;
        
        xChange = 0;
        yChange = 0;
        allow = 50;

	move = 3;
    }
    
    public static int getXOffset(){
    	return xOffset;
    }
    public static int getYOffset(){
    	return yOffset;
    }
    

    public void keyPressed(KeyEvent e) {
        // 0 to create new virus at (300, 300)
        if(e.getKeyCode() == KeyEvent.VK_0){
            vgm.addVirus(200, 200);
        }
        // S to split
        if(e.getKeyCode() == KeyEvent.VK_S){
            if(vgm.groupNum() <= 6){
                vgm.split();
            }
        }
        //Different Groups
        if(e.getKeyCode() == KeyEvent.VK_1){
            vgm.changeCurrent(1);
        }
        if(e.getKeyCode() == KeyEvent.VK_2){
            vgm.changeCurrent(2);

        }
        if(e.getKeyCode() == KeyEvent.VK_3){
            vgm.changeCurrent(3);

        }
        if(e.getKeyCode() == KeyEvent.VK_4){
            vgm.changeCurrent(4);

        }
        if(e.getKeyCode() == KeyEvent.VK_5){
            vgm.changeCurrent(5);

        }
        if(e.getKeyCode() == KeyEvent.VK_6){
            vgm.changeCurrent(6);

        }
    }
    

    
	public void mouseMoved(MouseEvent e) {	
		int x = e.getX() + xOffset;
		int y = e.getY() + yOffset;
		//System.out.println(xOffset + " " + yOffset);
		
		//System.out.println(x +  " " + y);
		//System.out.println(World.getWidth + " " + World.getHeight);
		if(x <= (xOffset + allow) && xOffset  > 0){
			xChange -= move;
		}
		else if(x >= (xOffset + World.getWidth - allow) && xOffset < World.GAME_WIDTH){
			xChange += move;
		}
		else if(y <= (yOffset + allow) && yOffset > 0){
			yChange -= move;
		}
		else if(y >= (yOffset + World.getHeight - allow) && xOffset < World.GAME_HEIGHT){
			yChange += move;
		}	
		
		xOffset += xChange;
		yOffset += yChange;
		xChange = 0;
		yChange = 0;
	}
	
	public void mouseDragged(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {
        vgm.updateCoord(e.getX() + xOffset, e.getY() + yOffset);

    }


    public void mouseReleased(MouseEvent e) {}
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
    public void mouseClicked(MouseEvent arg0) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

}