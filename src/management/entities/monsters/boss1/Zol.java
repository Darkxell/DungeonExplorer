package management.entities.monsters.boss1;

import java.awt.Color;
import java.awt.Graphics2D;

import main.DungeonExplorer;
import management.Position;
import management.entities.Hitbox;
import management.entities.Monster;
import management.entities.particle.MobDeath;
import management.entities.particle.MobHit;
import management.floors.Room;
import management.player.PlayerInfo;
import res.audio.SoundsHolder;
import res.images.Res_Particles;
import res.images.mobs.Res_Chuchu;
import util.MathVector;

public class Zol extends Monster implements Comparable<Zol> {

	public ZolInfo spawninfo;

	public boolean big = false;
	public boolean killed = false;
	public int score = 0;

	public float height = 0f, heightmomentum = 0f;

	public Zol(Room roompointer, double x, double y, ZolInfo spawn) {
		super(roompointer, x, y);
		spawninfo = spawn;
		this.big = spawninfo.spawnbig;
		this.heightmomentum = spawninfo.upwardsmomentum;
	}

	@Override
	public void updateM() {
		// Variables init
		double playerX = PlayerInfo.posX - roompointer.posX, playerY = PlayerInfo.posY - roompointer.posY;
		// Animation iteration
		frameanim++;
		if (frameanim >= 30)
			frameanim = 0;
		// AI iteration
		this.posX += spawninfo.direction.x * spawninfo.speed * (1f - spawninfo.chaseratio);
		this.posY += spawninfo.direction.y * spawninfo.speed * (1f - spawninfo.chaseratio);
		MathVector towardsplayer = new MathVector(playerX - this.posX , playerY - this.posY).normalize();
		this.posX += towardsplayer.x * spawninfo.speed * spawninfo.chaseratio;
		this.posY += towardsplayer.y * spawninfo.speed * spawninfo.chaseratio;
		this.height += heightmomentum;
		heightmomentum -= 0.1f;
		// Score iteration
		float distwithplayer = (float) new MathVector(this.posX - playerX, this.posY - playerY).norm();
		score += 8 - (int) distwithplayer;
	}

	private int frameanim = 0;

	@Override
	public void print(Graphics2D g2d) {
		int heightoffset = height > 0 ? (int) height : 0;
		g2d.drawImage(Res_Particles.shadow, (int) ((super.roompointer.posX + super.posX) * 16) - 4,
				(int) ((super.roompointer.posY + super.posY) * 16) - 2, null);
		g2d.drawImage(big ? Res_Chuchu.ZOL_BIG[frameanim > 20 ? 0 : 1] : Res_Chuchu.ZOL_SMALL[frameanim > 20 ? 0 : 1],
				(int) ((super.roompointer.posX + super.posX) * 16 - 8),
				(int) ((super.roompointer.posY + super.posY) * 16 - 8) - heightoffset, null);
		g2d.setColor(Color.CYAN);
		if (PlayerInfo.DEBUGMODE) {
			g2d.drawString("" + (score / 10), (int) ((super.roompointer.posX + super.posX) * 16 - 8),
					(int) ((super.roompointer.posY + super.posY) * 16 + 13));
		}
	}

	@Override
	public void onhit() {
		roompointer.addEntity(new MobHit(roompointer, posX, posY - 0.2));
		if (big) {
			big = false;
		} else {
			roompointer.addEntity(new MobDeath(roompointer, posX, posY));
			DungeonExplorer.sm.playSound(SoundsHolder.getSong("MC_Enemy_Kill.mp3"));
			killed = true;
			kill();
		}

	}

	@Override
	public Hitbox getHitbox(double posX, double posY) {
		Position[] points = new Position[9];
		final double halfsize = 0.22d;
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

	@Override
	public int compareTo(Zol o) {
		return score - o.score;
	}

}
