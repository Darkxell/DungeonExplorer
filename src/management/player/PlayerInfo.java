package management.player;

import management.Position;
import management.player.playerstates.PlayerState;
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

    private static int playerupdater;

    /** The player inventory. */
    public static Inventory playerInventory = new Inventory();

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
     * The direction faced by the player. Is equals to the last key PRESSED if
     * you are not already pressing an other one.
     */
    public static int playerdirection = DOWN;
    /**
     * is true if the player direction is locked. pressing keys while the player
     * direction is locked will not change it.
     */
    public static boolean isDirectionLocked;

    public static SpriteSheet playersprite = PlayerSpriteSheet.create();

    /** The current player health. */
    public static double health = 3.0d;
    /** The walking speed of the player. */
    public static double wspeed = 0.1d;

    /**
     * The current state of the player. Is <code>null</code> if the player is
     * not using an item, not rolling, knocked back or using an item, meaning
     * that he is standing still or walking.
     */
    public static PlayerState currentstate;

    /** The id of the item in the player S hand. */
    public static int hand_S_itemID;
    /** The id of the item in the player D hand. */
    public static int hand_D_itemID;

    /** Updates the player. */
    public static void update() {
	ViewCamera.movecamera();

	++playerupdater;
	if (playerupdater > 2) {
	    playerupdater = 0;
	    playersprite.next();
	}

	if (currentstate == null) {
	    WalkingUtility.updateWalksprite();
	    WalkingUtility.walk();
	} else {
	    currentstate.update();
	}
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
     * Same as <code>isPessingAKey()</code> but returns true if the player is
     * holding at least two keys.
     */
    protected static boolean isPressingMultipleKeys() {
	if (hold_left)
	    if (hold_right || hold_up || hold_down)
		return true;
	if (hold_right)
	    if (hold_left || hold_up || hold_down)
		return true;
	if (hold_up)
	    if (hold_right || hold_left || hold_down)
		return true;
	if (hold_down)
	    if (hold_right || hold_up || hold_left)
		return true;
	return false;
    }

    /**
     * Returns 9 points representing the player position. Since the player is
     * smaller than a tile, this works with only 3 positions per side, plus a
     * middle one. Keep in mind that this doesn't represent a full hitbox but
     * only some points inside it.<br/>
     * This returns an hypotetical player hitbox using the specified
     * coordinates. If you want the straight up player hitbox, use
     * <code>getplayerHitbox(PlayerInfo.posX,PlayerInfo.posY)</code>;
     */
    public static Position[] getPlayerHitbox(double posX, double posY) {
	Position[] points = new Position[9];
	double halfsize = 0.3d;
	points[0] = new Position(posX - halfsize, posY - halfsize);
	points[1] = new Position(posX, posY - halfsize);
	points[2] = new Position(posX + halfsize, posY - halfsize);
	points[3] = new Position(posX - halfsize, posY);
	points[4] = new Position(posX, posY);
	points[5] = new Position(posX + halfsize, posY);
	points[6] = new Position(posX - halfsize, posY + halfsize);
	points[7] = new Position(posX, posY + halfsize);
	points[8] = new Position(posX + halfsize, posY + halfsize);
	return points;
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
	if (!isPressingAKey() && !isDirectionLocked)
	    playerdirection = LEFT;
	hold_left = true;
    }

    public static void leftrelease() {
	hold_left = false;
	if (isPressingAKey() && !isDirectionLocked) {
	    if (hold_right)
		playerdirection = RIGHT;
	    if (hold_up)
		playerdirection = UP;
	    if (hold_down)
		playerdirection = DOWN;
	}
    }

    public static void rightpress() {
	if (!isPressingAKey() && !isDirectionLocked)
	    playerdirection = RIGHT;
	hold_right = true;
    }

    public static void rightrelease() {
	hold_right = false;
	if (isPressingAKey() && !isDirectionLocked) {
	    if (hold_left)
		playerdirection = LEFT;
	    if (hold_up)
		playerdirection = UP;
	    if (hold_down)
		playerdirection = DOWN;
	}
    }

    public static void downpress() {
	if (!isPressingAKey() && !isDirectionLocked)
	    playerdirection = DOWN;
	hold_down = true;
    }

    public static void downrelease() {
	hold_down = false;
	if (isPressingAKey() && !isDirectionLocked) {
	    if (hold_left)
		playerdirection = LEFT;
	    if (hold_right)
		playerdirection = RIGHT;
	    if (hold_up)
		playerdirection = UP;
	}
    }

    public static void uppress() {
	if (!isPressingAKey() && !isDirectionLocked)
	    playerdirection = UP;
	hold_up = true;
    }

    public static void uprelease() {
	hold_up = false;
	if (isPressingAKey() && !isDirectionLocked) {
	    if (hold_left)
		playerdirection = LEFT;
	    if (hold_right)
		playerdirection = RIGHT;
	    if (hold_down)
		playerdirection = DOWN;
	}
    }
}
