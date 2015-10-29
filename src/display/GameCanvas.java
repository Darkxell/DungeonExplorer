package display;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import main.DungeonExplorer;
import management.states.CanvasState;
import management.states.CanvasStatesHolder;

public class GameCanvas extends Canvas {

    private static final long serialVersionUID = 1L;

    public CanvasState state = CanvasStatesHolder.MAINMENUSTATE;

    /**
     * The Width of the internal display script. This is equals to the original
     * GameBoyAdvence screensize.
     */
    public static final int ScreenWidth = 240;
    /**
     * The Height of the internal display script. This is equals to the original
     * GameBoyAdvence screensize.
     */
    public static final int ScreenHeight = 160;

    private int mouseX;
    private int mouseY;

    public GameCanvas() {
	this.addMouseListener(new MouseListener() {

	    @Override
	    public void mouseReleased(MouseEvent e) {
	    }

	    @Override
	    public void mousePressed(MouseEvent e) {
	    }

	    @Override
	    public void mouseExited(MouseEvent e) {
	    }

	    @Override
	    public void mouseEntered(MouseEvent e) {
	    }

	    @Override
	    public void mouseClicked(MouseEvent e) {
	    }
	});
	this.addMouseMotionListener(new MouseMotionListener() {

	    @Override
	    public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	    }

	    @Override
	    public void mouseDragged(MouseEvent e) {
		if (mouseY < 20)
		    DungeonExplorer.frame.moveTo(e.getXOnScreen() - mouseX,
			    e.getYOnScreen() - mouseY);
	    }
	});
	this.addKeyListener(new KeyListener() {

	    @Override
	    public void keyTyped(KeyEvent e) {
		state.keyTyped(e);
	    }

	    @Override
	    public void keyReleased(KeyEvent e) {
		state.keyReleased(e);
	    }

	    @Override
	    public void keyPressed(KeyEvent e) {
		state.keyPressed(e);
	    }
	});
    }

    @Override
    public void update(Graphics g) {
	BufferStrategy bs = this.getBufferStrategy();
	Graphics gr = bs.getDrawGraphics();
	// gr.setColor(Color.WHITE);
	// gr.fillRect(0, 0, GameFrame.WIDTH, 20);
	// gr.setColor(Color.LIGHT_GRAY);
	// gr.fillRect(0, 19, GameFrame.WIDTH, 1);

	BufferedImage doublebuffer = new BufferedImage(ScreenWidth,
		ScreenHeight, BufferedImage.TYPE_INT_RGB);
	Graphics2D doublebuffergraphics = doublebuffer.createGraphics();
	state.print(doublebuffergraphics);
	gr.drawImage(doublebuffer, 0, 20, GameFrame.WIDTH,
		GameFrame.HEIGHT - 20, null);
	bs.show();
	gr.dispose();
    }

}
