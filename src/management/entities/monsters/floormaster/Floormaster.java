package management.entities.monsters.floormaster;

import java.awt.Graphics2D;

import display.sprites.entities.FloormasterSpriteSheet;
import management.Position;
import management.entities.Hitbox;
import management.entities.Monster;
import management.floors.Room;

public class Floormaster extends Monster {

	public Floormaster(Room roompointer, double x, double y) {
		super(roompointer, x, y);
		super.state = new Floormaster_idle(this);
		super.entityDesign = new FloormasterSpriteSheet();
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
