package management.entities.monsters.boss1;

import java.util.concurrent.ThreadLocalRandom;

import util.MathVector;

public class ZolInfo {

	public final boolean spawnbig;
	public final MathVector direction;
	public final float chaseratio;
	public final float upwardsmomentum;
	public final float speed;

	public ZolInfo() {
		this(false, MathVector.newRandom(), 0.2f, 1.6f, 0.08f);
	}

	public ZolInfo(boolean spawnbig, MathVector direction, float chaseratio, float upwardsmomentum, float speed) {
		this.spawnbig = spawnbig;
		this.direction = direction;
		this.chaseratio = chaseratio;
		this.upwardsmomentum = upwardsmomentum;
		this.speed = speed;
	}

	/**
	 * Returns a mix of this ZolInfo.
	 * 
	 * @param mutationchance chance to apply a random value to each parameter. Float
	 *                       between 0 and 1.
	 */
	public ZolInfo breedWith(ZolInfo with, float mutationchance) {
		// spawnbig chance : Biased dominant gene (biased by only needing one big to spread)
		boolean new_spawnbig = ThreadLocalRandom.current().nextFloat() < mutationchance
				? ThreadLocalRandom.current().nextBoolean()
				: with.spawnbig || this.spawnbig;
		// direction chance : Random heritage gene
		MathVector new_direction = ThreadLocalRandom.current().nextFloat() < mutationchance ? MathVector.newRandom()
				: ThreadLocalRandom.current().nextFloat() < 0.5f ? with.direction : this.direction;
		// chasing ratio : Averaging heritage gene
		float new_chaseratio = ThreadLocalRandom.current().nextFloat() < mutationchance
				? ThreadLocalRandom.current().nextFloat() * 0.7f
				: (with.chaseratio + this.chaseratio) / 2;
		// upwards momentum : Averaging heritage gene
		float new_upwardsmomentum = ThreadLocalRandom.current().nextFloat() < mutationchance
				? ThreadLocalRandom.current().nextFloat() * 4
				: (with.upwardsmomentum + this.upwardsmomentum) / 2;
		// speed : Averaging iterating gene
		float new_speed = (with.speed + this.speed) / 2;
		if (ThreadLocalRandom.current().nextFloat() < mutationchance)
			new_speed += ThreadLocalRandom.current().nextFloat() * 0.02f - 0.01f;
		if(new_speed < 0.04)
			new_speed = 0.04f;
		// Returns the newly bred information
		return new ZolInfo(new_spawnbig, new_direction, new_chaseratio, new_upwardsmomentum, new_speed);
	}
}
