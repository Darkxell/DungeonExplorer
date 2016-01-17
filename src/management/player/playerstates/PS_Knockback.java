package management.player.playerstates;

import java.awt.image.BufferedImage;

import res.audio.SoundsHolder;
import main.DungeonExplorer;
import management.Position;
import management.player.PlayerInfo;
import util.MathVector;

public class PS_Knockback implements PlayerState {

    private int time;
    private MathVector direction;

    /**
     * A knockback state for the player. This takes a direction and a length in
     * frames (1/50s).
     */
    public PS_Knockback(MathVector direction, int length) {
	time = length;
	this.direction = direction;
	DungeonExplorer.sm.playSound(SoundsHolder.getSong("MC_Link_Hurt.mp3"));
    }

    @Override
    public void update() {
	--time;
	Position p = direction.getFixedTranslation(PlayerInfo.posX,
		PlayerInfo.posY, 0.13);
	if (PS_Walk.canWalkTo(p.x, PlayerInfo.posY)) 
	    PlayerInfo.posX = p.x;
	if (PS_Walk.canWalkTo(PlayerInfo.posX, p.y)) 
	    PlayerInfo.posY = p.y;
	if (time <= 0)
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
	return false;
    }

    @Override
    public BufferedImage getRightPText() {
	return null;
    }

}
