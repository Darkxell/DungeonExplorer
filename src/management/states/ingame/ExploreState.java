package management.states.ingame;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import management.floors.CurrentFloorHolder;
import management.player.PlayerInfo;
import management.states.CanvasState;

/**
 * Pretty much the main Canvas state. Displays the player and the environement
 * around it.
 */
public class ExploreState implements CanvasState {

    private boolean kleft;
    private boolean kright;
    private boolean kdown;
    private boolean kup;

    @SuppressWarnings("unused")
    private CanvasState parent;

    public ExploreState(CanvasState parent) {
	this.parent = parent;
    }

    @Override
    public void print(Graphics2D g2d) {
	if (PlayerInfo.playerdirection == PlayerInfo.RIGHT) {
	    g2d.drawImage(PlayerInfo.playersprite.getCurrentSprite(), 132, 100,
		    -32, 32, null);
	} else {
	    g2d.drawImage(PlayerInfo.playersprite.getCurrentSprite(), 100, 100,
		    null);
	}

	CurrentFloorHolder.printPlayerRoom(g2d);

    }

    @Override
    public void update() {
	PlayerInfo.update();

	CurrentFloorHolder.updatePlayerRoom();

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
	if (e.getKeyCode() == KeyEvent.VK_LEFT) {
	    kleft = false;
	    PlayerInfo.leftrelease();
	} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
	    kright = false;
	    PlayerInfo.rightrelease();
	} else if (e.getKeyCode() == KeyEvent.VK_UP) {
	    kup = false;
	    PlayerInfo.uprelease();
	} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
	    kdown = false;
	    PlayerInfo.downrelease();
	}
    }

    @Override
    public void keyPressed(KeyEvent e) {
	if (e.getKeyCode() == KeyEvent.VK_LEFT) {
	    if (!kleft) {
		kleft = true;
		PlayerInfo.leftpress();
	    }
	} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
	    if (!kright) {
		kright = true;
		PlayerInfo.rightpress();
	    }
	} else if (e.getKeyCode() == KeyEvent.VK_UP) {
	    if (!kup) {
		kup = true;
		PlayerInfo.uppress();
	    }
	} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
	    if (!kdown) {
		kdown = true;
		PlayerInfo.downpress();
	    }
	}
    }

}
