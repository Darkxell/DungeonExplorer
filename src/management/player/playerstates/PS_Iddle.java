package management.player.playerstates;

import java.awt.image.BufferedImage;

import res.images.Res_Player;
import management.floors.CurrentFloorHolder;
import management.floors.specialtiles.KeyDoor;
import management.player.Inventory;
import management.player.PlayerInfo;
import display.sprites.entities.PlayerSpriteSheet;

public class PS_Iddle implements PlayerState {

    @Override
    public void update() {
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
	    PlayerInfo.playersprite.setSpriteID(PlayerSpriteSheet.ID_STILL_UP);
	    break;
	case PlayerInfo.DOWN:
	    PlayerInfo.playersprite
		    .setSpriteID(PlayerSpriteSheet.ID_STILL_DOWN);
	    break;
	}
	if (PlayerInfo.health <= 0)
	    PlayerInfo.currentstate = new PS_Faint();

    }

    @Override
    public void actionRpress() {
	if (PlayerInfo.getTileFacingPlayer() instanceof KeyDoor
		&& PlayerInfo.playerInventory.keys >= 1) {
	    KeyDoor.openDoorAt((int) PlayerInfo.getCoordsFacingPlayer().x,
		    (int) PlayerInfo.getCoordsFacingPlayer().y,
		    CurrentFloorHolder.CurrentFloor.getPlayerRoom());
	    PlayerInfo.playerInventory.keys--;
	}
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
	PlayerInfo.currentstate = new PS_Walk();
    }

    @Override
    public void pressRight() {
	PlayerInfo.currentstate = new PS_Walk();
    }

    @Override
    public void pressUp() {
	PlayerInfo.currentstate = new PS_Walk();
    }

    @Override
    public void pressDown() {
	PlayerInfo.currentstate = new PS_Walk();
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
	return null;
    }

}
