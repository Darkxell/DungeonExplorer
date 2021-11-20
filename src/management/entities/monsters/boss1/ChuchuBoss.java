package management.entities.monsters.boss1;

import java.awt.Color;
import java.awt.Graphics2D;

import management.Position;
import management.entities.Hitbox;
import management.entities.Monster;
import management.floors.Room;
import res.images.ImagesHolder;
import res.images.mobs.Res_Chuchu;

public class ChuchuBoss extends Monster{

	public ChuchuBoss(Room roompointer, double x, double y) {
		super(roompointer, x, y);
		super.hp = 100d;
	}

	@Override
	public void updateM() {
		
	}

	@Override
	public void print(Graphics2D g2d) {
		g2d.drawImage(Res_Chuchu.CHUCHU_FEET,
				(int) ((super.roompointer.posX + super.posX) * 16 - 8),
				(int) ((super.roompointer.posY + super.posY) * 16 - 8), null);
	}

	@Override
	public void onhit() {
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
	
	private static final Color BB1 = new Color(122, 59, 199);
	private static final Color BB2 = new Color(111, 51, 190);
	private static final Color BB3 = new Color(58, 13, 156);
	
	@Override
	public void printOnUI(Graphics2D g2d) {
		int px = 20, py = 140;
		g2d.drawImage(ImagesHolder.ENTITIES_BOSSBAR, px, py, null);
		g2d.setColor(BB1);
		g2d.fillRect(px + 13, py + 5, (int) super.hp, 3);
		g2d.setColor(BB2);
		g2d.fillRect(px + 13, py + 6, (int) super.hp + 1, 1);
		g2d.setColor(BB3);
		g2d.fillRect(px + 13, py + 7, (int) super.hp + 2, 1);
	}

}
