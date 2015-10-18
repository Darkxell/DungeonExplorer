package management.floors;

import java.awt.Graphics2D;

import res.Res;

/** Static class that hold the current floor. */
public abstract class CurrentFloorHolder {

    /** The depth of the current floor. */
    public static int depth = 0;

    public static Floor CurrentFloor = new Floor(Res.FOLDER_PATH + "");

    // TODO: set the default floor here.

    /** Updates the room the player is currently in. */
    public static void updatePlayerRoom() {
	CurrentFloor.getPlayerRoom().update();
    }

    /** Prints the current room on the specified Graphics2D object. */
    public static void printPlayerRoom(Graphics2D g2d) {
	CurrentFloor.getPlayerRoom().print(g2d, 0, 0);// TODO change the
						      // position
    }

}
