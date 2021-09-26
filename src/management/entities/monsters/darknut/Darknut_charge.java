package management.entities.monsters.darknut;

import java.awt.Graphics2D;

import display.sprites.entities.DarknutSpriteSheet;
import management.entities.Entity;
import management.entities.EntityState;
import util.MathUtils;

public class Darknut_charge extends EntityState {

	private int updater = 0;
	private int chargelength = 0;

	private static final int WINDUP = 16;
	private static final int MAXDURATION = 150;

	public Darknut_charge(Entity parent) {
		super(parent);
	}

	@Override
	public void print(Graphics2D g2d) {
		g2d.drawImage(this.parententity.entityDesign.getCurrentSprite(),
				(int) ((this.parententity.roompointer.posX + this.parententity.posX) * 16 - 28),
				(int) ((this.parententity.roompointer.posY + this.parententity.posY) * 16 - 43), null);
	}

	@Override
	public void update() {
		Darknut p = (Darknut) parententity;
		if (chargelength == 0) {
			p.facing = (p.posX > p.dmap.path.get(p.following).x) ? Entity.LEFT : Entity.RIGHT;
			p.entityDesign.setSpriteID(
					p.facing == Entity.LEFT ? DarknutSpriteSheet.ID_WINDUP_LEFT : DarknutSpriteSheet.ID_WINDUP_RIGHT);
		}

		++chargelength;
		++updater;
		if (p.dmap.path != null && chargelength > WINDUP) {
			p.moveto(p.dmap.path.get(p.following).x + 0.5, p.dmap.path.get(p.following).y + 0.5, 0.15d);
		}

		if (updater > (5)) {
			updater = 0;
			p.entityDesign.next();
		}

		double distppp = MathUtils.dist2(p.dmap.path.get(p.following).x + 0.5, p.dmap.path.get(p.following).y + 0.5,
				this.parententity.posX, this.parententity.posY);
		if (chargelength >= MAXDURATION || distppp < 0.35)
			p.nextState();

	}

}
