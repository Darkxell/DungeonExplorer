package management.floors;

import java.awt.Graphics2D;

import util.StringMatcher;
import management.entities.Entity;

/** Object that represents a room. */
public class Room {

    /** The entities in this room. */
    @SuppressWarnings("unused")
    private Entity[] entities;

    public int width;
    public int height;

    /** The tiles in this room. */
    private Tile[] tiles;

    /** The X position of the room in the roomMap. */
    public int posX;
    /** The Y position of the room in the roomMap. */
    public int posY;

    /**
     * Creates a Room object using a Room StringCode. <br/>
     * The wanted filesyntax is the following :<br/>
     * <code><br/>
     * ! [tilecode] ;  ... ; [tilecode] ;<br/>
     * ! [tilecode] ;  ... ; [tilecode] ;<br/>
     * ...<br/>
     * ! [tilecode] ;  ... ; [tilecode] ;<br/>
     * </code>
     */
    public Room(String roomCode, int posX, int posY) {
	this.posX = posX;
	this.posY = posY;
	String[] lines = StringMatcher.split(roomCode, '!');
	this.height = lines.length - 1;
	this.width = StringMatcher.split(lines[1], ';').length;

	this.tiles = new Tile[height * width];
	for (int i = 1; i < height + 1; i++) {
	    String[] splittedline = StringMatcher.split(lines[i], ';');
	    for (int j = 0; j < splittedline.length; j++) {

		this.tiles[width * (i - 1) + j] = new Tile(
			StringMatcher.getAbsoluteContent(splittedline[j]));

	    }
	}

    }

    /** Gets the wanted tile.s */
    public Tile getTile(int x, int y) {
	return this.tiles[this.width * y + x];
    }

    /**
     * Prints the room at the wanted coordinates. The x and y coordinates define
     * the top left corner of the room.
     */
    public void print(Graphics2D g2d, int x, int y) {
	for (int i = 0; i < this.width; i++) {
	    for (int j = 0; j < this.height; j++) {
		try {
		    g2d.drawImage(getTile(i, j).sprite.get(), 16 * i + x, 16 * j + y,
			    null);
		} catch (Exception e) {
		    System.err
			    .println("Tile not printed in the current room at coordinates : "
				    + i + " / " + j);
		}
	    }
	}
	// TODO : draw the entities, the player and the tileentities.
    }

    /** Updates everything in the room. */
    public void update() {
	for (int i = 0; i < this.tiles.length; i++) {
	    try {
		tiles[i].update();
	    } catch (Exception e) {
		System.err.println("Tile " + i + " could not be updated.");
	    }

	}

	// entities.update();
    }

    /** Deletes the entity pointed in the argument from this room. */
    public void deleteEntity(Entity pointer) {
	// TODO
    }

}
