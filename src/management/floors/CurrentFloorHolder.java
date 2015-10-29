package management.floors;

import java.awt.Graphics2D;

import display.GameCanvas;
import management.player.PlayerInfo;
import management.player.ViewCamera;
import res.Res;

/** Static class that hold the current floor. */
public abstract class CurrentFloorHolder {

    /** The depth of the current floor. */
    public static int depth = 0;

    public static Floor CurrentFloor = new Floor(Res.FOLDER_PATH
	    + "maps\\testdungeon");

    // TODO: set the default floor here.

    /** Updates the room the player is currently in. */
    public static void updatePlayerRoom() {
	CurrentFloor.getPlayerRoom().update();
    }

    /** Prints the whole floor on the specified Graphics2D object. */
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
	if (PlayerInfo.playerdirection == PlayerInfo.RIGHT) {
	    g2d.drawImage(PlayerInfo.playersprite.getCurrentSprite(),
		    (int) (PlayerInfo.posX * 16 + (pSpriteSize / 2)),
		    (int) (PlayerInfo.posY * 16 - pSpriteSize / 2),
		    -pSpriteSize, pSpriteSize, null);
	} else {
	    g2d.drawImage(PlayerInfo.playersprite.getCurrentSprite(),
		    (int) (PlayerInfo.posX * 16 - pSpriteSize / 2),
		    (int) (PlayerInfo.posY * 16 - pSpriteSize / 2), null);
	}

	g2d.translate(-translateX, -translateY);

    }

}
