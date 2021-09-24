package management.entities.monsters.floormaster;

import java.awt.Graphics2D;

import display.sprites.entities.FloormasterSpriteSheet;
import main.DungeonExplorer;
import management.Position;
import management.entities.Hitbox;
import management.entities.Monster;
import management.entities.items.Heart;
import management.entities.items.Rupee;
import management.entities.particle.MobDeath;
import management.entities.particle.MobHit;
import management.floors.Room;
import res.audio.SoundsHolder;
import util.NumberUtil;

public class Floormaster extends Monster {

	public Floormaster(Room roompointer, double x, double y) {
		super(roompointer, x, y);
		super.state = new Floormaster_idle(this);
		super.entityDesign = new FloormasterSpriteSheet();
		super.damage = 0.5d;
	}

	@Override
	public void updateM() {
		super.state.update();
	}

	@Override
	public void print(Graphics2D g2d) {
		super.state.print(g2d);
	}

	@Override
	public void onhit() {
		kill();
		int r = NumberUtil.randomINT(1, 6);
		if (r == 1)
		    roompointer.addEntity(new Heart(roompointer, posX, posY,20));
		if (r == 3)
		    roompointer.addEntity(new Rupee(roompointer, posX, posY,20));
		roompointer.addEntity(new MobDeath(roompointer, posX, posY));
		roompointer.addEntity(new MobHit(roompointer, posX, posY - 0.2));
		 DungeonExplorer.sm.playSound(SoundsHolder.getSong("MC_Enemy_Kill.mp3"));
	}

	@Override
	public Hitbox getHitbox(double posX, double posY) {
		Position[] points = new Position[9];
		double halfsize = 0.48d;
		points[0] = new Position(posX - halfsize, posY - halfsize);
		points[1] = new Position(posX, posY - halfsize);
		points[2] = new Position(posX + halfsize, posY - halfsize);
		points[3] = new Position(posX - halfsize, posY);
		points[4] = new Position(posX, posY);
		points[5] = new Position(posX + halfsize, posY);
		points[6] = new Position(posX - halfsize, posY + halfsize);
		points[7] = new Position(posX, posY + halfsize);
		points[8] = new Position(posX + halfsize, posY + halfsize);
		return new Hitbox(points);
	}

}
