package management.floors;

import java.io.File;

import management.player.PlayerInfo;
import util.StringMatcher;
import util.TextFileUtility;

/** Object that stores all of the rooms (tiles array of a floor.) */
public class Floor {

    /** The Rooms of this floor. */
    public Room[] rooms;
    /** All of the textures printed above the tiles and entities on this floor. */
    public Overtexture[] hovering;

    /**
     * Generates a floor using a map folder.<br/>
     * The folder should contain map file nammed like this :<br/>
     * <code>x-y.txt</code><br/>
     * where x and y represents the room position in the floor.
     * */
    public Floor(String folderpath) {
	File folder = new File(folderpath);
	File[] roomsfiles = folder.listFiles();
	rooms = new Room[roomsfiles.length];
	int x, y;
	for (int i = 0; i < rooms.length; i++) {
	    String name = StringMatcher.getRawFilename(roomsfiles[i].getName());
	    String[] indexes = StringMatcher.split(name, '-');
	    x = Integer.parseInt(indexes[0]);
	    y = Integer.parseInt(indexes[1]);
	    rooms[i] = new Room(
		    TextFileUtility.getTextFileContent(roomsfiles[i]), x, y);
	}
    }

    /** Gets the room the player currently is in. */
    public Room getPlayerRoom() {
	for (int i = 0; i < rooms.length; i++)
	    if (PlayerInfo.posX >= rooms[i].posX
		    && PlayerInfo.posY >= rooms[i].posY
		    && PlayerInfo.posX < rooms[i].posX + rooms[i].width
		    && PlayerInfo.posY < rooms[i].posY + rooms[i].height)
		return rooms[i];
	return null;
    }

    /** Gets the tile type at the wanted coordinates. */
    public int getTileTypeAt(int tileX, int tileY) {
	for (int i = 0; i < rooms.length; i++)
	    try {
		if (tileX >= rooms[i].posX && tileY >= rooms[i].posY
			&& tileX < rooms[i].posX + rooms[i].width
			&& tileY < rooms[i].posY + rooms[i].height)
		    return rooms[i].getTile(tileX - rooms[i].posX, tileY
			    - rooms[i].posY).type;
	    } catch (Exception e) {
	    }
	System.err.println("No room found at coordinates : " + tileX + "/"
		+ tileY + " , Tile type returned : 0.");
	return 0;
    }

    /**
     * Predicate that returns true if the player center is on the tile at the
     * specified coordinates
     */
    public boolean isPlayerOnTile(int tileX, int tileY) {
	return (PlayerInfo.posX >= tileX && PlayerInfo.posY >= tileY
		&& PlayerInfo.posX < tileX + 1 && PlayerInfo.posY < tileY + 1);
    }

}
