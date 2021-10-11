package management.entities.items;

import java.awt.image.BufferedImage;

import main.DungeonExplorer;
import management.entities.Item;
import management.floors.Room;
import management.player.PlayerInfo;
import res.audio.SoundsHolder;
import res.images.Res_Map;

public class BossKey extends Item {

	public BossKey(Room roompointer, double x, double y, double height) {
		super(roompointer, x, y, height);
	}

	@Override
	public BufferedImage getSprite() {
		return Res_Map.icon_bosskey;
	}

	@Override
	public void pickup() {
		DungeonExplorer.sm.playSound(SoundsHolder.getSong("MC_Shell.mp3"));
		PlayerInfo.playerInventory.dungeon_hasbosskey = true;
		super.kill();
	}

}
