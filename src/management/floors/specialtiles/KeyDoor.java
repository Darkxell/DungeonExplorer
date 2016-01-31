package management.floors.specialtiles;

import java.awt.image.BufferedImage;

import res.images.Res_Tiles;
import display.sprites.AnimatedSprite;
import management.floors.Room;
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

    /** Open the door at the wanted coordinates in the wanted room. Note that the coordinates are relative to the floor and not to the room.*/
    public static void openDoorAt(int x, int y, Room room) {
	Tile door = room.getTile(x-room.posX, y-room.posY);
	if (door instanceof KeyDoor) {
	    switch (((KeyDoor)door).spriteID) {
	    case stone_left:
		room.setTileAt(x-room.posX, y-room.posY-1, new Tile(new AnimatedSprite(new BufferedImage[] { Res_Tiles.tilessprites[1] }), Tile.TYPE_SOLID));
		room.setTileAt(x-room.posX, y-room.posY, new Tile(new AnimatedSprite(new BufferedImage[] { Res_Tiles.tilessprites[21] }), Tile.TYPE_NORMAL));
		room.setTileAt(x-room.posX, y-room.posY+1, new Tile(new AnimatedSprite(new BufferedImage[] { Res_Tiles.tilessprites[41] }), Tile.TYPE_SOLID));
		break;
	    case stone_right:
		room.setTileAt(x-room.posX, y-room.posY-1, new Tile(new AnimatedSprite(new BufferedImage[] { Res_Tiles.tilessprites[4] }), Tile.TYPE_SOLID));
		room.setTileAt(x-room.posX, y-room.posY, new Tile(new AnimatedSprite(new BufferedImage[] { Res_Tiles.tilessprites[24] }), Tile.TYPE_NORMAL));
		room.setTileAt(x-room.posX, y-room.posY+1, new Tile(new AnimatedSprite(new BufferedImage[] { Res_Tiles.tilessprites[44] }), Tile.TYPE_SOLID));
		break;
	    case stone_up:
		room.setTileAt(x-room.posX-1, y-room.posY, new Tile(new AnimatedSprite(new BufferedImage[] { Res_Tiles.tilessprites[5] }), Tile.TYPE_SOLID));
		room.setTileAt(x-room.posX, y-room.posY, new Tile(new AnimatedSprite(new BufferedImage[] { Res_Tiles.tilessprites[6] }), Tile.TYPE_NORMAL));
		room.setTileAt(x-room.posX+1, y-room.posY, new Tile(new AnimatedSprite(new BufferedImage[] { Res_Tiles.tilessprites[7] }), Tile.TYPE_SOLID));
		break;
	    case stone_down:
		room.setTileAt(x-room.posX-1, y-room.posY, new Tile(new AnimatedSprite(new BufferedImage[] { Res_Tiles.tilessprites[65] }), Tile.TYPE_SOLID));
		room.setTileAt(x-room.posX, y-room.posY, new Tile(new AnimatedSprite(new BufferedImage[] { Res_Tiles.tilessprites[66] }), Tile.TYPE_NORMAL));
		room.setTileAt(x-room.posX+1, y-room.posY, new Tile(new AnimatedSprite(new BufferedImage[] { Res_Tiles.tilessprites[67] }), Tile.TYPE_SOLID));
		break;
	    }
	} else {
	    System.err.println("Error when oppening a door at coords : " + x
		    + "/" + y + " . Tile in not a key door.");
	}
    }

}
