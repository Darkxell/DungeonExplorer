package management.states.ingame.menu;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import res.images.Res_Inventory;
import res.images.Res_Map;
import management.player.PlayerInfo;
import management.states.CanvasState;
import management.states.ingame.MenuState;

public class MapState implements CanvasState {

	private MenuState parent;

	public MapState(MenuState parent) {
		this.parent = parent;
	}

	@Override
	public void print(Graphics2D g2d) {
		g2d.drawImage(Res_Inventory.MapOverlay, 7, 2, null);
		g2d.drawImage(Res_Inventory.MapTitle, 0, 5 - parent.titleoffset, null);
		g2d.drawImage(Res_Inventory.ButtonLeft,
				(parent.sidesoffset > 15) ? parent.sidesoffset - 30 : -parent.sidesoffset, 70, null);
		g2d.drawImage(Res_Inventory.ButtonRight,
				(parent.sidesoffset > 15) ? 249 - parent.sidesoffset : 219 + parent.sidesoffset, 70, null);
		if (PlayerInfo.playerInventory.dungeon_hasmap)
			g2d.drawImage(Res_Map.icon_map, 15, 113, null);
		if (PlayerInfo.playerInventory.dungeon_hasbosskey)
			g2d.drawImage(Res_Map.icon_bosskey, 38, 113, null);
		if (PlayerInfo.playerInventory.dungeon_hascompass) {
			g2d.drawImage(Res_Map.icon_compass, 60, 113, null);
			g2d.drawImage(bossblink < BOSSBMAX / 2 ? Res_Map.boss_big_1 : Res_Map.boss_big_2, 66, 64, null);
		}
		g2d.drawImage(Res_Map.flooricon_1f, 41, 67, null);
		g2d.drawImage(linkblink < LINKBMAX / 6 ? Res_Map.link_big_2 : Res_Map.link_big_1, 25, 64, null);
	}

	private int linkblink = 0;
	private final int LINKBMAX = 73;
	private int bossblink = 0;
	private final int BOSSBMAX = 29;

	@Override
	public void update() {
		linkblink++;
		bossblink++;
		if (linkblink > LINKBMAX)
			linkblink = 0;
		if (bossblink > BOSSBMAX)
			bossblink = 0;
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
			parent.SubState = parent.S_SUBITEMS;
		} else if (e.getKeyCode() == KeyEvent.VK_Z) {
			parent.resetOpenAnimation();
			parent.SubState = parent.S_ITEMS;
		}
	}

}
