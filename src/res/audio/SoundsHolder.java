package res.audio;

import java.io.File;

import audio.Song;

public abstract class SoundsHolder {

	public static Song[] sounds = getSounds();
	private static String[] names;

	private static Song[] getSounds() {
		try {
			File[] all = new File("res\\audio\\sounds").listFiles();
			names = new String[all.length];
			for (int i = 0; i < all.length; i++) 
				names[i] = all[i].getName();
			Song[] ts = new Song[all.length];
			for (int i = 0; i < all.length; i++)
				ts[i] = new Song(all[i].getAbsolutePath());
			return ts;
		} catch (Exception e) {
			e.printStackTrace();
			return new Song[0];
		}

	}

	public static Song getSong(String name) {
		for (int i = 0; i < names.length; i++)
			if (names[i].equals(name))
				return sounds[i];
		return null;
	}
}
