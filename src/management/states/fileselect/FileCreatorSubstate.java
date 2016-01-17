package management.states.fileselect;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import res.audio.SoundsHolder;
import res.images.Res_FileSelect;
import util.UTF8StringUtility;
import main.DungeonExplorer;
import management.saves.SaveFilesManager;
import management.states.CanvasState;
import management.states.FileSelectState;

public class FileCreatorSubstate implements CanvasState {

    private FileSelectState parent;

    private int letterX;
    private int letterY;
    private boolean uppercase;

    public String name = "";

    public FileCreatorSubstate(FileSelectState parent) {
	this.parent = parent;
    }

    @Override
    public void print(Graphics2D g2d) {
	g2d.drawImage(Res_FileSelect.displayarea3, 6, 35, null);
	// Draws the red pointer
	if (letterY != 5) {
	    g2d.drawImage(Res_FileSelect.charoverlay, 15 + (letterX * 16),
		    51 + (letterY * 16), null);
	}
	// Draws the letters
	if (uppercase)
	    g2d.drawImage(Res_FileSelect.uppergrid, 11, 50, null);
	else
	    g2d.drawImage(Res_FileSelect.lowergrid, 11, 50, null);
	g2d.drawImage(Res_FileSelect.numgrid, 11, 115, null);
	// Draws the 4 blue buttons
	if (letterX == 0 && letterY == 5)
	    g2d.drawImage(Res_FileSelect.kbutton_up_active, 30, 134, null);
	else
	    g2d.drawImage(Res_FileSelect.kbutton_up, 30, 134, null);
	if (letterX == 1 && letterY == 5)
	    g2d.drawImage(Res_FileSelect.kbutton_low_active, 80, 134, null);
	else
	    g2d.drawImage(Res_FileSelect.kbutton_low, 80, 134, null);
	if (letterX == 2 && letterY == 5)
	    g2d.drawImage(Res_FileSelect.kbutton_back_active, 130, 134, null);
	else
	    g2d.drawImage(Res_FileSelect.kbutton_back, 130, 134, null);
	if (letterX == 3 && letterY == 5)
	    g2d.drawImage(Res_FileSelect.kbutton_END_active, 180, 134, null);
	else
	    g2d.drawImage(Res_FileSelect.kbutton_END, 180, 134, null);
	// Draws the name box at the top and the cristal
	g2d.drawImage(Res_FileSelect.filebox_active, 15, 22, null);
	g2d.setColor(Color.WHITE);
	g2d.drawString(name, 27, 38);
	g2d.drawImage(parent.getCristalSprite(), 2, 27, null);
	switch (SaveFilesManager.currentFile) {
	case 1:
	    g2d.drawImage(Res_FileSelect.num1active, 6, 31, null);
	    break;
	case 2:
	    g2d.drawImage(Res_FileSelect.num2active, 6, 31, null);
	    break;
	case 3:
	    g2d.drawImage(Res_FileSelect.num3active, 6, 31, null);
	    break;
	}

	// Draws the entername text
	g2d.drawImage(Res_FileSelect.textOverlay_ENTERNAME, 10, 0, null);
    }

    @Override
    public void update() {
    }

    /** Rests this state. */
    public void reset() {
	this.letterX = 0;
	this.letterY = 0;
	this.name = "";
    }

