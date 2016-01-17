package management.entities.items;

import java.awt.image.BufferedImage;

import management.entities.Item;
import management.floors.Room;
import management.player.PlayerInfo;
import res.images.Res_MobsDrops;

public class Key extends Item {

    public Key(Room roompointer, double x, double y, double height) {
	super(roompointer, x, y, height);
    }

    @Override
    public BufferedImage getSprite() {
	return Res_MobsDrops.Key;
    }

    @Override
    public void pickup() {
	PlayerInfo.playerInventory.keys += 1;
	super.kill();
    }
}
