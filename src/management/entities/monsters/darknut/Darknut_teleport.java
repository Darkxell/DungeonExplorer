package management.entities.monsters.darknut;

import java.awt.Graphics2D;

import main.DungeonExplorer;
import management.entities.Entity;
import management.entities.EntityState;
import management.player.PlayerInfo;
import res.audio.SoundsHolder;
import res.images.mobs.Res_Darknut;

public class Darknut_teleport extends EntityState {

	private int tplength = 0;
	private static final int TPDURATION = 16;

	public Darknut_teleport(Entity parent) {
		super(parent);
	}

	@Override
	public void print(Graphics2D g2d) {
		if(tplength < 3 || tplength > TPDURATION -3) {
			g2d.drawImage(Res_Darknut.ghost,
					(int) ((this.parententity.roompointer.posX + this.parententity.posX) * 16 - 28),
					(int) ((this.parententity.roompointer.posY + this.parententity.posY) * 16 - 43), null);
		}else {
			g2d.drawImage(Res_Darknut.ghosttp,
					(int) ((this.parententity.roompointer.posX + this.parententity.posX) * 16 - 28),
					(int) ((this.parententity.roompointer.posY + this.parententity.posY) * 16 - 43), null);
		}
	}

	@Override
	public void update() {
		Darknut p = (Darknut) parententity;
		if(tplength == 1) {
			DungeonExplorer.sm.playSound(SoundsHolder.getSong("MC_Whirlwind.mp3"));
		}
		if(tplength == TPDURATION/2) {
			p.posX = p.circleX;
			p.posY = p.circleY;
			p.circleX = 3.5 + Math.random() * 10;
			p.circleY = 3.5 + Math.random() * 8;
			try {
				p.dmap.compute(p.posX, p.posY, PlayerInfo.posX - p.roompointer.posX,
						PlayerInfo.posY - p.roompointer.posY);
			} catch (Exception e) {
				System.err.println("Couldn't compute Dijkstra map For a Darknut!");
				e.printStackTrace();
			}
			p.following = p.dmap.path.size() - 1;
		}
		++tplength;
		if (tplength >= TPDURATION)
			p.nextState();
	}

}
