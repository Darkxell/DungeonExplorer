package management.floors;

import java.awt.Color;
import java.awt.Graphics2D;

import display.GameCanvas;
import management.entities.Entity;
import management.floors.floorsmanagers.FloorManager;
import management.floors.floorsmanagers.TestDungeonManager;
import management.player.PlayerInfo;
import management.player.ViewCamera;

/** Static class that hold the current floor. */
public abstract class CurrentFloorHolder {

	public static Floor CurrentFloor;
	public static FloorManager manager = new TestDungeonManager();

	/** Updates the room the player is currently in. */
	public static void updatePlayerRoom() {

		try {
			Room cpr = CurrentFloor.getPlayerRoom();
			if(cpr!=null)
				cpr.update();
		} catch (Exception e) {
			System.err.println("Error in updater : couldn't locate player room. No room updated.");
			e.printStackTrace();
		}
		manager.update();
	}

	/**
	 * Prints the whole floor on the specified Graphics2D object. This prints the
	 * tiles and the entities, including the player.
	 */
	public static void printFloor(Graphics2D g2d) {
		int translateX = (int) (-ViewCamera.cameraX * 16 + GameCanvas.ScreenWidth / 2);
		int translateY = (int) (-ViewCamera.cameraY * 16 + GameCanvas.ScreenHeight / 2);

		g2d.translate(translateX, translateY);

		for (int i = 0; i < CurrentFloor.rooms.length; i++) {
			CurrentFloor.rooms[i].print(g2d, CurrentFloor.rooms[i].posX * 16, CurrentFloor.rooms[i].posY * 16);
		}

		int pSpriteSize = PlayerInfo.playersprite.getCurrentSprite().getHeight();
		if (PlayerInfo.currentstate.isInvertedRight())
			g2d.drawImage(PlayerInfo.playersprite.getCurrentSprite(), (int) (PlayerInfo.posX * 16 + (pSpriteSize / 2)),
					(int) (PlayerInfo.posY * 16 - pSpriteSize / 2), -pSpriteSize, pSpriteSize, null);
		else
			g2d.drawImage(PlayerInfo.playersprite.getCurrentSprite(), (int) (PlayerInfo.posX * 16 - pSpriteSize / 2),
					(int) (PlayerInfo.posY * 16 - pSpriteSize / 2), null);

		for (int i = 0; i < CurrentFloor.rooms.length; i++) {
			CurrentFloor.rooms[i].printOvertexture(g2d, CurrentFloor.rooms[i].posX * 16,
					CurrentFloor.rooms[i].posY * 16);
		}

		if (PlayerInfo.DEBUGMODE) {
			g2d.setColor(new Color(0, 120, 230, 130));
			g2d.drawRect((int) (PlayerInfo.posX * 16 - pSpriteSize / 2), (int) (PlayerInfo.posY * 16 - pSpriteSize / 2),
					pSpriteSize, pSpriteSize);
			g2d.setColor(new Color(250, 0, 0, 130));
			int hitboxpx = (int) (0.3 * 32);
			g2d.fillRect((int) (PlayerInfo.posX * 16) - hitboxpx / 2, (int) (PlayerInfo.posY * 16) - hitboxpx / 2,
					hitboxpx, hitboxpx);
		}

		g2d.translate(-translateX, -translateY);

		Room currentroom = CurrentFloor.getPlayerRoom();
		if (currentroom != null) {
			Entity[] overmobs = CurrentFloor.getPlayerRoom().entities;
			for (int i = 0; i < overmobs.length; i++)
				overmobs[i].printOnUI(g2d);
		}

	}

}
