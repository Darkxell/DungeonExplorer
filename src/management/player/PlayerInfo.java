package management.player;

import management.floors.CurrentFloorHolder;
import display.GameCanvas;
import display.sprites.SpriteSheet;
import display.sprites.entities.PlayerSpriteSheet;

/**
 * Static class that holds all of the player information. This could be an
 * object, but since there is only one player at a time (at least for now, don't
 * get your hopes up...) I guess I might as well do it static way.
 */
public abstract class PlayerInfo {

    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;

    public static boolean hold_left;
    public static boolean hold_right;
    public static boolean hold_up;
    public static boolean hold_down;

    public static int playerupdater;

    /**
     * The X position of the player in the Floor. keep in mind that this is not
     * the position in the room.
     */
    public static double posX = 3.0d;
    /**
     * The Y position of the player in the Floor. keep in mind taht this is not
     * the position in the room.
     */
    public static double posY = 3.0d;
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

    /** The direction faced by the player. Is equals to the last key PRESSED. */
    public static int playerdirection = DOWN;

    public static SpriteSheet playersprite = PlayerSpriteSheet.create();

    /** The number of ruppees the player currently has. */
    public static int ruppees;
    /** The current player health. */
    public static double health = 3.0d;
    /** The walking speed of the player. */
    public static double wspeed = 0.1d;

    /** Updates the player. */
    public static void update() {
	movecamera();

	++playerupdater;
	if (playerupdater > 2) {
	    playerupdater = 0;
	    playersprite.next();
	}
	// TODO : check if the player isn't doing something else before calling
	// thoose methods.
	WalkingUtility.updateWalksprite();
	WalkingUtility.walk();
    }

    /** Moves the camera smartly towards the player. */
    private static void movecamera() {
	if (iscaminboundsat(cameraX, cameraY)) {
	    if (cameraX > posX + 1 && iscaminboundsat(cameraX - 0.1, cameraY))
		cameraX -= 0.1;
	    if (cameraX < posX - 1 && iscaminboundsat(cameraX + 0.1, cameraY))
		cameraX += 0.1;
	    if (cameraY > posY + 1 && iscaminboundsat(cameraX, cameraY - 0.1))
		cameraY -= 0.1;
	    if (cameraY < posY - 1 && iscaminboundsat(cameraX, cameraY + 0.1))
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
	return cameraX2 > CurrentFloorHolder.CurrentFloor.getPlayerRoom().posX
		+ ((double) GameCanvas.ScreenWidth / 32)
		&& cameraX2 < CurrentFloorHolder.CurrentFloor.getPlayerRoom().posX
			+ CurrentFloorHolder.CurrentFloor.getPlayerRoom().width
			- ((double) GameCanvas.ScreenWidth / 32)
		&& cameraY2 > CurrentFloorHolder.CurrentFloor.getPlayerRoom().posY
			+ (GameCanvas.ScreenHeight / 32)
		&& cameraY2 < CurrentFloorHolder.CurrentFloor.getPlayerRoom().posY
			+ CurrentFloorHolder.CurrentFloor.getPlayerRoom().height
			- (GameCanvas.ScreenHeight / 32);
    }

    /**
     * Returns true if the player is currently pressing one of the four
     * directional keys. Returns true if a dir key is pressed, doesn't check for
     * other dir keys actions.
     */
    protected static boolean isPressingAKey() {
	return hold_left || hold_right || hold_up || hold_down;
    }

    /**
     * Executes the interact script of the player. This is equivalent to
     * pressing the R button in a GBA.
     */
    public static void interact() {

    }

    /**
     * Executes the action1 script of the player. This is equivalent to pressing
     * the A button in a GBA.
     */
    public static void action1press() {

    }

    /**
     * Executes the action1 release script of the player. This is equivalent to
     * relasing the A button in a GBA.
     */
    public static void action1release() {

    }

    /**
     * Executes the action2 script of the player. This is equivalent to pressing
     * the B button in a GBA.
     */
    public static void action2press() {

    }

    /**
     * Executes the action2 release script of the player. This is equivalent to
     * relasing the B button in a GBA.
     */
    public static void action2release() {

    }

    public static void leftpress() {
	if (!isPressingAKey())
	    playerdirection = LEFT;
	hold_left = true;
    }

    public static void leftrelease() {
	hold_left = false;
	if (isPressingAKey()) {
	    if (hold_right)
		playerdirection = RIGHT;
	    if (hold_up)
		playerdirection = UP;
	    if (hold_down)
		playerdirection = DOWN;
	}
    }

    public static void rightpress() {
	if (!isPressingAKey())
	    playerdirection = RIGHT;
	hold_right = true;
    }

    public static void rightrelease() {
	hold_right = false;
	if (isPressingAKey()) {
	    if (hold_left)
		playerdirection = LEFT;
	    if (hold_up)
		playerdirection = UP;
	    if (hold_down)
		playerdirection = DOWN;
	}
    }

    public static void downpress() {
	if (!isPressingAKey())
	    playerdirection = DOWN;
	hold_down = true;
    }

    public static void downrelease() {
	hold_down = false;
	if (isPressingAKey()) {
	    if (hold_left)
		playerdirection = LEFT;
	    if (hold_right)
		playerdirection = RIGHT;
	    if (hold_up)
		playerdirection = UP;
	}
    }

    public static void uppress() {
	if (!isPressingAKey())
	    playerdirection = UP;
	hold_up = true;
    }

    public static void uprelease() {
	hold_up = false;
	if (isPressingAKey()) {
	    if (hold_left)
		playerdirection = LEFT;
	    if (hold_right)
		playerdirection = RIGHT;
	    if (hold_down)
		playerdirection = DOWN;
	}
    }
}
