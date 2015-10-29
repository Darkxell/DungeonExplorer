package management.player;

import management.Position;
import management.floors.CurrentFloorHolder;
import management.floors.Tile;
import display.sprites.entities.PlayerSpriteSheet;

/** Class that holds methods that help the player to walk. */
public abstract class WalkingUtility {

    /**
     * updates the player sprite if he is walking or not. THis method should not
     * be called if the player is doing something else than simply idling or
     * walking as it will reset the player sprite to the walking one.
     */
    protected static void updateWalksprite() {
	if (PlayerInfo.isPressingAKey()) {
	    switch (PlayerInfo.playerdirection) {
	    case PlayerInfo.LEFT:
		PlayerInfo.playersprite
			.setSpriteID(PlayerSpriteSheet.ID_WALK_LEFT);
		break;
	    case PlayerInfo.RIGHT:
		PlayerInfo.playersprite
			.setSpriteID(PlayerSpriteSheet.ID_WALK_LEFT);
		break;
	    case PlayerInfo.UP:
		PlayerInfo.playersprite
			.setSpriteID(PlayerSpriteSheet.ID_WALK_UP);
		break;
	    case PlayerInfo.DOWN:
		PlayerInfo.playersprite
			.setSpriteID(PlayerSpriteSheet.ID_WALK_DOWN);
		break;
	    }
	} else {
	    switch (PlayerInfo.playerdirection) {
	    case PlayerInfo.LEFT:
		PlayerInfo.playersprite
			.setSpriteID(PlayerSpriteSheet.ID_STILL_LEFT);
		break;
	    case PlayerInfo.RIGHT:
		PlayerInfo.playersprite
			.setSpriteID(PlayerSpriteSheet.ID_STILL_LEFT);
		break;
	    case PlayerInfo.UP:
		PlayerInfo.playersprite
			.setSpriteID(PlayerSpriteSheet.ID_STILL_UP);
		break;
	    case PlayerInfo.DOWN:
		PlayerInfo.playersprite
			.setSpriteID(PlayerSpriteSheet.ID_STILL_DOWN);
		break;
	    }
	}
    }

    /**
     * Moves the player according to his speed and the keys the player is
     * currently pressing. This only makes the player walk, he doesn't move
     * according to it's knockback ar anything.
     */
    protected static void walk() {
	double buffer = PlayerInfo.wspeed;
	if (PlayerInfo.isPressingMultipleKeys())
	    PlayerInfo.wspeed = 0.75 * PlayerInfo.wspeed;
	if (PlayerInfo.hold_left
		&& canWalkTo(PlayerInfo.posX - PlayerInfo.wspeed,
			PlayerInfo.posY)) {
	    PlayerInfo.posX -= PlayerInfo.wspeed;

	}
	if (PlayerInfo.hold_right
		&& canWalkTo(PlayerInfo.posX + PlayerInfo.wspeed,
			PlayerInfo.posY)) {
	    PlayerInfo.posX += PlayerInfo.wspeed;
	}
	if (PlayerInfo.hold_up
		&& canWalkTo(PlayerInfo.posX, PlayerInfo.posY
			- PlayerInfo.wspeed)) {
	    PlayerInfo.posY -= PlayerInfo.wspeed;
	}
	if (PlayerInfo.hold_down
		&& canWalkTo(PlayerInfo.posX, PlayerInfo.posY
			+ PlayerInfo.wspeed)) {
	    PlayerInfo.posY += PlayerInfo.wspeed;
	}
	PlayerInfo.wspeed = buffer;
    }

    /**
     * Predicate that returns true if the player can walk in the wanted
     * coordinates.
     */
    private static boolean canWalkTo(double toX, double toY) {
	Position[] playerhitbox = PlayerInfo.getPlayerHitbox(toX, toY);
	for (int i = 0; i < playerhitbox.length; i++) {
	    if (!Tile.canWalkOn(CurrentFloorHolder.CurrentFloor.getTileTypeAt(
		    (int) (playerhitbox[i].x), (int) (playerhitbox[i].y)),
		    playerhitbox[i].x - Math.floor(playerhitbox[i].x),
		    playerhitbox[i].y - Math.floor(playerhitbox[i].y))) {
		return false;
	    }
	}
	return true;
    }
}
