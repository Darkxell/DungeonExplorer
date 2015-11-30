package management.player;

import management.floors.CurrentFloorHolder;
import display.GameCanvas;

public abstract class ViewCamera {
    /**
     * The X position of the camera in the floor. Pretty much follows the player
     * Xpos at set speed.
     */
    public static double cameraX;
    /**
     * The Y position of the camera in the floor. Pretty much follows the player
     * Ypos at set speed.
     */
    public static double cameraY;

    /** Moves the camera smartly towards the player. */
    protected static void movecamera() {
	if (iscaminboundsat(cameraX, cameraY)) {
	    if (cameraX > PlayerInfo.posX + 1
		    && iscaminboundsat(cameraX - 0.1, cameraY))
		cameraX -= 0.1;
	    if (cameraX < PlayerInfo.posX - 1
		    && iscaminboundsat(cameraX + 0.1, cameraY))
		cameraX += 0.1;
	    if (cameraY > PlayerInfo.posY + 1
		    && iscaminboundsat(cameraX, cameraY - 0.1))
		cameraY -= 0.1;
	    if (cameraY < PlayerInfo.posY - 1
		    && iscaminboundsat(cameraX, cameraY + 0.1))
		cameraY += 0.1;
	} else {
	    if (cameraX < CurrentFloorHolder.CurrentFloor.getPlayerRoom().posX
		    + ((double) GameCanvas.ScreenWidth / 32))
		cameraX += 0.2;
	    if (cameraX > CurrentFloorHolder.CurrentFloor.getPlayerRoom().posX
		    + CurrentFloorHolder.CurrentFloor.getPlayerRoom().width
		    - ((double) GameCanvas.ScreenWidth / 32))
		cameraX -= 0.2;
	    if (cameraY < CurrentFloorHolder.CurrentFloor.getPlayerRoom().posY
		    + (GameCanvas.ScreenHeight / 32))
		cameraY += 0.2;
	    if (cameraY > CurrentFloorHolder.CurrentFloor.getPlayerRoom().posY
		    + CurrentFloorHolder.CurrentFloor.getPlayerRoom().height
		    - (GameCanvas.ScreenHeight / 32))
		cameraY -= 0.2;
	}
    }

    /**
     * Returns true if an hypotetical camera in the x and y position would be in
     * the room.
     */
    private static boolean iscaminboundsat(double cameraX2, double cameraY2) {
	try {
	    return cameraX2 > CurrentFloorHolder.CurrentFloor.getPlayerRoom().posX
		    + ((double) GameCanvas.ScreenWidth / 32)
		    && cameraX2 < CurrentFloorHolder.CurrentFloor
			    .getPlayerRoom().posX
			    + CurrentFloorHolder.CurrentFloor.getPlayerRoom().width
			    - ((double) GameCanvas.ScreenWidth / 32)
		    && cameraY2 > CurrentFloorHolder.CurrentFloor
			    .getPlayerRoom().posY
			    + (GameCanvas.ScreenHeight / 32)
		    && cameraY2 < CurrentFloorHolder.CurrentFloor
			    .getPlayerRoom().posY
			    + CurrentFloorHolder.CurrentFloor.getPlayerRoom().height
			    - (GameCanvas.ScreenHeight / 32);
	} catch (Exception e) {
	    System.err
		    .println("Error in camera : couldn't locate player room. Camera follows the player.");
	    return true;
	}
    }
}
