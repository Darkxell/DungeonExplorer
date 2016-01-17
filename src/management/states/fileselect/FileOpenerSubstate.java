package management.states.fileselect;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import res.audio.SoundsHolder;
import res.images.Res_FileSelect;
import main.DungeonExplorer;
import management.saves.SaveFilesManager;
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
	switch (SaveFilesManager.currentFile) {
	case 1:
	    g2d.drawImage(Res_FileSelect.num1active, 41, 49, null);
	    g2d.drawString(SaveFilesManager.file1name, 63, 57);
	    break;
	case 2:
	    g2d.drawImage(Res_FileSelect.num2active, 41, 49, null);
	    g2d.drawString(SaveFilesManager.file2name, 63, 57);
	    break;
	case 3:
	    g2d.drawImage(Res_FileSelect.num3active, 41, 49, null);
	    g2d.drawString(SaveFilesManager.file3name, 63, 57);
	    break;
	}
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
	if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE
		|| e.getKeyCode() == KeyEvent.VK_ESCAPE
		|| e.getKeyCode() == KeyEvent.VK_S) {
	    DungeonExplorer.sm.playSound(SoundsHolder
		    .getSong("MC_Menu_Erase.mp3"));
	    button = 1;
	    parent.substate = parent.main;
	} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
	    DungeonExplorer.sm.playSound(SoundsHolder
		    .getSong("MC_Menu_Cursor.mp3"));
	    ++button;
	} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
	    DungeonExplorer.sm.playSound(SoundsHolder
		    .getSong("MC_Menu_Cursor.mp3"));
	    --button;
	} else if ((e.getKeyCode() == KeyEvent.VK_SPACE && button == 1)
		|| (e.getKeyCode() == KeyEvent.VK_D && button == 1)) {
	    DungeonExplorer.sm.playSound(SoundsHolder
		    .getSong("MC_Menu_Select.mp3"));
	    DungeonExplorer.frame.getCanvas().state = CanvasStatesHolder.GAMESTATE;
	}
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

}
