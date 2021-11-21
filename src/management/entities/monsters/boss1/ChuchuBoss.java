package management.entities.monsters.boss1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

import main.DungeonExplorer;
import management.Position;
import management.entities.Hitbox;
import management.entities.Monster;
import management.floors.Room;
import management.player.PlayerInfo;
import res.audio.SoundsHolder;
import res.images.ImagesHolder;
import res.images.Res_Particles;
import res.images.mobs.Res_Chuchu;
import util.MathVector;

public class ChuchuBoss extends Monster {

	private ArrayList<Zol> childrens = new ArrayList<>(20);

	// Genetic algorithm attributes for zol spawning
	private int generationsize = 16, bestthreshold = 4;
	private float mutationchance = 0.2f;

	public float height = 0f, heightmomentum = 0f, speed = 0.03f;

	public ChuchuBoss(Room roompointer, double x, double y) {
		super(roompointer, x, y);
		super.hp = 100d;
		for (int i = 0; i < generationsize; i++) {
			Zol toadd = new Zol(roompointer, posX, posY, new ZolInfo());
			childrens.add(toadd);
			roompointer.addEntity(toadd);
		}
	}

	private int nextphasecountdown = 0;
	private boolean grounded = true;

	@Override
	public void updateM() {
		nextphasecountdown++;
		if (nextphasecountdown >= 130) {
			nextphasecountdown = 0;
			DungeonExplorer.sm.playSound(SoundsHolder.getSong("MC_Enemy_Jump.mp3"));
			iterateGeneration();
			heightmomentum = 3.5f;
			grounded = false;
		}
		this.height += heightmomentum;
		heightmomentum -= 0.1f;
		if (height > 0) {
			double playerX = PlayerInfo.posX - roompointer.posX, playerY = PlayerInfo.posY - roompointer.posY;
			MathVector towardsplayer = new MathVector(playerX - this.posX, playerY - this.posY).normalize();
			this.posX += towardsplayer.x * speed;
			this.posY += towardsplayer.y * speed;
		} else {
			height = 0f;
			if (!grounded) {
				grounded = true;
				DungeonExplorer.sm.playSound(SoundsHolder.getSong("MC_GustJar_Blast3.mp3"));
			}
		}
	}

	/**
	 * Kills off the current zol generation, and spawns a new batch by breeding the
	 * fittest of the previous iteration.
	 */
	private void iterateGeneration() {
		// Computes the new generation to spawn
		Collections.sort(childrens);
		ArrayList<Zol> survivors = new ArrayList<>(bestthreshold + 1);
		// Force culls the old generation
		for (int i = 0; i < childrens.size(); i++) {
			if (i < bestthreshold)
				survivors.add(childrens.get(i));
			Zol currentzol = childrens.get(i);
			if (!currentzol.killed)
				currentzol.kill();
		}
		childrens.clear();
		if (PlayerInfo.DEBUGMODE)
			for (int i = 0; i < survivors.size(); i++)
				System.out.print(survivors.get(i).score + " * ");
		System.out.println();
		// Spawns the new generation
		for (int i = 0; i < generationsize; i++) {
			Zol parent1 = survivors.get(ThreadLocalRandom.current().nextInt(survivors.size())),
					parent2 = survivors.get(ThreadLocalRandom.current().nextInt(survivors.size()));
			Zol toadd = new Zol(roompointer, posX, posY,
					parent1.spawninfo.breedWith(parent2.spawninfo, mutationchance));
			childrens.add(toadd);
			roompointer.addEntity(toadd);
		}
	}

	@Override
	public void print(Graphics2D g2d) {
		int heightoffset = height > 0 ? (int) height : 0;
		g2d.drawImage(Res_Particles.shadow, (int) ((super.roompointer.posX + super.posX) * 16) - heightoffset / 2,
				(int) ((super.roompointer.posY + super.posY) * 16) - 7, heightoffset, 14, null);
		g2d.drawImage(Res_Chuchu.CHUCHU_FEET, (int) ((super.roompointer.posX + super.posX) * 16 - 20),
				(int) ((super.roompointer.posY + super.posY) * 16 - 11) - heightoffset, null);
		g2d.drawImage(Res_Chuchu.CHUCHU_IDLE, (int) ((super.roompointer.posX + super.posX) * 16 - 23),
				(int) ((super.roompointer.posY + super.posY) * 16 - 47) - heightoffset, null);
	}

	@Override
	public void onhit() {
	}

	@Override
	public Hitbox getHitbox(double posX, double posY) {
		Position[] points = new Position[9];
		final double halfsizeVert = 0.55d, halfsizeHori = 0.95d;
		points[0] = new Position(posX - halfsizeHori, posY - halfsizeVert);
		points[1] = new Position(posX, posY - halfsizeVert);
		points[2] = new Position(posX + halfsizeHori, posY - halfsizeVert);
		points[3] = new Position(posX - halfsizeHori, posY);
		points[4] = new Position(posX, posY);
		points[5] = new Position(posX + halfsizeHori, posY);
		points[6] = new Position(posX - halfsizeHori, posY + halfsizeVert);
		points[7] = new Position(posX, posY + halfsizeVert);
		points[8] = new Position(posX + halfsizeHori, posY + halfsizeVert);
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
