package management.entities.monsters.octorok;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import res.images.mobs.Res_Oktorok;
import management.entities.Entity;
import management.entities.EntityState;

public class Oktorok_still extends EntityState {

    public Oktorok_still(Entity parent) {
	super(parent);
    }

    @Override
    public void print(Graphics2D g2d) {
	BufferedImage sprite = Res_Oktorok.still_down;
	switch (super.parententity.facing) {
	case Entity.UP:
	    sprite = Res_Oktorok.still_up;
	    break;
	case Entity.LEFT:
	    sprite = Res_Oktorok.still_left;
	    break;
	case Entity.RIGHT:
	    sprite = Res_Oktorok.still_right;
	    break;
	}
	g2d.drawImage(
		sprite,
		(int) ((super.parententity.roompointer.posX + super.parententity.posX) * 16 - 8),
		(int) ((super.parententity.roompointer.posY + super.parententity.posY) * 16 - 8),
		null);
    }

    @Override
    public void update() {
	if (Math.random() < 0.04)
	    super.parententity.state = new Oktorok_walk(super.parententity);
	if (((Oktorok) parententity).isLookingAtPlayer() && Math.random() < 0.1)
	    super.parententity.state = new Oktorok_shoot(super.parententity);
    }

}
