package management.entities.monsters.octorok;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import res.audio.SoundsHolder;
import res.images.mobs.Res_Oktorok;
import main.DungeonExplorer;
import management.entities.Entity;
import management.entities.EntityState;
import management.entities.monsters.boulder.Boulder;

public class Oktorok_shoot extends EntityState {

    public Oktorok_shoot(Entity parent) {
	super(parent);
    }

    private int countdown = 10;

    @Override
    public void print(Graphics2D g2d) {
	BufferedImage sprite = Res_Oktorok.shoot_down;
	switch (super.parententity.facing) {
	case Entity.UP:
	    sprite = Res_Oktorok.shoot_up;
	    break;
	case Entity.LEFT:
	    sprite = Res_Oktorok.shoot_left;
	    break;
	case Entity.RIGHT:
	    sprite = Res_Oktorok.shoot_right;
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
	if(countdown==10)
		 DungeonExplorer.sm.playSound(SoundsHolder.getSong("MC_Octorok_Spit.mp3"));
	--countdown;
	if (countdown <= 0) {
	    super.parententity.state = new Oktorok_still(super.parententity);
	    parententity.roompointer.addEntity(new Boulder(
		    parententity.roompointer, parententity.posX,
		    parententity.posY, parententity.facing));
	}
    }

}
