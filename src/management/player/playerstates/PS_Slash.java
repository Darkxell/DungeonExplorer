package management.player.playerstates;

import java.awt.image.BufferedImage;

import res.audio.SoundsHolder;
import util.DoubleRectangle;
import util.NumberUtil;
import display.sprites.entities.PlayerSpriteSheet;
import main.DungeonExplorer;
import management.floors.CurrentFloorHolder;
import management.floors.Room;
import management.player.Inventory;
import management.player.PlayerInfo;

public class PS_Slash implements PlayerState {

    private int countdown = 8;

    private int slashdirection;

    @Override
    public void update() {
	if (countdown == 8) {
	    DungeonExplorer.sm.playSound(SoundsHolder
		    .getSong("MC_Link_Sword.mp3"));
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
	    slash();
	}
	if (countdown == 6)
	    switch (NumberUtil.randomINT(1, 3)) {
	    case 1:
		DungeonExplorer.sm.playSound(SoundsHolder
			.getSong("MC_Link_Sword1.mp3"));
		break;
	    case 2:
		DungeonExplorer.sm.playSound(SoundsHolder
			.getSong("MC_Link_Sword2.mp3"));
		break;
	    case 3:
		DungeonExplorer.sm.playSound(SoundsHolder
			.getSong("MC_Link_Sword3.mp3"));
		break;
	    }
	--countdown;
	if (countdown <= 0)
	    if (!PlayerInfo.isPressingAKey())
		PlayerInfo.currentstate = new PS_Iddle();
	    else
		PlayerInfo.currentstate = new PS_Walk();
    }

    /** Creates particle effects on monsters and diminish their health. */
    private void slash() {
	DoubleRectangle slashhitbox = new DoubleRectangle(
		PlayerInfo.posX - 1.5, PlayerInfo.posY - 1.5, 3, 3);
	Room currentroom = CurrentFloorHolder.CurrentFloor.getPlayerRoom();
	if(currentroom != null)for (int i = 0; i < currentroom.entities.length; i++)
	    try {
		if (CurrentFloorHolder.CurrentFloor.getPlayerRoom().entities[i]
			.getHitbox(
				CurrentFloorHolder.CurrentFloor.getPlayerRoom().entities[i].posX
					+ CurrentFloorHolder.CurrentFloor
						.getPlayerRoom().posX,
				CurrentFloorHolder.CurrentFloor.getPlayerRoom().entities[i].posY
					+ CurrentFloorHolder.CurrentFloor
						.getPlayerRoom().posY)
			.intersects(slashhitbox))
		    CurrentFloorHolder.CurrentFloor.getPlayerRoom().entities[i]
			    .onhit();
	    } catch (Exception e) {
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
