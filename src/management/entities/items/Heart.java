package management.entities.items;

import java.awt.image.BufferedImage;

import res.audio.SoundsHolder;
import res.images.Res_MobsDrops;
import main.DungeonExplorer;
import management.entities.Item;
import management.floors.Room;
import management.player.PlayerInfo;

public class Heart extends Item {

    public Heart(Room roompointer, double x, double y, double height) {
	super(roompointer, x, y, height);
	DungeonExplorer.sm.playSound(SoundsHolder
		.getSong("MC_Heart_Bounce.mp3"));
    }

    @Override
    public BufferedImage getSprite() {
	return Res_MobsDrops.Heart;
    }

    @Override
    public void pickup() {
	PlayerInfo.health += 1;
	if (PlayerInfo.health > PlayerInfo.playerInventory.maxhealth)
	    PlayerInfo.health = PlayerInfo.playerInventory.maxhealth;
	super.kill();
	DungeonExplorer.sm.playSound(SoundsHolder
		.getSong("MC_Heart.mp3"));
    }
}
