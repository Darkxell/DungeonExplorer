package management.player.playerstates;

import java.awt.image.BufferedImage;

import management.player.PlayerInfo;
import display.sprites.entities.PlayerSpriteSheet;

public class PS_Faint implements PlayerState {

    public boolean setter = true;

    @Override
    public void update() {
	if (setter)
	    PlayerInfo.playersprite.setSpriteID(PlayerSpriteSheet.ID_FAINT);

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
	return false;
    }

    @Override
    public BufferedImage getRightPText() {
	return null;
    }

}
