package management.player;

import management.floors.CurrentFloorHolder;
import display.GameCanvas;

public abstract class ViewCamera {
	/**
	 * The X position of the camera in the floor. Pretty much follows the player
	 * Xpos at set speed.
	 */
	public static double cameraX = PlayerInfo.posX;
	/**
	 * The Y position of the camera in the floor. Pretty much follows the player
	 * Ypos at set speed.
	 */
	public static double cameraY = PlayerInfo.posY;

	/** Moves the camera smartly towards the player. */
	protected static void movecamera() {
		double dist = 0.1;
		if (iscaminboundsat(cameraX, cameraY)) {
			dist = isCameraFarFromPlayer() ? 0.3 : 0.15;
			if (cameraX > PlayerInfo.posX + 1 && iscaminboundsat(cameraX - dist, cameraY))
				cameraX -= dist;
			if (cameraX < PlayerInfo.posX - 1 && iscaminboundsat(cameraX + dist, cameraY))
				cameraX += dist;
			if (cameraY > PlayerInfo.posY + 1 && iscaminboundsat(cameraX, cameraY - dist))
				cameraY -= dist;
			if (cameraY < PlayerInfo.posY - 1 && iscaminboundsat(cameraX, cameraY + dist))
				cameraY += dist;
		} else {
			dist = 0.4;
			if (cameraX < CurrentFloorHolder.CurrentFloor.getPlayerRoom().posX + ((double) GameCanvas.ScreenWidth / 32))
				cameraX += dist;
			if (cameraX > CurrentFloorHolder.CurrentFloor.getPlayerRoom().posX
					+ CurrentFloorHolder.CurrentFloor.getPlayerRoom().width - ((double) GameCanvas.ScreenWidth / 32))
				cameraX -= dist;
			if (cameraY < CurrentFloorHolder.CurrentFloor.getPlayerRoom().posY + (GameCanvas.ScreenHeight / 32))
				cameraY += dist;
			if (cameraY > CurrentFloorHolder.CurrentFloor.getPlayerRoom().posY
					+ CurrentFloorHolder.CurrentFloor.getPlayerRoom().height - (GameCanvas.ScreenHeight / 32))
				cameraY -= dist;
		}
	}

	/**
	 * Returns true if an hypothetical camera in the x and y position would be in the
	 * room.
	 */
	private static boolean iscaminboundsat(double cameraX2, double cameraY2) {
		try {
			return cameraX2 > CurrentFloorHolder.CurrentFloor.getPlayerRoom().posX
					+ ((double) GameCanvas.ScreenWidth / 32)
					&& cameraX2 < CurrentFloorHolder.CurrentFloor.getPlayerRoom().posX
							+ CurrentFloorHolder.CurrentFloor.getPlayerRoom().width
							- ((double) GameCanvas.ScreenWidth / 32)
					&& cameraY2 > CurrentFloorHolder.CurrentFloor.getPlayerRoom().posY + (GameCanvas.ScreenHeight / 32)
					&& cameraY2 < CurrentFloorHolder.CurrentFloor.getPlayerRoom().posY
							+ CurrentFloorHolder.CurrentFloor.getPlayerRoom().height - (GameCanvas.ScreenHeight / 32);
		} catch (Exception e) {
			System.err.println("Error in camera : couldn't locate player room. Camera follows the player.");
			return true;
		}
	}

	/** Predicate that returns true if the camera is far from the player. */
	private static boolean isCameraFarFromPlayer() {
		if ((cameraX > PlayerInfo.posX + 5) || (cameraX < PlayerInfo.posX - 5) || (cameraY > PlayerInfo.posY + 4)
				|| (cameraY < PlayerInfo.posY - 4))
			return true;
		return false;
	}
}
