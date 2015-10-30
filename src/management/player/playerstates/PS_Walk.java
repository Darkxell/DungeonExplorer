package management.player.playerstates;

import management.Position;
import management.floors.CurrentFloorHolder;
import management.floors.Tile;
import management.player.PlayerInfo;
import display.sprites.entities.PlayerSpriteSheet;

public class PS_Walk implements PlayerState {

    @Override
    public void update() {
	updateWalksprite();
	walk();
	if (!PlayerInfo.isPressingAKey())
	    PlayerInfo.currentstate = null;
    }

    /**
     * Updates the player sprite either if he is walking or not. This method
     * should not be called if the player is doing something else than simply
     * idling or walking as it will reset the player sprite to the walking one.
     */
    private void updateWalksprite() {
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
    private void walk() {
	double speed = 0.1d;
	if (PlayerInfo.isPressingMultipleKeys())
	    speed = 0.75 * speed;
	if (PlayerInfo.hold_left
		&& canWalkTo(PlayerInfo.posX - speed, PlayerInfo.posY)) {
	    PlayerInfo.posX -= speed;

	}
	if (PlayerInfo.hold_right
		&& canWalkTo(PlayerInfo.posX + speed, PlayerInfo.posY)) {
	    PlayerInfo.posX += speed;
	}
	if (PlayerInfo.hold_up
		&& canWalkTo(PlayerInfo.posX, PlayerInfo.posY - speed)) {
	    PlayerInfo.posY -= speed;
	}
	if (PlayerInfo.hold_down
		&& canWalkTo(PlayerInfo.posX, PlayerInfo.posY + speed)) {
	    PlayerInfo.posY += speed;
	}
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

    @Override
    public void actionRpress() {
    }

    @Override
    public void actionRrelease() {
    }

    @Override
    public void action1press() {
    }

    @Override
    public void action1release() {
    }

    @Override
    public void action2press() {
    }

    @Override
    public void action2release() {
    }

}
