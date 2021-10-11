package management.player.playerstates;

import java.awt.image.BufferedImage;

import res.images.Res_Player;
import management.Position;
import management.floors.CurrentFloorHolder;
import management.floors.Tile;
import management.floors.specialtiles.KeyDoor;
import management.player.Inventory;
import management.player.PlayerInfo;
import display.sprites.entities.PlayerSpriteSheet;

public class PS_Walk implements PlayerState {

    @Override
    public void update() {
	updateWalksprite();
	walk();
	if (!PlayerInfo.isPressingAKey())
	    PlayerInfo.currentstate = new PS_Iddle();
	if (PlayerInfo.health <= 0)
	    PlayerInfo.currentstate = new PS_Faint();
    }

    /**
     * Updates the player sprite either if he is walking or not. This method
     * should not be called if the player is doing something else than simply
     * idling or walking as it will reset the player sprite to the walking one.
     */
    private void updateWalksprite() {
	switch (PlayerInfo.playerdirection) {
	case PlayerInfo.LEFT:
	    PlayerInfo.playersprite.setSpriteID(PlayerSpriteSheet.ID_WALK_LEFT);
	    break;
	case PlayerInfo.RIGHT:
	    PlayerInfo.playersprite.setSpriteID(PlayerSpriteSheet.ID_WALK_LEFT);
	    break;
	case PlayerInfo.UP:
	    PlayerInfo.playersprite.setSpriteID(PlayerSpriteSheet.ID_WALK_UP);
	    break;
	case PlayerInfo.DOWN:
	    PlayerInfo.playersprite.setSpriteID(PlayerSpriteSheet.ID_WALK_DOWN);
	    break;
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
    public static boolean canWalkTo(double toX, double toY) {
	if (PlayerInfo.DEBUGMODE)
	    return true;
	Position[] playerhitbox = PlayerInfo.getPlayerHitbox(toX, toY).cardinals;
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
	if (PlayerInfo.getTileFacingPlayer() instanceof KeyDoor
		&& PlayerInfo.playerInventory.keys >= 1) {
	    KeyDoor.openDoorAt((int) PlayerInfo.getCoordsFacingPlayer().x,
		    (int) PlayerInfo.getCoordsFacingPlayer().y,
		    CurrentFloorHolder.CurrentFloor.getPlayerRoom());
	    PlayerInfo.playerInventory.keys--;
	} else
	    PlayerInfo.currentstate = new PS_Roll();
    }

    @Override
    public void actionRrelease() {
    }

	@Override
	public void action1press() {
		if (PlayerInfo.hand_S_itemID == Inventory.ITEM_SWORD)
			PlayerInfo.currentstate = new PS_Slash();
		if (PlayerInfo.hand_S_itemID == Inventory.ITEM_BOOMERANG)
			PlayerInfo.currentstate = new PS_ThrowBoomerang();
	}

	@Override
	public void action1release() {
	}

	@Override
	public void action2press() {
		if (PlayerInfo.hand_D_itemID == Inventory.ITEM_SWORD)
			PlayerInfo.currentstate = new PS_Slash();
		if (PlayerInfo.hand_D_itemID == Inventory.ITEM_BOOMERANG)
			PlayerInfo.currentstate = new PS_ThrowBoomerang();
	}

    @Override
    public void action2release() {
    }

    @Override
    public void pressLeft() {
    }

    @Override
    public void pressRight() {
    }

    @Override
    public void pressUp() {
    }

    @Override
    public void pressDown() {
    }

    @Override
    public void releaseLeft() {
    }

    @Override
    public void releaseRight() {
    }

    @Override
    public void releaseUp() {
    }

    @Override
    public void releaseDown() {
    }

    @Override
    public boolean isInvertedRight() {
	return PlayerInfo.playerdirection == PlayerInfo.RIGHT;
    }

    @Override
    public BufferedImage getRightPText() {
	if (PlayerInfo.getTileFacingPlayer() instanceof KeyDoor
		&& PlayerInfo.playerInventory.keys >= 1)
	    return Res_Player.text_open;
	return Res_Player.text_roll;
    }

}
