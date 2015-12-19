package management.entities.octorok;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import res.images.mobs.Res_Oktorok;
import management.Position;
import management.entities.Entity;
import management.entities.EntityState;
import management.floors.CurrentFloorHolder;
import management.floors.Tile;

public class Oktorok_walk extends EntityState {

    private int walkanim;
    private boolean walka;

    public Oktorok_walk(Entity parent) {
	super(parent);
    }

    @Override
    public void print(Graphics2D g2d) {
	BufferedImage[] sprite = Res_Oktorok.walk_down;
	switch (super.parententity.facing) {
	case Entity.UP:
	    sprite = Res_Oktorok.walk_up;
	    break;
	case Entity.LEFT:
	    sprite = Res_Oktorok.walk_left;
	    break;
	case Entity.RIGHT:
	    sprite = Res_Oktorok.walk_right;
	    break;
	}
	g2d.drawImage(
		(walka) ? sprite[0] : sprite[1],
		(int) ((super.parententity.roompointer.posX + super.parententity.posX) * 16 - 8),
		(int) ((super.parententity.roompointer.posY + super.parententity.posY) * 16 - 8),
		null);
    }

    @Override
    public void update() {
	if (walkanim <= 0) {
	    walka = !walka;
	    walkanim = 15;
	} else
	    --walkanim;
	walk();
	if (Math.random() < 0.01) {
	    super.parententity.facing = (int) (Math.random() * 4);
	    super.parententity.state = new Oktorok_still(super.parententity);
	}
	if (((Oktorok) parententity).isLookingAtPlayer() && Math.random() < 0.1)
	    super.parententity.state = new Oktorok_shoot(super.parententity);
    }

    /**
     * Moves the oktotok according to its direction. this just moves the oktorok
     * a bit forward if there is no wall.
     */
    private void walk() {
	double speed = 0.01d;
	switch (super.parententity.facing) {
	case Entity.RIGHT:
	    if (canWalkTo(super.parententity.posX + speed,
		    super.parententity.posY))
		super.parententity.posX += speed;
	    break;
	case Entity.LEFT:
	    if (canWalkTo(super.parententity.posX - speed,
		    super.parententity.posY))
		super.parententity.posX -= speed;
	    break;
	case Entity.UP:
	    if (canWalkTo(super.parententity.posX, super.parententity.posY
		    - speed))
		super.parententity.posY -= speed;
	    break;
	case Entity.DOWN:
	    if (canWalkTo(super.parententity.posX, super.parententity.posY
		    + speed))
		super.parententity.posY += speed;
	    break;
	}
    }

    private boolean canWalkTo(double toX, double toY) {
	Position[] hitbox = ((Oktorok) parententity).getHitbox(toX, toY).cardinals;
	for (int i = 0; i < hitbox.length; i++) {
	    if (CurrentFloorHolder.CurrentFloor.getTileTypeAt(
		    (int) (hitbox[i].x), (int) (hitbox[i].y)) != Tile.TYPE_NORMAL) {
		return false;
	    }
	}
	return true;
    }

}
