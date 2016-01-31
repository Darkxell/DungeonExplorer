package management.floors;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import res.images.Res_Tiles;
import display.sprites.AnimatedSprite;
import util.StringMatcher;
import management.entities.Entity;

/** Object that represents a room. */
public class Room {

    /** The entities in this room. */
    public Entity[] entities;

    public int width;
    public int height;

    /** The tiles in this room. */
    private Tile[] tiles;
    /** The overtexture of this room. */
    private Overtexture[] overtextures;

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
     * overtexture <br/>
     * (x,y,i) ; ... ; (xn,yn,in)
     * </code>
     */
    public Room(String roomCode, int posX, int posY) {
	this.posX = posX;
	this.posY = posY;
	this.entities = new Entity[] {};
	String[] splittedcode = roomCode.split("overtexture");
	String[] lines = StringMatcher.split(splittedcode[0], '!');
	this.height = lines.length - 1;
	this.width = StringMatcher.split(lines[1], ';').length;
	this.tiles = new Tile[height * width];
	for (int i = 1; i < height + 1; i++) {
	    String[] splittedline = StringMatcher.split(lines[i], ';');
	    for (int j = 0; j < splittedline.length; j++)
		this.tiles[width * (i - 1) + j] = new Tile(
			StringMatcher.getAbsoluteContent(splittedline[j]));
	}
	try {
	    String[] ovtxts = splittedcode[1].split(";");
	    overtextures = new Overtexture[ovtxts.length];
	    for (int i = 0; i < ovtxts.length; i++) {
		String[] single = StringMatcher.getAbsoluteContent(
			StringMatcher.getBracketsContent(ovtxts[i], 1)).split(
			",");
		overtextures[i] = new Overtexture(new AnimatedSprite(
			new BufferedImage[] { Res_Tiles.tilessprites[Integer
				.parseInt(single[2])] }),
			Integer.parseInt(single[0]),
			Integer.parseInt(single[1]));
	    }
	} catch (Exception e) {
	    overtextures = new Overtexture[] {};
	}

    }

    /** Gets the wanted tile. */
    public Tile getTile(int x, int y) {
	return this.tiles[this.width * y + x];
    }

    /** Sets the wanted tile. */
    public void setTileAt(int x, int y, Tile toSet) {
	this.tiles[this.width * y + x] = toSet;
    }

    /**
     * Prints the room at the wanted coordinates. The x and y coordinates define
     * the top left corner of the room.
     */
    public void print(Graphics2D g2d, int x, int y) {
	for (int i = 0; i < this.width; i++)
	    for (int j = 0; j < this.height; j++)
		try {
		    g2d.drawImage(getTile(i, j).sprite.get(), 16 * i + x, 16
			    * j + y, null);
		} catch (Exception e) {
		    System.err
			    .println("Tile not printed in the current room at coordinates : "
				    + i + " / " + j);
		}
	for (int i = 0; i < entities.length; i++)
	    entities[i].print(g2d);

	// TODO : draw the tileentities.
    }

    /**
     * Prints the overtexture of this room. The x and x coordinates define the
     * top corner of the room. This is basically the same as the
     * <code>Room.print()</code> method, but only for the overtexture.
     */
    public void printOvertexture(Graphics2D g2d, int x, int y) {
	for (int i = 0; i < overtextures.length; i++)
	    try {
		g2d.drawImage(overtextures[i].sprite.get(), 16
			* overtextures[i].x + x, 16 * overtextures[i].y + y,
			null);
	    } catch (Exception e) {
		System.err.println("Overtexture couldn't be printed.");
	    }
    }

    /** Updates everything in the room. */
    public void update() {
	for (int i = 0; i < this.tiles.length; i++)
	    try {
		tiles[i].update();
	    } catch (Exception e) {
		System.err.println("Tile " + i + " could not be updated.");
	    }
	for (int i = 0; i < entities.length; i++)
	    entities[i].update();
	for (int i = 0; i < overtextures.length; i++)
	    overtextures[i].sprite.next();// FIXME : not 50 ups here
    }

    /** Deletes the entity pointed in the argument from this room. */
    public void deleteEntity(Entity pointer) {
	Entity[] newentities = new Entity[entities.length - 1];
	int removeid = -1;
	for (int i = 0; i < entities.length; i++)
	    if (entities[i] == pointer)
		removeid = i;
	if (removeid == -1)
	    System.err.println("Entitée non trouvée. Délétion impossible.");
	else {
	    System.arraycopy(entities, 0, newentities, 0, removeid);
	    System.arraycopy(entities, removeid + 1, newentities, removeid,
		    entities.length - 1 - removeid);
	    this.entities = newentities;
	}
    }

    public void addEntity(Entity toAdd) {
	Entity[] entities2 = new Entity[entities.length + 1];
	System.arraycopy(entities, 0, entities2, 0, entities.length);
	entities2[entities.length] = toAdd;
	entities = entities2;
    }

}
