package management.floors.floorsmanagers;

import java.awt.image.BufferedImage;

import res.Res;
import res.images.Res_Tiles;
import display.sprites.AnimatedSprite;
import management.entities.octorok.Oktorok;
import management.entities.sluggula.Sluggula;
import management.floors.CurrentFloorHolder;
import management.floors.Floor;
import management.floors.Room;
import management.floors.Tile;

public class TestDungeonManager implements FloorManager {

    public TestDungeonManager() {
	setFloorFromSave();
    }
    
    @Override
    public void setFloorFromSave() {
	CurrentFloorHolder.CurrentFloor = new Floor(Res.FOLDER_PATH
		    + "maps\\testdungeon");
	Room r0 = CurrentFloorHolder.CurrentFloor.rooms[0];
	r0.addEntity(new Sluggula(r0,10,10));
	r0.addEntity(new Oktorok(r0,5,10));
	
	// TODO : set the testdungeon state from the save file
    }

    @Override
    public void update() {
	if (CurrentFloorHolder.CurrentFloor.isPlayerOnTile(13, 2)) {
	    CurrentFloorHolder.CurrentFloor.getPlayerRoom().setTileAt(
		    13, 2, new Tile(new AnimatedSprite(new BufferedImage[] {Res_Tiles.tilessprites[36]}), 0));
	    CurrentFloorHolder.CurrentFloor.getPlayerRoom().setTileAt(2, 12, new Tile(new AnimatedSprite(new BufferedImage[] {Res_Tiles.tilessprites[5]}), 1));
	    CurrentFloorHolder.CurrentFloor.getPlayerRoom().setTileAt(3, 12, new Tile(new AnimatedSprite(new BufferedImage[] {Res_Tiles.tilessprites[6]}), 0));
	    CurrentFloorHolder.CurrentFloor.getPlayerRoom().setTileAt(4, 12, new Tile(new AnimatedSprite(new BufferedImage[] {Res_Tiles.tilessprites[7]}), 1));
	}
    }

}
