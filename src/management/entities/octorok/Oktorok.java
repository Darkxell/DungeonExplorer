package management.entities.octorok;

import java.awt.Graphics2D;

import management.Position;
import management.entities.Hitbox;
import management.entities.Monster;
import management.floors.Room;
import management.player.PlayerInfo;

public class Oktorok extends Monster {

    public Oktorok(Room roompointer, double x, double y) {
	super(roompointer, x, y);
	super.state = new Oktorok_still(this);
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
	double halfsize = 0.45d;
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

    /**
     * Predicate that returns true if and only if the oktorok is looking at the
     * player.
     */
    public boolean isLookingAtPlayer() {
	int viewdistance = 5;// in tiles
	int x = (int) super.posX, y = (int) super.posY;
	int px = (int) PlayerInfo.posX, py = (int) PlayerInfo.posY;
	switch (super.facing) {
	case UP:
	    for (int i = 0; i < viewdistance; i++)
		if (x == px && y-i == py)
		    return true;
	    return false;
	case DOWN:
	    for (int i = 0; i < viewdistance; i++)
		if (x == px && y+i == py)
		    return true;
	    return false;
	case RIGHT:
	    for (int i = 0; i < viewdistance; i++)
		if (x+i == px && y == py)
		    return true;
	    return false;
	case LEFT:
	    for (int i = 0; i < viewdistance; i++)
		if (x-i == px && y == py)
		    return true;
	    return false;
	default:
	    return false;
	}
    }

}
