package management.entities.particle;

import java.awt.image.BufferedImage;

import res.images.Res_Particles;
import management.entities.ParticleEffect;
import management.floors.Room;

public class MobDeath extends ParticleEffect {

    public MobDeath(Room roompointer, double x, double y) {
	super(4, roompointer, x, y);
    }

    @Override
    public BufferedImage[] getSpriteArray() {
	return Res_Particles.mobdeath;
    }

}
