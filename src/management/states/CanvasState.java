package management.states;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public interface CanvasState {

    /** Graphicly prints the state to the canvas using its graphic object. */
    public void print(Graphics2D g2d);

    /** Updates the state to move to the next frame. */
    public void update();

    public void keyTyped(KeyEvent e);

    public void keyReleased(KeyEvent e);

    public void keyPressed(KeyEvent e);
}
