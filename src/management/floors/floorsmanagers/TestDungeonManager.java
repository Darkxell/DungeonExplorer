package management.floors.floorsmanagers;

import java.awt.image.BufferedImage;

import res.Res;
import res.audio.MusicHolder;
import res.audio.SoundsHolder;
import res.images.Res_Tiles;
import display.sprites.AnimatedSprite;
import main.DungeonExplorer;
import management.entities.items.Key;
import management.entities.monsters.darknut.Darknut;
import management.entities.monsters.flock.Flock;
import management.entities.monsters.flock.ObstacleBoid;
import management.entities.monsters.floormaster.Floormaster;
import management.entities.monsters.octorok.Oktorok;
import management.entities.monsters.sluggula.Sluggula;
import management.entities.monsters.unknown.Meat;
import management.entities.monsters.unknown.Unknown;
import management.entities.particle.MobDeath;
import management.floors.CurrentFloorHolder;
import management.floors.Floor;
import management.floors.Room;
import management.floors.Tile;
import management.floors.specialtiles.KeyDoor;
import management.player.PlayerInfo;
import management.player.ViewCamera;

public class TestDungeonManager implements FloorManager {

	public TestDungeonManager() {
		setFloorFromSave();
	}

	@Override
	public void setFloorFromSave() {
		CurrentFloorHolder.CurrentFloor = new Floor(Res.FOLDER_PATH + "maps\\testdungeon");
		Room r = CurrentFloorHolder.CurrentFloor.rooms[0];
		r.addEntity(new Sluggula(r, 10, 10));
		r.addEntity(new Sluggula(r, 5, 10));
		r = CurrentFloorHolder.CurrentFloor.rooms[1];
		r.addEntity(new Sluggula(r, 5, 9));
		r = CurrentFloorHolder.CurrentFloor.rooms[2];
		r.addEntity(new Key(r, 6, 6, 0));
		r = CurrentFloorHolder.CurrentFloor.rooms[5];
		r.addEntity(new Unknown(r, 7, 2));
		r.addEntity(new Meat(r, 9, 9));
		r = CurrentFloorHolder.CurrentFloor.rooms[8];
		r.addEntity(new Floormaster(r, 8, 3));
		r.addEntity(new Floormaster(r, 8, 9));
		r = CurrentFloorHolder.CurrentFloor.rooms[10];
		r.addEntity(new Sluggula(r, 36, 14));
		r.addEntity(new Sluggula(r, 34, 16));
		r.addEntity(new Floormaster(r, 4.5, 8.5));
		r.addEntity(new Floormaster(r, 18.5, 8.5));
		r = CurrentFloorHolder.CurrentFloor.rooms[11];
		Flock f = new Flock(r, 2, 3, 19, 12);
		f.content.add(new ObstacleBoid(r, f, 9.5d, 4.5d));
		f.content.add(new ObstacleBoid(r, f, 13.5d, 4.5d));
		f.content.add(new ObstacleBoid(r, f, 8.5d, 6.5d));
		f.content.add(new ObstacleBoid(r, f, 14.5d, 6.5d));
		r.addEntity(f);
		r = CurrentFloorHolder.CurrentFloor.rooms[12];
		r.addEntity(new Darknut(r, 8.5, 6));
		DungeonExplorer.sm.setBackgroundMusic(MusicHolder.getSong("MC_DeepwoodShrine.mp3"));
		CurrentFloorHolder.CurrentFloor.rooms[7].setTileAt(17, 10, new KeyDoor(KeyDoor.stone_left));
		// TODO : set the testdungeon state from the save file
	}

	boolean openfirestroom = true;
	boolean spawnerplate = true;
	boolean opensegondroom = true;
	boolean openmazegate = true;
	boolean lastdoor = true;

