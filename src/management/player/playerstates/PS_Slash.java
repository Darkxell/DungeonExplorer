package management.player.playerstates;

import java.awt.image.BufferedImage;

import display.sprites.entities.PlayerSpriteSheet;
import management.player.Inventory;
import management.player.PlayerInfo;

public class PS_Slash implements PlayerState {

    private int countdown = 8;

    private int slashdirection;

    @Override
    public void update() {
	if (countdown == 8) {
	    slashdirection = PlayerInfo.playerdirection;
	    switch (PlayerInfo.playerdirection) {
	    case PlayerInfo.DOWN:
		PlayerInfo.playersprite
			.setSpriteID(PlayerSpriteSheet.ID_SLASH_DOWN_1);
		break;
	    case PlayerInfo.UP:
		PlayerInfo.playersprite
			.setSpriteID(PlayerSpriteSheet.ID_SLASH_UP_1);
		break;
	    case PlayerInfo.LEFT:
		PlayerInfo.playersprite
			.setSpriteID(PlayerSpriteSheet.ID_SLASH_LEFT_1);
		break;
	    case PlayerInfo.RIGHT:
		PlayerInfo.playersprite
			.setSpriteID(PlayerSpriteSheet.ID_SLASH_LEFT_1);
		break;
	    }
	}
	--countdown;
	if (countdown <= 0)
	    if (!PlayerInfo.isPressingAKey())
		PlayerInfo.currentstate = new PS_Iddle();
	    else
		PlayerInfo.currentstate = new PS_Walk();
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
	return slashdirection == PlayerInfo.RIGHT;
    }

    @Override
    public BufferedImage getRightPText() {
	return null;
    }

}
