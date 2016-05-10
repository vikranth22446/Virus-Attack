
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class InputHandler implements MouseListener, KeyListener{

    private int currentGroup;

    private VirusGroupManager vgm;

    public InputHandler(VirusGroupManager vgm){
        currentGroup = 1;
        this.vgm = vgm;
    }

    public void keyPressed(KeyEvent e) {
        // 0 to create new virus at (300, 300)
        if(e.getKeyCode() == KeyEvent.VK_0){
            vgm.addVirus(currentGroup);
        }
        // S to split
        if(e.getKeyCode() == KeyEvent.VK_S){
            if(vgm.groupNum() <= 6){
                vgm.split(currentGroup);
            }
        }
        //Different Groups
        if(e.getKeyCode() == KeyEvent.VK_1){
            currentGroup = 1;
        }
        if(e.getKeyCode() == KeyEvent.VK_2){
            currentGroup = 2;
        }
        if(e.getKeyCode() == KeyEvent.VK_3){
            currentGroup = 3;
        }
        if(e.getKeyCode() == KeyEvent.VK_4){
            currentGroup = 4;
        }
        if(e.getKeyCode() == KeyEvent.VK_5){
            currentGroup = 5;
        }
        if(e.getKeyCode() == KeyEvent.VK_6){
            currentGroup = 6;
        }
    }

    public void mousePressed(MouseEvent e) {
        vgm.updateCoord(currentGroup, e.getX(), e.getY());

    }


    public void mouseReleased(MouseEvent e) {}
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
    public void mouseClicked(MouseEvent arg0) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}



