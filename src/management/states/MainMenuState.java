package management.states;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import main.DungeonExplorer;
import display.GameCanvas;
import res.audio.MusicHolder;
import res.audio.SoundsHolder;
import res.images.Res_MainMenu;

public class MainMenuState implements CanvasState {

    public int frame;
    public double swordsize;
    public int raystate;
    public int startcounter;
    public int swordshine;

    @Override
    public void print(Graphics2D g2d) {
	g2d.drawImage(Res_MainMenu.background, 0, 0, null);
	// Draws the light rays.
	switch ((int) (raystate / 10) + 1) {
	case 1:
	    g2d.drawImage(Res_MainMenu.light1, 0, 0, null);
	    break;
	case 2:
	    g2d.drawImage(Res_MainMenu.light2, 0, 0, null);
	    break;
	case 3:
	    g2d.drawImage(Res_MainMenu.light3, 0, 0, null);
	    break;
	case 4:
	    g2d.drawImage(Res_MainMenu.light4, 0, 0, null);
	    break;
	default:
	    break;
	}
	if (frame == 120)
	    DungeonExplorer.sm.playSound(SoundsHolder
		    .getSong("MC_TitleScreen.mp3"));
	// Draws the sword.
	if (frame > 150) {
	    int swidth = (int) (Res_MainMenu.sword1.getWidth() * swordsize);
	    int sheight = (int) (Res_MainMenu.sword1.getHeight() * swordsize);
	    int sposx = (GameCanvas.ScreenWidth - swidth) / 2;
	    int sposy = (GameCanvas.ScreenHeight - sheight) / 2;
	    g2d.drawImage(Res_MainMenu.sword1, sposx, sposy, swidth, sheight,
		    null);
	    if (swordshine < 15) {
		g2d.drawImage(Res_MainMenu.sword2, sposx, sposy, swidth,
			sheight, null);
	    } else {
		g2d.drawImage(Res_MainMenu.sword1, sposx, sposy, swidth,
			sheight, null);
	    }
	}
	// Draws the menu title and the start text.
	if (frame > 200) {
	    g2d.drawImage(Res_MainMenu.title, 36, 15, null);
	}
	if (frame > 400 && startcounter > 20) {
	    g2d.drawImage(Res_MainMenu.start, 75, 140, null);
	}

    }

    @Override
    public void update() {
	if (frame < 150) {
	    swordsize = 3.5d;
	} else {
	    if (swordsize > 1)
		swordsize -= 0.1d;
	}
	++frame;
	++raystate;
	if (raystate >= 40)
	    raystate = 0;
	++startcounter;
	if (startcounter >= 80)
	    startcounter = 0;
	++swordshine;
	if (swordshine >= 107)
	    swordshine = 0;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
	if ((e.getKeyCode() == KeyEvent.VK_SPACE
		|| e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_D)
		&& frame > 400) {
	    DungeonExplorer.sm.playSound(SoundsHolder
		    .getSong("MC_Menu_Select.mp3"));
	    DungeonExplorer.sm.setBackgroundMusic(MusicHolder
		    .getSong("MC_FairyFountain1.mp3"));
	    DungeonExplorer.frame.getCanvas().state = new FileSelectState();
	}
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

}
