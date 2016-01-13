package management.entities.monsters.unknown;

import java.awt.Graphics2D;

import res.images.ImagesHolder;
import management.Position;
import management.entities.Hitbox;
import management.entities.Monster;
import management.floors.Room;

public class Unknown extends Monster {

    public Unknown(Room roompointer, double x, double y) {
	super(roompointer, x, y);
    }

    @Override
    public void updateM() {
    }

    @Override
    public void print(Graphics2D g2d) {
	g2d.drawImage(ImagesHolder.ENTITIES_UNKNOWN,
		(int) ((super.roompointer.posX + super.posX) * 16 - 16),
		(int) ((super.roompointer.posY + super.posY) * 16 - 16), null);
    }

    @Override
    public void onhit() {
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

}
