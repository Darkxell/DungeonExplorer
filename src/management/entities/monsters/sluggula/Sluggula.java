package management.entities.monsters.sluggula;

import java.awt.Graphics2D;

import res.audio.SoundsHolder;
import display.sprites.entities.SluggulaSpriteSheet;
import main.DungeonExplorer;
import management.Position;
import management.entities.Entity;
import management.entities.Hitbox;
import management.entities.Monster;
import management.entities.particle.MobDeath;
import management.entities.particle.MobHit;
import management.floors.CurrentFloorHolder;
import management.floors.Room;
import management.floors.Tile;

public class Sluggula extends Monster {

    private int updater;
    private int direction = Entity.DOWN;

    public Sluggula(Room roompointer, double x, double y) {
	super(roompointer, x, y);
	super.entityDesign = new SluggulaSpriteSheet();
    }

    @Override
    public void updateM() {
	++updater;
	if (updater > 16) {
	    updater = 0;
	    super.entityDesign.next();
	}
	switch (direction) {
	case Entity.DOWN:
	    super.entityDesign.setSpriteID(SluggulaSpriteSheet.ID_WALK_DOWN);
	    break;
	case Entity.UP:
	    super.entityDesign.setSpriteID(SluggulaSpriteSheet.ID_WALK_UP);
	    break;
	case Entity.LEFT:
	    super.entityDesign.setSpriteID(SluggulaSpriteSheet.ID_WALK_LEFT);
	    break;
	case Entity.RIGHT:
	    super.entityDesign.setSpriteID(SluggulaSpriteSheet.ID_WALK_RIGHT);
	    break;
	}
	walk();
	if (Math.random() < 0.01)
	    direction = (int) (Math.random() * 4);
    }

    @Override
    public void print(Graphics2D g2d) {
	g2d.drawImage(super.entityDesign.getCurrentSprite(),
		(int) ((super.roompointer.posX + super.posX) * 16 - 16),
		(int) ((super.roompointer.posY + super.posY) * 16 - 16), null);
    }

    @Override
    public void onhit() {
	kill();
	roompointer.addEntity(new MobDeath(roompointer, posX, posY));
	roompointer.addEntity(new MobHit(roompointer, posX, posY - 0.2));
	DungeonExplorer.sm.playSound(SoundsHolder.getSong("MC_Enemy_Kill.mp3"));
    }

    /**
     * Moves the sluggula according to its direction. this just moves the
     * sluggula a bit forward if there is no wall.
     */
    private void walk() {
	double speed = 0.005d;
	switch (direction) {
	case RIGHT:
	    if (canWalkTo(super.posX + speed, super.posY))
		super.posX += speed;
	    break;
	case LEFT:
	    if (canWalkTo(super.posX - speed, super.posY))
		super.posX -= speed;
	    break;
	case UP:
	    if (canWalkTo(super.posX, super.posY - speed))
		super.posY -= speed;
	    break;
	case DOWN:
	    if (canWalkTo(super.posX, super.posY + speed))
		super.posY += speed;
	    break;
	}
    }

    @Override
    public Hitbox getHitbox(double posX, double posY) {
	Position[] points = new Position[9];
	double halfsize = 0.3d;
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

    private boolean canWalkTo(double toX, double toY) {
	Position[] hitbox = getHitbox(toX, toY).cardinals;
	for (int i = 0; i < hitbox.length; i++) {
	    if (CurrentFloorHolder.CurrentFloor.getTileTypeAt(
		    (int) (hitbox[i].x + roompointer.posX),
		    (int) (hitbox[i].y + roompointer.posY)) != Tile.TYPE_NORMAL) {
		return false;
	    }
	}
	return true;
    }

}
