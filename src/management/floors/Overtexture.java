package management.floors;

import display.sprites.AnimatedSprite;

/**
 * Represents a texture of something to print above the player, tiles and
 * entities, like a door top or a ceiling.
 */
public class Overtexture {

    /** The sprite of the Overtexture. */
    public AnimatedSprite sprite;

    public int x;
    public int y;

    public Overtexture(AnimatedSprite sprite, int x, int y) {
	this.sprite = sprite;
	this.x = x;
	this.y = y;
    }

}
