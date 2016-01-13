package management.floors;

import java.awt.Graphics2D;

import display.GameCanvas;
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
	    cpr.update();
	} catch (Exception e) {
	    System.err
		    .println("Error in updater : couldn't locate player room. No room updated.");
	    e.printStackTrace();
	}
	manager.update();
    }

    /**
     * Prints the whole floor on the specified Graphics2D object. This prints
     * the tiles and the entities, including the player.
     */
    public static void printFloor(Graphics2D g2d) {
	int translateX = (int) (-ViewCamera.cameraX * 16 + GameCanvas.ScreenWidth / 2);
	int translateY = (int) (-ViewCamera.cameraY * 16 + GameCanvas.ScreenHeight / 2);

	g2d.translate(translateX, translateY);

	for (int i = 0; i < CurrentFloor.rooms.length; i++) {
	    CurrentFloor.rooms[i].print(g2d, CurrentFloor.rooms[i].posX * 16,
		    CurrentFloor.rooms[i].posY * 16);
	}

	int pSpriteSize = PlayerInfo.playersprite.getCurrentSprite()
		.getHeight();
	if (PlayerInfo.currentstate.isInvertedRight())
	    g2d.drawImage(PlayerInfo.playersprite.getCurrentSprite(),
		    (int) (PlayerInfo.posX * 16 + (pSpriteSize / 2)),
		    (int) (PlayerInfo.posY * 16 - pSpriteSize / 2),
		    -pSpriteSize, pSpriteSize, null);
	else
	    g2d.drawImage(PlayerInfo.playersprite.getCurrentSprite(),
		    (int) (PlayerInfo.posX * 16 - pSpriteSize / 2),
		    (int) (PlayerInfo.posY * 16 - pSpriteSize / 2), null);
	g2d.translate(-translateX, -translateY);

    }

}
