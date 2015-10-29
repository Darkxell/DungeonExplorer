package management.states.ingame;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import display.sprites.misc.InventoryCursor;
import res.images.Res_Inventory;
import management.states.CanvasState;
import management.states.GameState;
import management.states.ingame.menu.ItemsState;
import management.states.ingame.menu.MapState;
import management.states.ingame.menu.SubItemsState;

/** The inventory/maps state, basically the menu. */
public class MenuState implements CanvasState {

    private GameState parent;
    public int titleoffset = 27;
    public int sidesoffset = 30;

    public ItemsState S_ITEMS = new ItemsState(this);
    public SubItemsState S_SUBITEMS = new SubItemsState(this);
    public MapState S_MAP = new MapState(this);

    public CanvasState SubState = S_ITEMS;

    public MenuState(GameState parent) {
	this.parent = parent;
    }

    @Override
    public void print(Graphics2D g2d) {
	g2d.drawImage(Res_Inventory.background, 0, 0, null);
	this.SubState.print(g2d);
    }

    @Override
    public void update() {
	InventoryCursor.update();
	this.SubState.update();
	if (titleoffset > 0)
	    titleoffset -= 2;
	if (sidesoffset > 0)
	    sidesoffset -= 2;
    }

    /**
     * Resets the vertical offset of the title. Also resets the side buttons
     * animation.
     */
    public void resetOpenAnimation() {
	this.titleoffset = 27;
	this.sidesoffset = 30;
    }

    @Override
    public void keyTyped(KeyEvent e) {
	SubState.keyTyped(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
	if (e.getKeyCode() == KeyEvent.VK_ENTER) {
	    this.parent.substate = this.parent.explorestate;
	}
	SubState.keyReleased(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
	SubState.keyPressed(e);
    }

}
