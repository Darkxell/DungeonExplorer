package management.floors.floorsmanagers;

import java.awt.image.BufferedImage;

import res.Res;
import res.images.Res_Tiles;
import display.sprites.AnimatedSprite;
import management.entities.monsters.octorok.Oktorok;
import management.entities.monsters.sluggula.Sluggula;
import management.entities.monsters.unknown.Meat;
import management.entities.monsters.unknown.Unknown;
import management.entities.particle.MobDeath;
import management.floors.CurrentFloorHolder;
import management.floors.Floor;
import management.floors.Room;
import management.floors.Tile;
import management.player.PlayerInfo;
import management.player.ViewCamera;

public class TestDungeonManager implements FloorManager {

    public TestDungeonManager() {
	setFloorFromSave();
    }
    
    @Override
    public void setFloorFromSave() {
	CurrentFloorHolder.CurrentFloor = new Floor(Res.FOLDER_PATH
		    + "maps\\testdungeon");
	Room r = CurrentFloorHolder.CurrentFloor.rooms[0];
	r.addEntity(new Sluggula(r,10,10));
	r.addEntity(new Sluggula(r,5,10));
	r = CurrentFloorHolder.CurrentFloor.rooms[5];
	r.addEntity(new Unknown(r,7,2));
	r.addEntity(new Meat(r,9,9));
	
	// TODO : set the testdungeon state from the save file
    }

    boolean openfirestroom = true;
    boolean spawnerplate = true;
    boolean opensegondroom = true;
    
    @Override
    public void update() {
	Floor currentfloor =  CurrentFloorHolder.CurrentFloor;
	if (currentfloor.isPlayerOnTile(13, 2) && openfirestroom) {
	    openfirestroom = false;
	    currentfloor.getPlayerRoom().setTileAt(
		    13, 2, new Tile(new AnimatedSprite(new BufferedImage[] {Res_Tiles.tilessprites[36]}), 0));
	    currentfloor.getPlayerRoom().setTileAt(2, 12, new Tile(new AnimatedSprite(new BufferedImage[] {Res_Tiles.tilessprites[5]}), 1));
	    currentfloor.getPlayerRoom().setTileAt(3, 12, new Tile(new AnimatedSprite(new BufferedImage[] {Res_Tiles.tilessprites[6]}), 0));
	    currentfloor.getPlayerRoom().setTileAt(4, 12, new Tile(new AnimatedSprite(new BufferedImage[] {Res_Tiles.tilessprites[7]}), 1));
	    currentfloor.getPlayerRoom().addEntity(new MobDeath(currentfloor.getPlayerRoom(), 13.5, 2.5));
	}
	if (currentfloor.isPlayerOnTile(30, 38)) {
	    if(spawnerplate){
		spawnerplate = false;
	    currentfloor.getPlayerRoom().setTileAt(
		    12, 8, new Tile(new AnimatedSprite(new BufferedImage[] {Res_Tiles.tilessprites[235]}), 0));
	    currentfloor.getPlayerRoom().addEntity(new MobDeath(currentfloor.getPlayerRoom(), 12.5, 8.5));
	    currentfloor.getPlayerRoom().addEntity(new Oktorok(currentfloor.getPlayerRoom(), 7, 7));
	    currentfloor.getPlayerRoom().addEntity(new Oktorok(currentfloor.getPlayerRoom(), 11, 11));
	    currentfloor.getPlayerRoom().addEntity(new Oktorok(currentfloor.getPlayerRoom(), 11, 3));
	    }
	    }else{
		if(!spawnerplate){
		    currentfloor.getPlayerRoom().setTileAt(
			    12, 8, new Tile(new AnimatedSprite(new BufferedImage[] {Res_Tiles.tilessprites[20]}), 0));
		    spawnerplate = true;
		}
	    }
	if (currentfloor.isPlayerOnTile(44, 84)) {
	    PlayerInfo.posX = 4.5;
	    PlayerInfo.posY = 4.5;
	    ViewCamera.cameraX = 4.5;
	    ViewCamera.cameraY = 4.5;
	}
	if (currentfloor.isPlayerOnTile(48, 38) && opensegondroom) {
	    opensegondroom = false;
	    currentfloor.getPlayerRoom().setTileAt(
		    2, 21, new Tile(new AnimatedSprite(new BufferedImage[] {Res_Tiles.tilessprites[36]}), 0));
	    currentfloor.getPlayerRoom().addEntity(new MobDeath(currentfloor.getPlayerRoom(), 2.5, 21.5));
	    currentfloor.getPlayerRoom().setTileAt(12, 37, new Tile(new AnimatedSprite(new BufferedImage[] {Res_Tiles.tilessprites[5]}), 1));
	    currentfloor.getPlayerRoom().setTileAt(13, 37, new Tile(new AnimatedSprite(new BufferedImage[] {Res_Tiles.tilessprites[6]}), 0));
	    currentfloor.getPlayerRoom().setTileAt(14, 37, new Tile(new AnimatedSprite(new BufferedImage[] {Res_Tiles.tilessprites[7]}), 1));
	    
	    CurrentFloorHolder.CurrentFloor.rooms[6].setTileAt(3, 1, new Tile(new AnimatedSprite(new BufferedImage[] {Res_Tiles.tilessprites[65]}), 1));
	    CurrentFloorHolder.CurrentFloor.rooms[6].setTileAt(4, 1, new Tile(new AnimatedSprite(new BufferedImage[] {Res_Tiles.tilessprites[66]}), 0));
	    CurrentFloorHolder.CurrentFloor.rooms[6].setTileAt(5, 1, new Tile(new AnimatedSprite(new BufferedImage[] {Res_Tiles.tilessprites[67]}), 1));
	}
    }

}
