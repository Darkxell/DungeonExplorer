package management.entities.monsters.darknut;

import java.awt.Color;
import java.awt.Graphics2D;

import display.sprites.entities.DarknutSpriteSheet;
import management.Position;
import management.entities.Hitbox;
import management.entities.Monster;
import management.floors.Room;
import management.player.PlayerInfo;

public class Darknut extends Monster {

	public Darknut(Room roompointer, double x, double y) {
		super(roompointer, x, y);
		super.state = new Darknut_Spawn(this);
		super.entityDesign = new DarknutSpriteSheet();
		super.damage = 0.5d;
	}

	@Override
	public void updateM() {
		super.state.update();
	}

	@Override
	public void print(Graphics2D g2d) {
		super.state.print(g2d);
		if (PlayerInfo.DEBUGMODE) {
			g2d.setColor(Color.RED);
			g2d.fillRect((int) (16 * (posX + roompointer.posX)) - 1, (int) (16 * (posY + roompointer.posY)) - 1, 2, 2);
		}
	}

	@Override
	public void onhit() {
		System.out.println("Haha, you hit a darknut. Fool.");
	}

	@Override
	public Hitbox getHitbox(double posX, double posY) {
		Position[] points = new Position[9];
		double halfsize = 0.65d;
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
