package management.entities;

import java.awt.Graphics2D;

import util.DoubleRectangle;
import util.MathVector;
import management.Position;
import management.floors.Room;
import management.player.PlayerInfo;
import management.player.playerstates.PS_Knockback;

/** A class that represents a certain entity made to be a monster. */
public abstract class Monster extends Entity {

    public Monster(Room roompointer, double x, double y) {
	super(roompointer, x, y);
    }

    @Override
    public final void update(){
	updateM();
	knockback();
    }

    /** updates the monster. */
    public abstract void updateM();
    
    @Override
    public abstract void print(Graphics2D g2d);

    @Override
    public abstract void onhit();

    /**
     * knockback the player from this monster if it collides with the player
     * hitbox.
     */
    public void knockback() {
	DoubleRectangle hitbox = getHitbox(super.posX, super.posY)
		.getRectHitbox();
	boolean collide = false;
	Position[] poses = PlayerInfo.getPlayerHitbox(PlayerInfo.posX,
		PlayerInfo.posY);
	for (int i = 0; i < poses.length; i++)
	    if (hitbox.isInside(poses[i]))
		collide = true;
	if (collide && !(PlayerInfo.currentstate instanceof PS_Knockback)){
	    PlayerInfo.currentstate = new PS_Knockback(new MathVector(PlayerInfo.posX-super.posX,PlayerInfo.posY-super.posY), 9);
	    PlayerInfo.health -= 0.25;
	}
	    
    }

}
