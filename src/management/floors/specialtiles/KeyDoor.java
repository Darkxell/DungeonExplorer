package management.floors.specialtiles;

import java.awt.image.BufferedImage;

import res.images.Res_Tiles;
import display.sprites.AnimatedSprite;
import management.floors.Tile;

public class KeyDoor extends Tile {

    private int spriteID;

    public static final int stone_left = 274;
    public static final int stone_right = 275;
    public static final int stone_up = 115;
    public static final int stone_down = 135;

    public KeyDoor(int spriteID) {
	super(new AnimatedSprite(
		new BufferedImage[] { Res_Tiles.tilessprites[spriteID] }),
		Tile.TYPE_SOLID);
	this.spriteID = spriteID;
    }

    /** Open the door. */
    public void open() {
	switch (spriteID) {
	case stone_left:
	    super.sprite = new AnimatedSprite(
		    new BufferedImage[] { Res_Tiles.tilessprites[21] });
	    break;
	case stone_right:
	    super.sprite = new AnimatedSprite(
		    new BufferedImage[] { Res_Tiles.tilessprites[24] });
	    break;
	case stone_up:
	    super.sprite = new AnimatedSprite(
		    new BufferedImage[] { Res_Tiles.tilessprites[6] });
	    break;
	case stone_down:
	    super.sprite = new AnimatedSprite(
		    new BufferedImage[] { Res_Tiles.tilessprites[66] });
	    break;
	}
	super.type = Tile.TYPE_NORMAL;
    }

}
