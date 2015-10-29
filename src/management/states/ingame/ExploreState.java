package management.states.ingame;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import res.images.Res_FileSelect;
import res.images.Res_Inventory;
import management.floors.CurrentFloorHolder;
import management.player.PlayerInfo;
import management.states.CanvasState;
import management.states.GameState;

/**
 * Pretty much the main Canvas state. Displays the player and the environement
 * around it.
 */
public class ExploreState implements CanvasState {

    private boolean kleft;
    private boolean kright;
    private boolean kdown;
    private boolean kup;

    private GameState parent;

    public ExploreState(GameState parent) {
	this.parent = parent;
    }

    @Override
    public void print(Graphics2D g2d) {

	CurrentFloorHolder.printFloor(g2d);
	
	g2d.drawImage(Res_FileSelect.buttonZ, 215, 5, null);
	g2d.drawImage(Res_Inventory.ButtonS, 190, 19, null);
	g2d.drawImage(Res_Inventory.ButtonD, 210, 19, null);

    }

    @Override
    public void update() {
	PlayerInfo.update();

	CurrentFloorHolder.updatePlayerRoom();

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
	} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
	    parent.menustate.resetOpenAnimation();
	    parent.substate = parent.menustate;
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
