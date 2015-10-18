package management.states;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import management.states.ingame.ExploreState;
import management.states.ingame.MenuState;

/**The GameState. Basically displays the whole game.*/
public class GameState implements CanvasState {
    
    public MenuState menustate = new MenuState(this);
    public ExploreState explorestate = new ExploreState(this);
    
    public CanvasState substate = explorestate;
    
    @Override
    public void print(Graphics2D g2d) {
	substate.print(g2d);
    }

    @Override
    public void update() {
	substate.update();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
	substate.mouseReleased(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
	substate.mousePressed(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
	substate.mouseExited(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
	substate.mouseEntered(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
	substate.mouseClicked(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
	substate.mouseMoved(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
	substate.mouseDragged(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
	substate.keyTyped(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
	substate.keyReleased(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
	substate.keyPressed(e);
    }

}
