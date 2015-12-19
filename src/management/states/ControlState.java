package management.states;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import main.DungeonExplorer;
import res.images.Res_FileSelect;
import res.images.Res_MainMenu;
import display.sprites.AnimatedSprite;
import display.sprites.misc.MenuCristalSprite;

public class ControlState implements CanvasState {

    public ControlState(CanvasState returnstate) {
	this.returnstate = returnstate;
    }

    private CanvasState returnstate;
    private int backgroundglow;
    private int cristalglow;
    private AnimatedSprite cristal = MenuCristalSprite.create();

    @Override
    public void print(Graphics2D g2d) {
	if (backgroundglow < 10)
	    g2d.drawImage(Res_FileSelect.background2, 0, 0, null);
	else if (backgroundglow < 20)
	    g2d.drawImage(Res_FileSelect.background3, 0, 0, null);
	else if (backgroundglow < 30)
	    g2d.drawImage(Res_FileSelect.background4, 0, 0, null);
	else if (backgroundglow < 40)
	    g2d.drawImage(Res_FileSelect.background3, 0, 0, null);
	else if (backgroundglow < 40)
	    g2d.drawImage(Res_FileSelect.background2, 0, 0, null);
	else
	    g2d.drawImage(Res_FileSelect.background1, 0, 0, null);
	g2d.drawImage(Res_MainMenu.controls, 0, 0, null);
	g2d.drawImage(cristal.get(), 15, 139, null);
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
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
	if (e.getKeyCode() == KeyEvent.VK_D
		|| e.getKeyCode() == KeyEvent.VK_ENTER) {
	    DungeonExplorer.frame.getCanvas().state = returnstate;
	}
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

}
