package management.states.ingame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import res.images.Res_FileSelect;
import res.images.Res_Inventory;
import res.images.Res_Items;
import util.ItemsUtility;
import util.NumberUtil;
import management.entities.Entity;
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
	private boolean kD;
	private boolean kS;
	private boolean kR;

	private GameState parent;

	public ExploreState(GameState parent) {
		this.parent = parent;
	}

	@Override
	public void print(Graphics2D g2d) {

		CurrentFloorHolder.printFloor(g2d);

		g2d.drawImage(Res_FileSelect.buttonZ, 215, 10, null);
		g2d.drawImage(PlayerInfo.currentstate.getRightPText(), 205, 5, null);
		g2d.drawImage(Res_Inventory.ButtonS, 190, 27, null);
		g2d.drawImage(ItemsUtility.getSpriteFromID(PlayerInfo.hand_S_itemID), 180, 17, null);
		g2d.drawImage(Res_Inventory.ButtonD, 210, 27, null);
		g2d.drawImage(ItemsUtility.getSpriteFromID(PlayerInfo.hand_D_itemID), 200, 17, null);

		g2d.drawImage(Res_Items.hud_key, 210, 130, null);
		g2d.drawImage(Res_Items.hud_rupee_green, 200, 146, null);
		NumberUtil.printNumberWithFont(g2d, NumberUtil.FONT_WHITESQUARES, 212, 147, PlayerInfo.playerInventory.ruppees);
		NumberUtil.printNumberWithFont(g2d, NumberUtil.FONT_WHITESQUARES, 225, 133, PlayerInfo.playerInventory.keys);

		for (int i = 0; i < PlayerInfo.playerInventory.maxhealth; ++i) {
			if (PlayerInfo.health > 1 + i)
				g2d.drawImage(Res_FileSelect.hearth_small_4, 10 + (9 * i), 5, null);
			else if (PlayerInfo.health <= i)
				g2d.drawImage(Res_FileSelect.hearth_small_0, 10 + (9 * i), 5, null);
			else if (PlayerInfo.health == i + 1)
				g2d.drawImage(Res_FileSelect.hearth_big_4, 10 + (9 * i), 5, null);
			else if (PlayerInfo.health == i + 0.75)
				g2d.drawImage(Res_FileSelect.hearth_big_3, 10 + (9 * i), 5, null);
			else if (PlayerInfo.health == i + 0.5)
				g2d.drawImage(Res_FileSelect.hearth_big_2, 10 + (9 * i), 5, null);
			else if (PlayerInfo.health == i + 0.25)
				g2d.drawImage(Res_FileSelect.hearth_big_1, 10 + (9 * i), 5, null);
		}
		
		if(PlayerInfo.DEBUGMODE)
			printDebug(g2d);

	}

	/** Prints a layer of debug info on the screen */
	private void printDebug(Graphics2D g2d) {
		if(PlayerInfo.DEBUGMODE) {
			g2d.setColor(Color.WHITE);
			g2d.drawString("X:" + ((float)(int)(PlayerInfo.posX*10))/10 + "/Y:" + ((float)(int)(PlayerInfo.posY*10))/10, 5, 155);
			g2d.setColor(Color.RED);
			g2d.drawString("NOCLIP ON", 5, 145);
		}
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
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			kD = false;
			PlayerInfo.action2release();
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			kdown = false;
			PlayerInfo.action1release();
		} else if (e.getKeyCode() == KeyEvent.VK_Z) {
			kR = false;
			PlayerInfo.actionRrelease();
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
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			if (!kD) {
				kD = true;
				PlayerInfo.action2press();
			}
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			if (!kS) {
				kdown = true;
				PlayerInfo.action1press();
			}
		} else if (e.getKeyCode() == KeyEvent.VK_Z) {
			if (!kR) {
				kR = true;
				PlayerInfo.actionRpress();
			}
		}
	}

}
