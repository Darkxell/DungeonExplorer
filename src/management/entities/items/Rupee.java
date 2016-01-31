package management.entities.items;

import java.awt.image.BufferedImage;

import res.audio.SoundsHolder;
import res.images.Res_MobsDrops;
import main.DungeonExplorer;
import management.entities.Item;
import management.floors.Room;
import management.player.PlayerInfo;

public class Rupee extends Item {

    public Rupee(Room roompointer, double x, double y, double height) {
	super(roompointer, x, y, height);
	DungeonExplorer.sm.playSound(SoundsHolder
		.getSong("MC_Rupee_Bounce.mp3"));
    }

    @Override
    public BufferedImage getSprite() {
	return Res_MobsDrops.Rupee_green;
    }

    @Override
    public void pickup() {
	PlayerInfo.playerInventory.ruppees += 1;
	super.kill();
	DungeonExplorer.sm.playSound(SoundsHolder.getSong("MC_Rupee.mp3"));
    }
}