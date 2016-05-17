
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

    public void mousePressed(MouseEvent e) {
        vgm.updateCoord(e.getX(), e.getY());

    }


    public void mouseReleased(MouseEvent e) {}
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
    public void mouseClicked(MouseEvent arg0) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}