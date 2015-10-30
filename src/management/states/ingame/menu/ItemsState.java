package management.states.ingame.menu;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import display.sprites.misc.InventoryCursor;
import display.sprites.misc.JarItem;
import res.images.Res_Inventory;
import util.ItemsUtility;
import management.player.Inventory;
import management.player.PlayerInfo;
import management.states.CanvasState;
import management.states.ingame.MenuState;

public class ItemsState implements CanvasState {

    private MenuState parent;
    private int cursorposition;

    public ItemsState(MenuState parent) {
	this.parent = parent;
    }

    @Override
    public void print(Graphics2D g2d) {
	g2d.drawImage(Res_Inventory.ItemsOverlay, 15, 20, null);
	g2d.drawImage(Res_Inventory.ItemsTitle, 0, 5 - parent.titleoffset, null);
	for (int i = 0; i < 4; i++)
	    for (int j = 0; j < 4; j++) {
		if (isInHand(i * 4 + j))
		    if (i != 3)
			g2d.drawImage(Res_Inventory.activeItemBG, j * 36 + 53,
				i * 24 + 41, null);
		    else
			g2d.drawImage(Res_Inventory.activeItemBG, j * 25 + 51,
				113, null);
	    }
	drawItems(g2d);
	if (cursorposition < 12) {
	    int cX = 52 + ((cursorposition % 4) * 36);
	    int cY = 40 + (((int) (cursorposition / 4)) * 24);
	    InventoryCursor
		    .drawCursor(g2d, cX, cY, 28, 21, InventoryCursor.RED);
	} else if (cursorposition < 16) {
	    InventoryCursor.drawCursor(g2d, 52 + ((cursorposition - 12) * 25),
		    111, 25, 21, InventoryCursor.RED);
	} else {
	    InventoryCursor.drawCursor(g2d, 157, 112, 50, 21,
		    InventoryCursor.GREEN);
	}

	g2d.drawImage(Res_Inventory.ButtonLeft,
		(parent.sidesoffset > 15) ? parent.sidesoffset - 30
			: -parent.sidesoffset, 70, null);
	g2d.drawImage(Res_Inventory.ButtonRight,
		(parent.sidesoffset > 15) ? 249 - parent.sidesoffset
			: 219 + parent.sidesoffset, 70, null);
	drawHandButtons(g2d);
    }

    @Override
    public void update() {
	JarItem.update();
    }

    /**
     * Draws the two buttons and the assimilated objects on the top right
     * corner.
     */
    private void drawHandButtons(Graphics2D g2d) {
	g2d.drawImage(Res_Inventory.ButtonS, 170, 13, null);
	g2d.drawImage(ItemsUtility.getSpriteFromID(PlayerInfo.hand_S_itemID),
		160, 3, null);
	g2d.drawImage(Res_Inventory.ButtonD, 200, 13, null);
	g2d.drawImage(ItemsUtility.getSpriteFromID(PlayerInfo.hand_D_itemID),
		190, 3, null);
    }

