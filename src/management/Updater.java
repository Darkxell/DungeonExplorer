package management;

import main.DungeonExplorer;

public class Updater implements Runnable {

    /** Reads the updates methods and refreshes the canvas 50 times per second. */
    public void run() {
	long milistart = System.currentTimeMillis();
	int frame = 0;
	for (;;) {
	    while (milistart + (frame * 20) > System.currentTimeMillis()) {
		try {
		    Thread.sleep(2);
		} catch (InterruptedException e) {
		}
	    }
	    ++frame;
	    
	    try {
		DungeonExplorer.frame.getCanvas().state.update();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	    try {
		DungeonExplorer.frame.getCanvas().update(null);
	    } catch (Exception e) {
		e.printStackTrace();
	    }

	}
    }

}
