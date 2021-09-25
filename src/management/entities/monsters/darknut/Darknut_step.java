package management.entities.monsters.darknut;

import java.awt.Graphics2D;

import display.sprites.entities.DarknutSpriteSheet;
import management.entities.Entity;
import management.entities.EntityState;
import util.MathUtils;

public class Darknut_step extends EntityState {

	private int updater = 0;
	private int steplength = 0;

	private static final int DASHFRAMES = 14;

	public Darknut_step(Entity parent) {
		super(parent);
	}

	@Override
	public void print(Graphics2D g2d) {
		g2d.drawImage(this.parententity.entityDesign.getCurrentSprite(),
				(int) ((this.parententity.roompointer.posX + this.parententity.posX) * 16 - 28),
				(int) ((this.parententity.roompointer.posY + this.parententity.posY) * 16 - 28), null);
	}

	@Override
	public void update() {
		switch (steplength) {
		case 0:
			super.parententity.entityDesign.setSpriteID(DarknutSpriteSheet.ID_MOVE_DOWN);
			break;
		case DASHFRAMES:
			super.parententity.entityDesign.setSpriteID(DarknutSpriteSheet.ID_IDLE_DOWN);
			break;
		}
		++updater;
		++steplength;
		if (updater > (7)) {
			updater = 0;
			this.parententity.entityDesign.next();
		}

		Darknut p = (Darknut) parententity;
		if (p.dmap.path != null) {
			if (p.following == -1)
				p.following = p.dmap.path.size() - 1;
			double distppp = MathUtils.dist2(p.dmap.path.get(p.following).x + 0.5, p.dmap.path.get(p.following).y + 0.5,
					this.parententity.posX, this.parententity.posY);
			p.moveto(p.dmap.path.get(p.following).x + 0.5, p.dmap.path.get(p.following).y + 0.5,
					steplength > DASHFRAMES ? 0.024 : 0.065);
			if (/* p.following > 0 && */ distppp < 0.35)
				p.nextState();
		}

	}

}
