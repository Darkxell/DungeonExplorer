package main;

import audio.SoundManager;
import display.GameFrame;
import management.Updater;

/** Static class that holds the main methods. */
public abstract class DungeonExplorer {

	public static SoundManager sm;

	public static GameFrame frame;

	public static Updater updater;
	public static Thread updaterthread;

	/** Launching method. */
	public static void main(String[] args) {
		sm = new SoundManager();
		frame = new GameFrame();
		updater = new Updater();
		updaterthread = new Thread(updater);
		updaterthread.start();
	}
	// A => D
	// B => S
	// Left => A
	// Right=> Z
	// Dirs => Keys
	// Start=> ENTER
	// Select=>BACKSPACE
}
