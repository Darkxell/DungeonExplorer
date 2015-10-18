package management.states;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface CanvasState {

    /** Graphicly prints the state to the canvas using its graphic object. */
    public void print(Graphics2D g2d);

    /** Updates the state to move to the next frame. */
    public void update();

    public void mouseReleased(MouseEvent e);

    public void mousePressed(MouseEvent e);

    public void mouseExited(MouseEvent e);

    public void mouseEntered(MouseEvent e);

    public void mouseClicked(MouseEvent e);

    public void mouseMoved(MouseEvent e);

    public void mouseDragged(MouseEvent e);

    public void keyTyped(KeyEvent e);

    public void keyReleased(KeyEvent e);

    public void keyPressed(KeyEvent e);
}
