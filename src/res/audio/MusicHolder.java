package res.audio;

import java.io.File;

import res.Res;
import audio.Song;

public abstract class MusicHolder {
    public static Song[] musics = getMusics();
    private static String[] names;

    private static Song[] getMusics() {
	File[] all = new File(Res.FOLDER_PATH + "audio\\music").listFiles();
	names = new String[all.length];
	for (int i = 0; i < all.length; i++)
	    names[i] = all[i].getName();
	Song[] ts = new Song[all.length];
	for (int i = 0; i < all.length; i++)
	    ts[i] = new Song(all[i].getAbsolutePath());
	return ts;
    }

    public static Song getSong(String name) {
	for (int i = 0; i < names.length; i++)
	    if (names[i].equals(name))
		return musics[i];
	return null;
    }
}