    /** Draws all of the items in the state if they need to be drawn. */
    private void drawItems(Graphics2D g2d) {
	g2d.drawImage(ItemsUtility.getSpriteFromID(Inventory.ITEM_SWORD), 58,
		42, null);
	g2d.drawImage(ItemsUtility.getSpriteFromID(Inventory.ITEM_GUSTJAR), 94,
		42, null);
	g2d.drawImage(ItemsUtility.getSpriteFromID(Inventory.ITEM_STICK), 130,
		42, null);
	g2d.drawImage(ItemsUtility.getSpriteFromID(Inventory.ITEM_BOOMERANG),
		166, 42, null);
	g2d.drawImage(ItemsUtility.getSpriteFromID(Inventory.ITEM_SHIELD), 58,
		66, null);
	g2d.drawImage(ItemsUtility.getSpriteFromID(Inventory.ITEM_MITS), 94,
		66, null);
	g2d.drawImage(ItemsUtility.getSpriteFromID(Inventory.ITEM_LANTERN),
		130, 66, null);
	g2d.drawImage(ItemsUtility.getSpriteFromID(Inventory.ITEM_BOMBS), 166,
		66, null);
	g2d.drawImage(ItemsUtility.getSpriteFromID(Inventory.ITEM_BOOTS), 58,
		90, null);
	g2d.drawImage(ItemsUtility.getSpriteFromID(Inventory.ITEM_CAPE), 94,
		90, null);
	g2d.drawImage(ItemsUtility.getSpriteFromID(Inventory.ITEM_OCARINA),
		130, 90, null);
	g2d.drawImage(ItemsUtility.getSpriteFromID(Inventory.ITEM_BOW), 166,
		90, null);
	g2d.drawImage(
		JarItem.getJarSprite(PlayerInfo.playerInventory.level_jar1),
		58, 113, null);
	g2d.drawImage(
		JarItem.getJarSprite(PlayerInfo.playerInventory.level_jar2),
		83, 113, null);
	g2d.drawImage(
		JarItem.getJarSprite(PlayerInfo.playerInventory.level_jar3),
		108, 113, null);
	g2d.drawImage(
		JarItem.getJarSprite(PlayerInfo.playerInventory.level_jar4),
		133, 113, null);
    }

    /**
     * Returns true if the item at the <code>itemposition</code> position in the
     * inventory is in a player hand.
     */
    private boolean isInHand(int itemposition) {
	return (itemposition == PlayerInfo.hand_D_itemID || itemposition == PlayerInfo.hand_S_itemID);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
	if (e.getKeyCode() == KeyEvent.VK_S && cursorposition != 16) {
	    if (PlayerInfo.hand_D_itemID != cursorposition)
		PlayerInfo.hand_S_itemID = cursorposition;
	    else {
		PlayerInfo.hand_D_itemID = PlayerInfo.hand_S_itemID;
		PlayerInfo.hand_S_itemID = cursorposition;
	    }

	} else if (e.getKeyCode() == KeyEvent.VK_D && cursorposition != 16) {
	    if (PlayerInfo.hand_S_itemID != cursorposition)
		PlayerInfo.hand_D_itemID = cursorposition;
	    else {
		PlayerInfo.hand_S_itemID = PlayerInfo.hand_D_itemID;
		PlayerInfo.hand_D_itemID = cursorposition;
	    }

	} else if (e.getKeyCode() == KeyEvent.VK_A) {
	    parent.resetOpenAnimation();
	    parent.SubState = parent.S_MAP;
	} else if (e.getKeyCode() == KeyEvent.VK_Z) {
	    parent.resetOpenAnimation();
	    parent.SubState = parent.S_SUBITEMS;
	} else if (e.getKeyCode() == KeyEvent.VK_UP) {
	    cursorposition -= 4;
	    switch (cursorposition) {
	    case -4:
		cursorposition = 12;
		break;
	    case -3:
		cursorposition = 13;
		break;
	    case -2:
		cursorposition = 14;
		break;
	    case -1:
		cursorposition = 16;
		break;
	    case 12:
		cursorposition = 11;
		break;
	    }
	} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
	    cursorposition += 4;
	    switch (cursorposition) {
	    case 15:
		cursorposition = 16;
		break;
	    case 16:
		cursorposition = 0;
		break;
	    case 17:
		cursorposition = 1;
		break;
	    case 18:
		cursorposition = 2;
		break;
	    case 19:
		cursorposition = 2;
		break;
	    case 20:
		cursorposition = 3;
		break;
	    }
	} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
	    --cursorposition;
	    switch (cursorposition) {
	    case -1:
		cursorposition = 3;
		break;
	    case 3:
		cursorposition = 7;
		break;
	    case 7:
		cursorposition = 11;
		break;
	    case 11:
		cursorposition = 16;
		break;
	    }
	} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
	    ++cursorposition;
	    switch (cursorposition) {
	    case 4:
		cursorposition = 0;
		break;
	    case 8:
		cursorposition = 4;
		break;
	    case 12:
		cursorposition = 8;
		break;
	    case 17:
		cursorposition = 12;
		break;
	    }
	}
    }

}