	@Override
	public void update() {
		Floor currentfloor = CurrentFloorHolder.CurrentFloor;
		if (currentfloor.isPlayerOnTile(13, 2) && openfirestroom) {
			DungeonExplorer.sm.playSound(SoundsHolder.getSong("MC_FloorSwitch.mp3"));
			openfirestroom = false;
			currentfloor.getPlayerRoom().setTileAt(13, 2,
					new Tile(new AnimatedSprite(new BufferedImage[] { Res_Tiles.tilessprites[36] }), 0));
			currentfloor.getPlayerRoom().setTileAt(2, 12,
					new Tile(new AnimatedSprite(new BufferedImage[] { Res_Tiles.tilessprites[5] }), 1));
			currentfloor.getPlayerRoom().setTileAt(3, 12,
					new Tile(new AnimatedSprite(new BufferedImage[] { Res_Tiles.tilessprites[6] }), 0));
			currentfloor.getPlayerRoom().setTileAt(4, 12,
					new Tile(new AnimatedSprite(new BufferedImage[] { Res_Tiles.tilessprites[7] }), 1));
			currentfloor.getPlayerRoom().addEntity(new MobDeath(currentfloor.getPlayerRoom(), 13.5, 2.5));
		}
		if (currentfloor.isPlayerOnTile(30, 38)) {
			if (spawnerplate) {
				spawnerplate = false;
				DungeonExplorer.sm.playSound(SoundsHolder.getSong("MC_FloorSwitch.mp3"));
				currentfloor.getPlayerRoom().setTileAt(12, 8,
						new Tile(new AnimatedSprite(new BufferedImage[] { Res_Tiles.tilessprites[235] }), 0));
				currentfloor.getPlayerRoom().addEntity(new MobDeath(currentfloor.getPlayerRoom(), 12.5, 8.5));
				currentfloor.getPlayerRoom().addEntity(new Oktorok(currentfloor.getPlayerRoom(), 7, 7));
				currentfloor.getPlayerRoom().addEntity(new Oktorok(currentfloor.getPlayerRoom(), 11, 11));
				currentfloor.getPlayerRoom().addEntity(new Oktorok(currentfloor.getPlayerRoom(), 11, 3));
			}
		} else {
			if (!spawnerplate) {
				DungeonExplorer.sm.playSound(SoundsHolder.getSong("MC_FloorSwitch.mp3"));
				currentfloor.getPlayerRoom().setTileAt(12, 8,
						new Tile(new AnimatedSprite(new BufferedImage[] { Res_Tiles.tilessprites[20] }), 0));
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
			DungeonExplorer.sm.playSound(SoundsHolder.getSong("MC_FloorSwitch.mp3"));
			currentfloor.getPlayerRoom().setTileAt(2, 21,
					new Tile(new AnimatedSprite(new BufferedImage[] { Res_Tiles.tilessprites[36] }), 0));
			currentfloor.getPlayerRoom().addEntity(new MobDeath(currentfloor.getPlayerRoom(), 2.5, 21.5));
			currentfloor.getPlayerRoom().setTileAt(12, 37,
					new Tile(new AnimatedSprite(new BufferedImage[] { Res_Tiles.tilessprites[5] }), 1));
			currentfloor.getPlayerRoom().setTileAt(13, 37,
					new Tile(new AnimatedSprite(new BufferedImage[] { Res_Tiles.tilessprites[6] }), 0));
			currentfloor.getPlayerRoom().setTileAt(14, 37,
					new Tile(new AnimatedSprite(new BufferedImage[] { Res_Tiles.tilessprites[7] }), 1));

			CurrentFloorHolder.CurrentFloor.rooms[6].setTileAt(3, 1,
					new Tile(new AnimatedSprite(new BufferedImage[] { Res_Tiles.tilessprites[65] }), 1));
			CurrentFloorHolder.CurrentFloor.rooms[6].setTileAt(4, 1,
					new Tile(new AnimatedSprite(new BufferedImage[] { Res_Tiles.tilessprites[66] }), 0));
			CurrentFloorHolder.CurrentFloor.rooms[6].setTileAt(5, 1,
					new Tile(new AnimatedSprite(new BufferedImage[] { Res_Tiles.tilessprites[67] }), 1));
		}
		if (currentfloor.isPlayerOnTile(83, 94) && openmazegate) {
			DungeonExplorer.sm.playSound(SoundsHolder.getSong("MC_FloorSwitch.mp3"));
			openmazegate = false;
			currentfloor.getPlayerRoom().setTileAt(12, 13,
					new Tile(new AnimatedSprite(new BufferedImage[] { Res_Tiles.tilessprites[36] }), 0));
			currentfloor.getPlayerRoom().setTileAt(7, 9,
					new Tile(new AnimatedSprite(new BufferedImage[] { Res_Tiles.tilessprites[5] }), 1));
			currentfloor.getPlayerRoom().setTileAt(8, 9,
					new Tile(new AnimatedSprite(new BufferedImage[] { Res_Tiles.tilessprites[6] }), 0));
			currentfloor.getPlayerRoom().setTileAt(9, 9,
					new Tile(new AnimatedSprite(new BufferedImage[] { Res_Tiles.tilessprites[7] }), 1));
			currentfloor.getPlayerRoom().setTileAt(7, 14,
					new Tile(new AnimatedSprite(new BufferedImage[] { Res_Tiles.tilessprites[65] }), 1));
			currentfloor.getPlayerRoom().setTileAt(8, 14,
					new Tile(new AnimatedSprite(new BufferedImage[] { Res_Tiles.tilessprites[66] }), 0));
			currentfloor.getPlayerRoom().setTileAt(9, 14,
					new Tile(new AnimatedSprite(new BufferedImage[] { Res_Tiles.tilessprites[67] }), 1));
			currentfloor.getPlayerRoom().addEntity(new MobDeath(currentfloor.getPlayerRoom(), 12.5, 13.5));
		}
		if (currentfloor.isPlayerOnTile(101, 66) && lastdoor) {
			DungeonExplorer.sm.playSound(SoundsHolder.getSong("MC_FloorSwitch.mp3"));
			lastdoor = false;
			currentfloor.getPlayerRoom().setTileAt(18, 3,
					new Tile(new AnimatedSprite(new BufferedImage[] { Res_Tiles.tilessprites[36] }), 0));
			currentfloor.getPlayerRoom().setTileAt(20, 13,
					new Tile(new AnimatedSprite(new BufferedImage[] { Res_Tiles.tilessprites[1] }), 1));
			currentfloor.getPlayerRoom().setTileAt(20, 14,
					new Tile(new AnimatedSprite(new BufferedImage[] { Res_Tiles.tilessprites[21] }), 0));
			currentfloor.getPlayerRoom().setTileAt(20, 15,
					new Tile(new AnimatedSprite(new BufferedImage[] { Res_Tiles.tilessprites[41] }), 1));
			currentfloor.getPlayerRoom().addEntity(new MobDeath(currentfloor.getPlayerRoom(), 18.5, 3.5));
		}
		if (currentfloor.isPlayerOnTile(115, 64) || currentfloor.isPlayerOnTile(116, 64)
				|| currentfloor.isPlayerOnTile(117, 64)) {
			DungeonExplorer.sm.setBackgroundMusic(MusicHolder.getSong("MC_Boss.mp3"));
			PlayerInfo.posY = 60.5;
		}
	}

}