    /**
     * End the state. The Fileselect screen go back to main, and the new created
     * file is either created or deleted.
     */
    private void end(boolean shouldcreate) {
	if (shouldcreate) {
	    switch (SaveFilesManager.currentFile) {
	    case 1:
		SaveFilesManager.file1set = true;
		SaveFilesManager.file1name = this.name;
		break;
	    case 2:
		SaveFilesManager.file2set = true;
		SaveFilesManager.file2name = this.name;
		break;
	    case 3:
		SaveFilesManager.file3set = true;
		SaveFilesManager.file3name = this.name;
		break;
	    }
	}
	parent.substate = parent.main;
	this.reset();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
	if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
	    DungeonExplorer.sm.playSound(SoundsHolder
		    .getSong("MC_Menu_Cursor.mp3"));
	    uppercase = !uppercase;
	} else if (e.getKeyCode() == KeyEvent.VK_ENTER
		|| (e.getKeyCode() == KeyEvent.VK_SPACE && letterX == 3 && letterY == 5)
		|| (e.getKeyCode() == KeyEvent.VK_D && letterX == 3 && letterY == 5)) {
	    DungeonExplorer.sm.playSound(SoundsHolder
		    .getSong("MC_Menu_Select.mp3"));
	    this.end(!name.equals(""));
	} else if ((e.getKeyCode() == KeyEvent.VK_SPACE && letterX == 0 && letterY == 5)
		|| (e.getKeyCode() == KeyEvent.VK_D && letterX == 0 && letterY == 5)
		|| e.getKeyCode() == KeyEvent.VK_A) {
	    DungeonExplorer.sm.playSound(SoundsHolder
		    .getSong("MC_Menu_Cursor.mp3"));
	    uppercase = true;
	} else if ((e.getKeyCode() == KeyEvent.VK_SPACE && letterX == 1 && letterY == 5)
		|| (e.getKeyCode() == KeyEvent.VK_D && letterX == 1 && letterY == 5)
		|| e.getKeyCode() == KeyEvent.VK_Z) {
	    DungeonExplorer.sm.playSound(SoundsHolder
		    .getSong("MC_Menu_Cursor.mp3"));
	    uppercase = false;
	} else if ((e.getKeyCode() == KeyEvent.VK_SPACE && letterX == 2 && letterY == 5)
		|| (e.getKeyCode() == KeyEvent.VK_D && letterX == 2 && letterY == 5)
		|| e.getKeyCode() == KeyEvent.VK_BACK_SPACE
		|| e.getKeyCode() == KeyEvent.VK_S) {
	    DungeonExplorer.sm.playSound(SoundsHolder
		    .getSong("MC_Menu_Erase.mp3"));
	    name = UTF8StringUtility.removeLastChar(name);
	} else if ((e.getKeyCode() == KeyEvent.VK_SPACE && letterY != 5)
		|| (e.getKeyCode() == KeyEvent.VK_D && letterY != 5)) {
	    if (name.length() < 6) {
		name += UTF8StringUtility.getCharFrom(letterX, letterY,
			uppercase);
		DungeonExplorer.sm.playSound(SoundsHolder
			.getSong("MC_Menu_Letter.mp3"));
	    } else {
		DungeonExplorer.sm.playSound(SoundsHolder
			.getSong("MC_Menu_Erase.mp3"));
	    }
	}
    }

    @Override
    public void keyPressed(KeyEvent e) {
	if (e.getKeyCode() == KeyEvent.VK_LEFT) {
	    DungeonExplorer.sm.playSound(SoundsHolder
		    .getSong("MC_Menu_Cursor2.mp3"));
	    letterX--;
	    if (letterY == 5) {
		if (letterX < 0)
		    letterX = 3;
	    } else if (letterX < 0)
		letterX = 12;
	} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
	    DungeonExplorer.sm.playSound(SoundsHolder
		    .getSong("MC_Menu_Cursor2.mp3"));
	    letterX++;
	    if (letterY == 5) {
		if (letterX > 3)
		    letterX = 0;
	    } else if (letterX > 12)
		letterX = 0;
	} else if (e.getKeyCode() == KeyEvent.VK_UP) {
	    DungeonExplorer.sm.playSound(SoundsHolder
		    .getSong("MC_Menu_Cursor2.mp3"));
	    letterY--;
	    if (letterY < 0) {
		letterY = 5;
		letterX = 0;
	    }
	} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
	    DungeonExplorer.sm.playSound(SoundsHolder
		    .getSong("MC_Menu_Cursor2.mp3"));
	    letterY++;
	    if (letterY == 5)
		letterX = 0;
	    if (letterY > 5)
		letterY = 0;
	}
    }

}
