package management.entities;

import java.awt.Graphics2D;

import management.Position;
import management.floors.Room;

public abstract class Item extends Entity {

    /**
     * The Z height of the item. This simulates 3D gestion of the item fall.
     * Graphically, 1 is one square on the Y axis.
     */
    private double height;
    /**
     * The speed of fall. This follows a logarithmic increase when the item
     * falls, an exponnential decrease when the item goes back up and is set to
     * 0 when the item is close to the ground with about no motion.
     * */
    private double motion;

    public Item(Room roompointer, double x, double y) {
	super(roompointer, x, y);
    }

    @Override
    public void update() {
	// TODO Auto-generated method stub

    }

    @Override
    public abstract void print(Graphics2D g2d);

    /**
     * Picks this item up. This method should do the pickup action (give you
     * ruppes, hearts or keystones, ext...) and kill this entity, maybe with a
     * particle animation.
     */
    public abstract void pickup();

    @Override
    public Hitbox getHitbox(double posX, double posY) {
	return new Hitbox(new Position[] { new Position(-0.2, -0.2),
		new Position(0, -0.2), new Position(0.2, -0.2),
		new Position(-0.2, 0), new Position(0, 0),
		new Position(0.2, 0), new Position(-0.2, 0.2),
		new Position(0, 0.2), new Position(0.2, 0.2) });
    }

    @Override
    public void onhit() {
	// Do nothing
    }

}
