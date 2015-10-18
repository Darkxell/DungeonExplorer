package management.player;

import display.sprites.SpriteSheet;
import display.sprites.entities.PlayerSpriteSheet;

/**
 * Static class that holds all of the player information. THis could be an
 * object, but since there is only one player at a time (at least for now, don't
 * get your hopes up...) I guess I might as well do it static way.
 */
public abstract class PlayerInfo {

    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;

    public static int playerupdater;
    
    public static int posX;
    public static int posY;
    public static int cameraX;
    public static int cameraY;

    /** The direction faced by the player. Is equals to the last key PRESSED. */
    public static int playerdirection = DOWN;

    public static SpriteSheet playersprite = PlayerSpriteSheet.create();

    /** The number of ruppees the player currently has. */
    public static int ruppees;
    /** The current player health. */
    public static double health = 3.0d;

    /** Updates the player. */
    public static void update() {
	++playerupdater;
	if (playerupdater > 2) {
	    playerupdater = 0;
	    playersprite.next();
	}
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
	playerdirection = LEFT;
	playersprite.setSpriteID(PlayerSpriteSheet.ID_WALK_LEFT);
    }

    public static void leftrelease() {
	playersprite.setSpriteID(PlayerSpriteSheet.ID_STILL_LEFT);
    }

    public static void rightpress() {
	playerdirection = RIGHT;
	playersprite.setSpriteID(PlayerSpriteSheet.ID_WALK_LEFT);
    }

    public static void rightrelease() {
	playersprite.setSpriteID(PlayerSpriteSheet.ID_STILL_LEFT);
    }

    public static void downpress() {
	playerdirection = DOWN;
	playersprite.setSpriteID(PlayerSpriteSheet.ID_WALK_DOWN);
    }

    public static void downrelease() {
	playersprite.setSpriteID(PlayerSpriteSheet.ID_STILL_DOWN);
    }

    public static void uppress() {
	playerdirection = UP;
	playersprite.setSpriteID(PlayerSpriteSheet.ID_WALK_UP);
    }

    public static void uprelease() {
	playersprite.setSpriteID(PlayerSpriteSheet.ID_STILL_UP);
    }

}
