package management.states.ingame.menu;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import res.images.Res_Inventory;
import management.states.CanvasState;
import management.states.ingame.MenuState;

public class SubItemsState implements CanvasState {

    private MenuState parent;

    public SubItemsState(MenuState parent) {
	this.parent = parent;
    }

    @Override
    public void print(Graphics2D g2d) {
	g2d.drawImage(Res_Inventory.SubItemsOverlay, 15, 20, null);
	g2d.drawImage(Res_Inventory.SubItemsTitle, 0, 5 - parent.titleoffset,
		null);
	g2d.drawImage(Res_Inventory.ButtonLeft,
		(parent.sidesoffset > 15) ? parent.sidesoffset - 30
			: -parent.sidesoffset, 70, null);
	g2d.drawImage(Res_Inventory.ButtonRight,
		(parent.sidesoffset > 15) ? 249 - parent.sidesoffset
			: 219 + parent.sidesoffset, 70, null);
    }

    @Override
    public void update() {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
	if (e.getKeyCode() == KeyEvent.VK_A) {
	    parent.resetOpenAnimation();
	    parent.SubState = parent.S_ITEMS;
	} else if (e.getKeyCode() == KeyEvent.VK_Z) {
	    parent.resetOpenAnimation();
	    parent.SubState = parent.S_MAP;
	}
    }

}
