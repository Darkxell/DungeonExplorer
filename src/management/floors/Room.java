package management.floors;

import java.awt.Graphics2D;

import management.entities.Entity;

/** Object that represents a room. */
public class Room {

    /** The entities in this room. */
    private Entity[] entities;

    /**
     * The tiles in this room. For more conveniance, this is a double array,
     * allowing to get the height and width of the room.
     */
    private Tile[][] tiles;

    /** The X position of the room in the roomMap. */
    public int posX;
    /** The Y position of the room in the roomMap. */
    public int posY;

    /**Creates a Room object using a Room StringCode.*/
    public Room(String roomCode){
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    }
    
    /**
     * Prints the room at the wanted coordinates. The x and y coordinates define
     * the top left corner of the room.
     */
    public void print(Graphics2D g2d, int x, int y) {
	for (int i = 0; i < tiles.length; i++) {
	    for (int j = 0; j < tiles[0].length; j++) {
		g2d.drawImage(tiles[i][j].sprite.get(), 16 * i, 16 * j, null);
	    }
	}
	// TODO : draw the entities, the player and

    }

    /** Updates everything in the room. */
    public void update() {
	for (int i = 0; i < tiles.length; i++)
	    for (int j = 0; j < tiles[0].length; j++)
		tiles[i][j].update();
	// entities.update();
    }

    /** Deletes the entity pointed in the argument from this room. */
    public void deleteEntity(Entity pointer) {
	// TODO
    }

}
