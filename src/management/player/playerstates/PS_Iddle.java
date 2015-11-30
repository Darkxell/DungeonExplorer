package management.player.playerstates;

import java.awt.image.BufferedImage;

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

    }

    @Override
    public void actionRpress() {
    }

    @Override
    public void actionRrelease() {
    }

    @Override
    public void action1press() {
	if (PlayerInfo.hand_S_itemID == Inventory.ITEM_SWORD)
	    PlayerInfo.currentstate = new PS_Slash();
    }

    @Override
    public void action1release() {
    }

    @Override
    public void action2press() {
	if (PlayerInfo.hand_D_itemID == Inventory.ITEM_SWORD)
	    PlayerInfo.currentstate = new PS_Slash();
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
	return null;
    }

}
