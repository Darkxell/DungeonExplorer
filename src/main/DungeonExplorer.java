package main;

import management.Updater;
import display.GameFrame;

/** Static class that holds the main methods. */
public abstract class DungeonExplorer {

    public static GameFrame frame;

    public static Updater updater;
    public static Thread updaterthread;

    /** Launching method. */
    public static void main(String[] args) {
	frame = new GameFrame();
	updater = new Updater();
	updaterthread = new Thread(updater);
	updaterthread.start();
    }
    //  A   => D
    //  B   => S
    // Left => A
    // Right=> Z
    // Dirs => Keys
    // Start=> ENTER
    // Select=>BACKSPACE
}
