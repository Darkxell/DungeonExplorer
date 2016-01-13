package management.entities.monsters.unknown;

import java.awt.Graphics2D;

import res.images.ImagesHolder;
import management.entities.Entity;
import management.entities.Hitbox;
import management.floors.Room;

public class Meat extends Entity {

    public Meat(Room roompointer, double x, double y) {
	super(roompointer, x, y);
    }

    private int countdown = 50;

    @Override
    public void update() {
	--countdown;
	if (countdown < 0) {
	    countdown = 50;
	    ++facing;
	    if (facing == 4)
		facing = 0;
	}
	switch (facing) {
	case Entity.DOWN:
	    posY+=0.02;
	    break;
	case Entity.UP:
	   posX+=0.02;
	    break;
	case Entity.LEFT:
	posX -= 0.02;
	    break;
	case Entity.RIGHT:
	         posY-=0.02;
	} 
    }

    @Override
    public void print(Graphics2D g2d) {
	g2d.drawImage(ImagesHolder.ENTITIES_MEAT,
		(int) ((super.roompointer.posX + super.posX) * 16 - 8),
		(int) ((super.roompointer.posY + super.posY) * 16 - 8), null);
    }

    @Override
    public Hitbox getHitbox(double posX, double posY) {
	return null;
    }

    @Override
    public void onhit() {
    }

}
