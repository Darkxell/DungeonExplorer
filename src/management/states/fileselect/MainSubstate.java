package management.states.fileselect;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import res.Palette;
import res.images.Res_FileSelect;
import management.saves.SaveFilesManager;
import management.states.CanvasState;
import management.states.FileSelectState;

public class MainSubstate implements CanvasState {

    private FileSelectState parent;

    private int selectedbox = 1;

    public MainSubstate(FileSelectState parent) {
	this.parent = parent;
    }

    @Override
    public void print(Graphics2D g2d) {
	g2d.drawImage(Res_FileSelect.textOverlay_CHOOSEAFILE, 10, 0, null);
	g2d.drawImage(Res_FileSelect.displayarea1, 120, 40, null);
	if (selectedbox == 1) {
	    g2d.drawImage(Res_FileSelect.filebox_active, 20, 30, null);
	    g2d.drawImage(parent.getCristalSprite(), 7, 35, null);
	    if (SaveFilesManager.file1set) {
		g2d.drawImage(parent.getPlayerSprite(), 130, 60, null);
		g2d.setColor(Color.WHITE);
		g2d.drawString(SaveFilesManager.file1name, 33, 47);
	    }
	} else {
	    g2d.drawImage(Res_FileSelect.filebox, 20, 30, null);
	    if (SaveFilesManager.file1set) {
		g2d.setColor(Palette.MenuFontGreen);
		g2d.drawString(SaveFilesManager.file1name, 33, 47);
	    }

	}
	if (selectedbox == 2) {
	    g2d.drawImage(Res_FileSelect.filebox_active, 20, 60, null);
	    g2d.drawImage(parent.getCristalSprite(), 7, 65, null);
	    if (SaveFilesManager.file2set) {
		g2d.drawImage(parent.getPlayerSprite(), 130, 60, null);
		g2d.setColor(Color.WHITE);
		g2d.drawString(SaveFilesManager.file2name, 33, 47);
	    }
	} else {
	    g2d.drawImage(Res_FileSelect.filebox, 20, 60, null);
	    if (SaveFilesManager.file2set) {
		g2d.setColor(Palette.MenuFontGreen);
		g2d.drawString(SaveFilesManager.file2name, 33, 47);
	    }
	}
	if (selectedbox == 3) {
	    g2d.drawImage(Res_FileSelect.filebox_active, 20, 90, null);
	    g2d.drawImage(parent.getCristalSprite(), 7, 95, null);
	    if (SaveFilesManager.file3set) {
		g2d.drawImage(parent.getPlayerSprite(), 130, 60, null);
		g2d.setColor(Color.WHITE);
		g2d.drawString(SaveFilesManager.file3name, 33, 47);
	    }
	} else {
	    g2d.drawImage(Res_FileSelect.filebox, 20, 90, null);
	    if (SaveFilesManager.file3set) {
		g2d.setColor(Palette.MenuFontGreen);
		g2d.drawString(SaveFilesManager.file3name, 33, 47);
	    }
	}
	if (selectedbox == 4) {
	    g2d.drawImage(Res_FileSelect.filebox_other_active, 20, 120, null);
	    g2d.drawImage(parent.getCristalSprite(), 7, 125, null);
	} else {
	    g2d.drawImage(Res_FileSelect.filebox_other, 20, 120, null);
	}
    }

    @Override
    public void update() {
	if (selectedbox < 1)
	    selectedbox = 4;
	if (selectedbox > 4)
	    selectedbox = 1;
	if (selectedbox != 4)
	    SaveFilesManager.currentFile = selectedbox;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
	if (e.getKeyCode() == KeyEvent.VK_DOWN
		|| e.getKeyCode() == KeyEvent.VK_RIGHT)
	    selectedbox++;
	if (e.getKeyCode() == KeyEvent.VK_UP
		|| e.getKeyCode() == KeyEvent.VK_LEFT)
	    selectedbox--;
	if (e.getKeyCode() == KeyEvent.VK_SPACE) {
	    switch (selectedbox) {
	    case 1:
		if (SaveFilesManager.file1set) {
		    parent.substate = parent.fileopener;
		} else {
		    parent.filecreator.reset();
		    parent.substate = parent.filecreator;
		}
		break;
	    case 2:
		if (SaveFilesManager.file2set) {
		    parent.substate = parent.fileopener;
		} else {
		    parent.filecreator.reset();
		    parent.substate = parent.filecreator;
		}
		break;
	    case 3:
		if (SaveFilesManager.file3set) {
		    parent.substate = parent.fileopener;
		} else {
		    parent.filecreator.reset();
		    parent.substate = parent.filecreator;
		}
		break;
	    case 4:

		break;
	    }
	}
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

}
