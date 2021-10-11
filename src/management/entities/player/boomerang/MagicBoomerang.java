package management.entities.player.boomerang;

import java.awt.Graphics2D;

import management.entities.Entity;
import management.entities.Hitbox;
import management.floors.Room;
import res.images.Res_PlayerItems;

public class MagicBoomerang extends Entity {

	public MagicBoomerang(Room roompointer, double x, double y) {
		super(roompointer, x, y);
	}

	private int rotation = 0;

	@Override
	public void update() {
		rotation++;
	}

	@Override
	public void print(Graphics2D g2d) {
		g2d.drawImage(Res_PlayerItems.boomerang2[rotation / 2 % 3],
				(int) ((super.roompointer.posX + super.posX) * 16 - 8),
				(int) ((super.roompointer.posY + super.posY) * 16 - 8), null);
	}

	@Override
	public Hitbox getHitbox(double posX, double posY) {
		return null;
	}

	@Override
	public void onhit() {
	}

}
