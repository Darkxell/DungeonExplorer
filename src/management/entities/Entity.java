package management.entities;

import java.awt.Graphics2D;

import management.floors.Room;
import management.player.PlayerInfo;
import display.sprites.SpriteSheet;

public abstract class Entity {

	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int UP = 2;
	public static final int DOWN = 3;

	/** Pointer to the room containing the entity. */
	public Room roompointer;
	/** The X position of the entity in its room. */
	public double posX;
	/** The Y position of the entity in its room. */
	public double posY;

	/** The health of the entity. */
	public double hp = 1;

	/**
	 * The direction faced by the entity. This may not be relevent depending on the
	 * entity, like an item drop may not face something else than down. This is
	 * mainly for display purposes but not only.
	 */
	public int facing = DOWN;

	/**
	 * The spriteSheet of the entity.
	 * 
	 * @see SpriteSheet
	 */
	public SpriteSheet entityDesign;

	public EntityState state;

	public Entity(Room roompointer, double x, double y) {
		this.roompointer = roompointer;
		this.posX = x;
		this.posY = y;
	}

	/** updates the entity. */
	public abstract void update();

	/** prints the entity on the graphics object. */
	public abstract void print(Graphics2D g2d);

	/** Returns the hitbox of this entity. */
	public abstract Hitbox getHitbox(double posX, double posY);

	/** Called when the entity is hit by the player sword. */
	public abstract void onhit();

	/** Deletes this entity from its parent room. */
	public void kill() {
		this.roompointer.deleteEntity(this);
	}

	/** Return the square of the distance between this entity and the player */
	public double distP2() {
		return Math.pow(posX - PlayerInfo.posX + roompointer.posX, 2) + Math.pow(posY - PlayerInfo.posY + roompointer.posY, 2);
	}
}
