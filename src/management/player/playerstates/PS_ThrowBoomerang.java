package management.player.playerstates;

import java.awt.image.BufferedImage;

import display.sprites.entities.PlayerSpriteSheet;
import management.entities.player.boomerang.MagicBoomerang;
import management.floors.CurrentFloorHolder;
import management.floors.Room;
import management.player.PlayerInfo;

public class PS_ThrowBoomerang implements PlayerState {

	private int duration = 0;
	private int throwdirection;

	@Override
	public void update() {
		if (duration == 0) {
			throwdirection = PlayerInfo.playerdirection;
			switch (PlayerInfo.playerdirection) {
			case PlayerInfo.DOWN:
				PlayerInfo.playersprite.setSpriteID(PlayerSpriteSheet.ID_THROW_DOWN);
				break;
			case PlayerInfo.UP:
				PlayerInfo.playersprite.setSpriteID(PlayerSpriteSheet.ID_THROW_UP);
				break;
			case PlayerInfo.LEFT:
			case PlayerInfo.RIGHT:
				PlayerInfo.playersprite.setSpriteID(PlayerSpriteSheet.ID_THROW_LEFT);
				break;
			}
		}else if (duration == 2) {
			Room r = CurrentFloorHolder.CurrentFloor.getPlayerRoom();
			r.addEntity(new MagicBoomerang(r, PlayerInfo.posX - r.posX, PlayerInfo.posY - r.posY));
		}
		duration++;
		if (duration > 15)
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
		return throwdirection == PlayerInfo.RIGHT;
	}

	@Override
	public BufferedImage getRightPText() {
		return null;
	}

}
