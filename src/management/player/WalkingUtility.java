package management.player;

import management.floors.CurrentFloorHolder;
import management.floors.Tile;
import display.sprites.entities.PlayerSpriteSheet;

/** Class that holds methods that help the makes the player walk. */
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
	if (PlayerInfo.hold_left
		&& (Tile.canWalkOn(CurrentFloorHolder.CurrentFloor
			.getTileTypeAt(
				(int) (PlayerInfo.posX - PlayerInfo.wspeed),
				(int) (PlayerInfo.posY)), Math
			.floor(PlayerInfo.posX - PlayerInfo.wspeed), Math
			.floor(PlayerInfo.posY)))) {
	    PlayerInfo.posX -= PlayerInfo.wspeed;

	}
	if (PlayerInfo.hold_right
		&& Tile.canWalkOn(CurrentFloorHolder.CurrentFloor
			.getTileTypeAt(
				(int) (PlayerInfo.posX + PlayerInfo.wspeed),
				(int) (PlayerInfo.posY)), Math
			.floor(PlayerInfo.posX + PlayerInfo.wspeed), Math
			.floor(PlayerInfo.posY))) {
	    PlayerInfo.posX += PlayerInfo.wspeed;
	}
	if (PlayerInfo.hold_up
		&& Tile.canWalkOn(CurrentFloorHolder.CurrentFloor
			.getTileTypeAt((int) (PlayerInfo.posX),
				(int) (PlayerInfo.posY - PlayerInfo.wspeed)),
			Math.floor(PlayerInfo.posX), Math.floor(PlayerInfo.posY
				- PlayerInfo.wspeed))) {
	    PlayerInfo.posY -= PlayerInfo.wspeed;
	}
	if (PlayerInfo.hold_down
		&& Tile.canWalkOn(CurrentFloorHolder.CurrentFloor
			.getTileTypeAt((int) (PlayerInfo.posX),
				(int) (PlayerInfo.posY + PlayerInfo.wspeed)),
			Math.floor(PlayerInfo.posX), Math.floor(PlayerInfo.posY
				+ PlayerInfo.wspeed))) {
	    PlayerInfo.posY += PlayerInfo.wspeed;
	}
    }

}
