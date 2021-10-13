package res.audio;

import java.io.File;

import audio.Song;

public abstract class MusicHolder {
	public static Song[] musics = getMusics();
	private static String[] names = new String[0];

	private static Song[] getMusics() {
		try {
			File[] all = new File("audio\\music").listFiles();
			names = new String[all.length];
			for (int i = 0; i < all.length; i++)
				names[i] = all[i].getName();
			Song[] ts = new Song[all.length];
			for (int i = 0; i < all.length; i++)
				ts[i] = new Song(all[i].getAbsolutePath());
			return ts;
		} catch (Exception e) {
			System.err.println("Couldn't load Music!");
			e.printStackTrace();
			return new Song[0];
		}
		
	}

	public static Song getSong(String name) {
		for (int i = 0; i < names.length; i++)
			if (names[i].equals(name))
				return musics[i];
		return null;
	}
}
