package management.states;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import display.sprites.AnimatedSprite;
import display.sprites.misc.MenuCristalSprite;
import display.sprites.misc.MenuPlayerSprite;
import management.states.fileselect.FileCreatorSubstate;
import management.states.fileselect.FileOpenerSubstate;
import management.states.fileselect.MainSubstate;
import res.images.Res_FileSelect;

public class FileSelectState implements CanvasState {

    public MainSubstate main = new MainSubstate(this);
    public FileOpenerSubstate fileopener = new FileOpenerSubstate(this);
    public FileCreatorSubstate filecreator = new FileCreatorSubstate(this);

    public CanvasState substate = main;

    private int backgroundglow;
    private int cristalglow;
    private int playermove;

    private AnimatedSprite cristal = MenuCristalSprite.create();
    private AnimatedSprite player = MenuPlayerSprite.create();

    @Override
    public void print(Graphics2D g2d) {
	if (backgroundglow < 10) {
	    g2d.drawImage(Res_FileSelect.background2, 0, 0, null);
	} else if (backgroundglow < 20) {
	    g2d.drawImage(Res_FileSelect.background3, 0, 0, null);
	} else if (backgroundglow < 30) {
	    g2d.drawImage(Res_FileSelect.background4, 0, 0, null);
	} else if (backgroundglow < 40) {
	    g2d.drawImage(Res_FileSelect.background3, 0, 0, null);
	} else if (backgroundglow < 40) {
	    g2d.drawImage(Res_FileSelect.background2, 0, 0, null);
	} else {
	    g2d.drawImage(Res_FileSelect.background1, 0, 0, null);
	}
	substate.print(g2d);
    }

    @Override
    public void update() {
	++backgroundglow;
	if (backgroundglow > 100)
	    backgroundglow = 0;
	++cristalglow;
	if (cristalglow > 6) {
	    cristalglow = 0;
	    cristal.next();
	}
	++playermove;
	if (playermove > 2) {
	    playermove = 0;
	    player.next();
	}

	substate.update();

    }

    /** gets the cristal sprite accorded to the inner values. */
    public BufferedImage getCristalSprite() {
	return cristal.get();
    }

    /** gets the player sprite accorded to the inner values. */
    public BufferedImage getPlayerSprite() {
	return player.get();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
	substate.mouseReleased(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
	substate.mousePressed(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
	substate.mouseExited(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
	substate.mouseEntered(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
	substate.mouseClicked(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
	substate.mouseMoved(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
	substate.mouseDragged(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
	substate.keyTyped(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
	substate.keyReleased(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
	substate.keyPressed(e);
    }

}
