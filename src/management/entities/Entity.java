package management.entities;

import java.awt.Graphics2D;

import management.floors.Room;
import display.sprites.SpriteSheet;

public abstract class Entity {

    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int RIGHT = 2;
    public static final int LEFT = 3;

    /** Pointer to the room containing the entity. */
    private Room roompointer;
    /** The X position of the entity in its room. */
    public int posX;
    /** The Y position of the entity in its room. */
    public int posY;

    /** The health of the entity. */
    public int hp = 1;

    /**
     * The direction faced by the entity. This may not be relevent depending on
     * the entity, like an item drop may not face something else than down. This
     * is mainly for display purposes but not only.
     */
    public int facing = DOWN;

    /**
     * The spriteSheet of the entity.
     * 
     * @see SpriteSheet
     */
    public SpriteSheet entityDesign;

    /** updates the entity. */
    public abstract void update();

    /** prints the entity on the graphics object. */
    public abstract void print(Graphics2D g2d);

    /** Called when the entity takes damage. */
    public abstract void onhit();

    /** Deletes this entity from its parent room. */
    public void kill() {
	this.roompointer.deleteEntity(this);
    }
}
