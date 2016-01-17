package management.player.playerstates;

import java.awt.image.BufferedImage;

import res.audio.SoundsHolder;
import util.NumberUtil;
import display.sprites.entities.PlayerSpriteSheet;
import main.DungeonExplorer;
import management.player.PlayerInfo;

public class PS_Roll implements PlayerState {

    private int rolldirection;
    private int countdown = 15;

    @Override
    public void update() {
	if (countdown == 13)
	    if (NumberUtil.randomINT(1, 2) == 1)
		DungeonExplorer.sm.playSound(SoundsHolder
			.getSong("MC_Link_Roll1.mp3"));
	    else
		DungeonExplorer.sm.playSound(SoundsHolder
			.getSong("MC_Link_Roll2.mp3"));

	if (countdown == 15) {
	    DungeonExplorer.sm.playSound(SoundsHolder
		    .getSong("MC_Link_Roll.mp3"));
	    rolldirection = PlayerInfo.playerdirection;
	    switch (PlayerInfo.playerdirection) {
	    case PlayerInfo.DOWN:
		PlayerInfo.playersprite
			.setSpriteID(PlayerSpriteSheet.ID_ROLL_DOWN);
		break;
	    case PlayerInfo.UP:
		PlayerInfo.playersprite
			.setSpriteID(PlayerSpriteSheet.ID_ROLL_UP);
		break;
	    case PlayerInfo.LEFT:
		PlayerInfo.playersprite
			.setSpriteID(PlayerSpriteSheet.ID_ROLL_LEFT);
		break;
	    case PlayerInfo.RIGHT:
		PlayerInfo.playersprite
			.setSpriteID(PlayerSpriteSheet.ID_ROLL_LEFT);
		break;
	    }
	}
	--countdown;
	double speed = 0.19d;
	if (rolldirection == PlayerInfo.LEFT
		&& PS_Walk.canWalkTo(PlayerInfo.posX - speed, PlayerInfo.posY)) {
	    PlayerInfo.posX -= speed;
	} else if (rolldirection == PlayerInfo.RIGHT
		&& PS_Walk.canWalkTo(PlayerInfo.posX + speed, PlayerInfo.posY)) {
	    PlayerInfo.posX += speed;
	} else if (rolldirection == PlayerInfo.UP
		&& PS_Walk.canWalkTo(PlayerInfo.posX, PlayerInfo.posY - speed)) {
	    PlayerInfo.posY -= speed;
	} else if (rolldirection == PlayerInfo.DOWN
		&& PS_Walk.canWalkTo(PlayerInfo.posX, PlayerInfo.posY + speed)) {
	    PlayerInfo.posY += speed;
	}

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
	return rolldirection == PlayerInfo.RIGHT;
    }

    @Override
    public BufferedImage getRightPText() {
	return null;
    }

}
