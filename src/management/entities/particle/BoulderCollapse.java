package management.entities.particle;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import res.images.Res_Particles;
import management.entities.ParticleEffect;
import management.floors.Room;

public class BoulderCollapse extends ParticleEffect {

    public BoulderCollapse(Room roompointer, double x, double y) {
	super(2, roompointer, x, y);
    }

    @Override
    public BufferedImage[] getSpriteArray() {
	return Res_Particles.bouldercollapse;
    }

    @Override
    public void print(Graphics2D g2d) {
	int id = lifetime / counter;
	g2d.drawImage(getSpriteArray()[id],
		(int) ((super.roompointer.posX + super.posX) * 16) - 5,
		(int) ((super.roompointer.posY + super.posY) * 16) - 12, null);
    }

}
