package management.entities.boulder;

import java.awt.Graphics2D;

import res.images.mobs.Res_Oktorok;
import display.sprites.AnimatedSprite;
import display.sprites.SpriteSheet;
import management.Position;
import management.entities.Entity;
import management.floors.Room;

public class Boulder extends Entity {

    public Boulder(Room roompointer, double x, double y, int direction) {
	super(roompointer, x, y);
	super.facing = direction;
	super.state = new Boulder_Roll(this);
	super.entityDesign = new SpriteSheet(
		new AnimatedSprite[] { new AnimatedSprite(Res_Oktorok.boulder) });
    }

    private int supdater = 3;

    @Override
    public void update() {
	super.state.update();
	--supdater;
	if (supdater < 0) {
	    supdater = 3;
	    super.entityDesign.next();
	}
    }

    @Override
    public void print(Graphics2D g2d) {
	super.state.print(g2d);
    }

    @Override
    public void onhit() {
    }

    public Position[] getHitbox(double posX, double posY) {
	Position[] points = new Position[9];
	double halfsize = 0.2d;
	points[0] = new Position(posX - halfsize, posY - halfsize);
	points[1] = new Position(posX, posY - halfsize);
	points[2] = new Position(posX + halfsize, posY - halfsize);
	points[3] = new Position(posX - halfsize, posY);
	points[4] = new Position(posX, posY);
	points[5] = new Position(posX + halfsize, posY);
	points[6] = new Position(posX - halfsize, posY + halfsize);
	points[7] = new Position(posX, posY + halfsize);
	points[8] = new Position(posX + halfsize, posY + halfsize);
	return points;
    }

}
