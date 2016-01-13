package management.entities.particle;

import java.awt.image.BufferedImage;

import res.images.Res_Particles;
import management.entities.ParticleEffect;
import management.floors.Room;

public class MobHit extends ParticleEffect {

    public MobHit(Room roompointer, double x, double y) {
	super(4, roompointer, x, y);
    }

    @Override
    public BufferedImage[] getSpriteArray() {
	return Res_Particles.mobhit;
    }

}
