package management.entities.monsters.boulder;

import java.awt.Graphics2D;

import management.Position;
import management.entities.Entity;
import management.entities.EntityState;
import management.entities.particle.BoulderCollapse;
import management.floors.CurrentFloorHolder;
import management.floors.Tile;

public class Boulder_Roll extends EntityState {

    public Boulder_Roll(Entity parent) {
	super(parent);
    }

    @Override
    public void print(Graphics2D g2d) {
	g2d.drawImage(
		parententity.entityDesign.getCurrentSprite(),
		(int) ((super.parententity.roompointer.posX + super.parententity.posX) * 16 - 5),
		(int) ((super.parententity.roompointer.posY + super.parententity.posY) * 16 - 5),
		null);
    }

    @Override
    public void update() {
	roll();
    }

    /**
     * Moves the boulder according to its direction. This moves the boulder
     * according to its direction and kills it if it hits a wall.
     */
    private void roll() {
	double speed = 0.2d;
	switch (super.parententity.facing) {
	case Entity.RIGHT:
	    if (canRollTo(super.parententity.posX + speed,
		    super.parententity.posY))
		super.parententity.posX += speed;
	    else {
		parententity.kill();
		parententity.roompointer.addEntity(new BoulderCollapse(
			parententity.roompointer, parententity.posX,
			parententity.posY));
	    }
	    break;
	case Entity.LEFT:
	    if (canRollTo(super.parententity.posX - speed,
		    super.parententity.posY))
		super.parententity.posX -= speed;
	    else {
		parententity.kill();
		parententity.roompointer.addEntity(new BoulderCollapse(
			parententity.roompointer, parententity.posX,
			parententity.posY));
	    }
	    break;
	case Entity.UP:
	    if (canRollTo(super.parententity.posX, super.parententity.posY
		    - speed))
		super.parententity.posY -= speed;
	    else {
		parententity.kill();
		parententity.roompointer.addEntity(new BoulderCollapse(
			parententity.roompointer, parententity.posX,
			parententity.posY));
	    }
	    break;
	case Entity.DOWN:
	    if (canRollTo(super.parententity.posX, super.parententity.posY
		    + speed))
		super.parententity.posY += speed;
	    else {
		parententity.kill();
		parententity.roompointer.addEntity(new BoulderCollapse(
			parententity.roompointer, parententity.posX,
			parententity.posY));
	    }
	    break;
	}
    }

    private boolean canRollTo(double toX, double toY) {
	Position[] hitbox = ((Boulder) parententity).getHitbox(toX, toY).cardinals;
	for (int i = 0; i < hitbox.length; i++)
	    if (CurrentFloorHolder.CurrentFloor.getTileTypeAt(
		    (int) (hitbox[i].x + parententity.roompointer.posX), (int) (hitbox[i].y + parententity.roompointer.posY)) != Tile.TYPE_NORMAL)
		return false;
	return true;
    }

}
