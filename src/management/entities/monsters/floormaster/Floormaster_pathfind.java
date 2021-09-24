package management.entities.monsters.floormaster;

import java.awt.Color;
import java.awt.Graphics2D;

import management.entities.Entity;
import management.entities.EntityState;
import management.entities.monsters.pathfinding.AstarMap;
import management.player.PlayerInfo;
import util.MathUtils;

public class Floormaster_pathfind extends EntityState {

	private int updater;
	private int maprefresh;
	private AstarMap pmap;
	private int following = -1;

	public Floormaster_pathfind(Entity parent) {
		super(parent);
		pmap = new AstarMap(parent.roompointer, (int) parent.posX, (int) parent.posY,
				(int) PlayerInfo.posX - parent.roompointer.posX, (int) PlayerInfo.posY - parent.roompointer.posY);
	}

	@Override
	public void print(Graphics2D g2d) {
		g2d.drawImage(this.parententity.entityDesign.getCurrentSprite(),
				(int) ((this.parententity.roompointer.posX + this.parententity.posX) * 16 - 16),
				(int) ((this.parententity.roompointer.posY + this.parententity.posY) * 16 - 16), null);
		if (PlayerInfo.DEBUGMODE) {
			pmap.print(g2d);
			if (pmap.path != null && following != -1) {
				g2d.setColor(Color.MAGENTA);
				g2d.fillRect(16 * pmap.path.get(following).x + pmap.x + 6, 16 * pmap.path.get(following).y  + pmap.y + 6, 4, 4);
			}
			g2d.setColor(Color.RED);
			g2d.fillRect((int) (16 * this.parententity.posX) -1 ,(int) (16 * this.parententity.posY) -1 , 2, 2);
			
		}
	}

	@Override
	public void update() {
		if (parententity.distP2() >= 50) {
			parententity.state = new Floormaster_idle(super.parententity);
			return;
		}

		++updater;
		if (updater > 16) {
			updater = 0;
			this.parententity.entityDesign.next();
		}

		++maprefresh;
		if (maprefresh > 150) {
			maprefresh = 0;
			pmap = new AstarMap(super.parententity.roompointer, (int) super.parententity.posX,
					(int) super.parententity.posY, (int) PlayerInfo.posX - super.parententity.roompointer.posX,
					(int) PlayerInfo.posY - super.parententity.roompointer.posY);
			following = -1;
		}
		if (pmap.path != null) {
			if (following == -1)
				following = pmap.path.size() - 1;
			double distppp = MathUtils.dist2(pmap.path.get(following).x, pmap.path.get(following).y,
					this.parententity.posX, this.parententity.posY);
			if (following > 0 && distppp < 0.3) 
				following -= 1;
			moveto(pmap.path.get(following).x, pmap.path.get(following).y);
		}
	}

	private void moveto(double x, double y) {
		double speed = 0.015;
		if (parententity.posX - x > 0.1)
			parententity.posX -= speed;
		if (parententity.posX - x < 0.1)
			parententity.posX += speed;
		if (parententity.posY - y > 0.1)
			parententity.posY -= speed;
		if (parententity.posY - y < 0.1)
			parententity.posY += speed;
	}

}
