package management.states.fileselect;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import res.images.Res_FileSelect;
import main.DungeonExplorer;
import management.states.CanvasState;
import management.states.CanvasStatesHolder;
import management.states.FileSelectState;

public class FileOpenerSubstate implements CanvasState {

    private FileSelectState parent;

    /** The current active button. */
    private int button = 1;

    public FileOpenerSubstate(FileSelectState parent) {
	this.parent = parent;
    }

    @Override
    public void print(Graphics2D g2d) {
	g2d.drawImage(Res_FileSelect.textOverlay_CHOOSEAFILE, 10, 0, null);
	g2d.drawImage(Res_FileSelect.displayarea1, 120, 40, null);
	g2d.drawImage(Res_FileSelect.filebox_active, 50, 40, null);
	g2d.drawImage(parent.getCristalSprite(), 37, 45, null);
	g2d.drawImage(parent.getPlayerSprite(), 130, 60, null);
	if (button == 1) {
	    g2d.drawImage(Res_FileSelect.button_START_active, 30, 130, null);
	    g2d.drawImage(parent.getCristalSprite(), 17, 134, null);
	} else {
	    g2d.drawImage(Res_FileSelect.button_START, 30, 130, null);
	}
	if (button == 2) {
	    g2d.drawImage(Res_FileSelect.button_COPY_active, 100, 130, null);
	    g2d.drawImage(parent.getCristalSprite(), 87, 134, null);
	} else {
	    g2d.drawImage(Res_FileSelect.button_COPY, 100, 130, null);
	}
	if (button == 3) {
	    g2d.drawImage(Res_FileSelect.button_ERASE_active, 170, 130, null);
	    g2d.drawImage(parent.getCristalSprite(), 157, 134, null);
	} else {
	    g2d.drawImage(Res_FileSelect.button_ERASE, 170, 130, null);
	}
    }

    @Override
    public void update() {
	if (button <= 0)
	    button = 3;
	if (button > 3)
	    button = 1;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
	if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
	    ++button;
	} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
	    --button;
	} else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE
		|| e.getKeyCode() == KeyEvent.VK_ESCAPE) {
	    parent.substate = parent.main;
	    button = 1;
	} else if (e.getKeyCode() == KeyEvent.VK_SPACE && button == 1) {
	    DungeonExplorer.frame.getCanvas().state = CanvasStatesHolder.GAMESTATE;
	}
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

}
