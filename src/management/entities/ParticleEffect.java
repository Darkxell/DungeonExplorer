package management.entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import management.floors.Room;

public abstract class ParticleEffect extends Entity {

    public ParticleEffect(Room roompointer, double x, double y) {
	super(roompointer, x, y);
    }

    public ParticleEffect(int framespersprite, Room roompointer, double x,
	    double y) {
	super(roompointer, x, y);
	this.counter = framespersprite;
    }

    /** the ammount of frames per sprite of the particle annimation. */
    protected int counter;

    /**
     * the ammount of time the particle exists. This value is increased by one
     * each update.
     */
    protected int lifetime;

    @Override
    public void update() {
	++lifetime;
	if (lifetime >= getSpriteArray().length * counter)
	    super.kill();

    }

    @Override
    public void print(Graphics2D g2d) {
	int id = lifetime/counter;
	g2d.drawImage(
		getSpriteArray()[id],
		(int) ((super.roompointer.posX + super.posX) * 16 - (getSpriteArray()[id]
			.getWidth() / 2)),
		(int) ((super.roompointer.posY + super.posY) * 16 - (getSpriteArray()[id]
			.getHeight() / 2)), null);
    }

    /** Returns an array containing all of the sprites */
    public abstract BufferedImage[] getSpriteArray();

    @Override
    public Hitbox getHitbox(double posX, double posY) {
	return null;
    }

    @Override
    public void onhit() {
    }

}
