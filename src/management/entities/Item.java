package management.entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import util.DoubleRectangle;
import management.Position;
import management.floors.Room;
import management.player.PlayerInfo;

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

    public Item(Room roompointer, double x, double y, double height) {
	super(roompointer, x, y);
	this.height = height;
    }

    @Override
    public void update() {
	height += motion;
	motion -= height / 10;
	motion /= 1.05;
	// Item bouncyness. The closest to 1 this value is, the bouncyer the
	// item is.
	if (Math.abs(motion) < 1 && Math.abs(height) < 1) {
	    motion = 0;
	    height = 0;
	}
	DoubleRectangle hitbox = getHitbox(super.posX, super.posY)
		.getRectHitbox();
	boolean collide = false;
	Position[] poses = PlayerInfo.getPlayerHitbox(PlayerInfo.posX,
		PlayerInfo.posY);
	for (int i = 0; i < poses.length; i++)
	    if (hitbox.isInside(poses[i]))
		collide = true;
	if (collide)
	    this.pickup();
    }

    /** Returns the sprite of the item. */
    public abstract BufferedImage getSprite();

    @Override
    public void print(Graphics2D g2d) {
	g2d.drawImage(getSprite(),
		(int) ((super.roompointer.posX + super.posX) * 16 - 8),
		(int) ((super.roompointer.posY + super.posY) * 16 - 8 - Math
			.abs(height)), null);
    }

    /**
     * Picks this item up. This method should do the pickup action (give you
     * ruppes, hearts or keystones, ext...) and kill this entity, maybe with a
     * particle animation.
     */
    public abstract void pickup();

    @Override
    public Hitbox getHitbox(double posX, double posY) {
	Position[] points = new Position[9];
	double halfsize = 0.26d;
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
    public void onhit() {
	// Do nothing
    }

}
