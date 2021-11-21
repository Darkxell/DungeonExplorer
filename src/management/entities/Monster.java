package management.entities;

import java.awt.Graphics2D;

import management.floors.Room;
import management.player.PlayerInfo;
import management.player.playerstates.PS_Knockback;
import util.MathVector;

/** A class that represents a certain entity made to be a monster. */
public abstract class Monster extends Entity {

	public double damage = 0.25d;

	public Monster(Room roompointer, double x, double y) {
		super(roompointer, x, y);
	}

	@Override
	public final void update() {
		updateM();
		knockback();
	}

	/** updates the monster. */
	public abstract void updateM();

	@Override
	public abstract void print(Graphics2D g2d);

	@Override
	public abstract void onhit();

	/**
	 * knockback the player from this monster if it collides with the player hitbox.
	 */
	public void knockback() {
		Hitbox hitbox = getHitbox(super.posX + roompointer.posX, super.posY + roompointer.posY);
		if (hitbox == null)
			return;
		boolean collide = false;
		Hitbox ph = PlayerInfo.getPlayerHitbox(PlayerInfo.posX, PlayerInfo.posY);
		if (ph.intersects(hitbox.getRectHitbox()))
			collide = true;
		if (collide && PlayerInfo.health > 0.01f && !(PlayerInfo.currentstate instanceof PS_Knockback)) {
			PlayerInfo.currentstate = new PS_Knockback(
					new MathVector(PlayerInfo.posX - super.posX, PlayerInfo.posY - super.posY), 9);
			PlayerInfo.health -= this.damage;
		}

	}

}
