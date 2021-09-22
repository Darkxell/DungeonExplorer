package display;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import res.images.Res_Frame;
import main.DungeonExplorer;
import management.player.PlayerInfo;
import management.states.CanvasState;
import management.states.ControlState;
import management.states.MainMenuState;

public class GameCanvas extends Canvas {

    private static final long serialVersionUID = 1L;

    public CanvasState state = new MainMenuState();

    /**
     * Set this to true to display the coordinates of the player in the current
     * floor on the title bar.
     */
    private boolean coordsHack = false;

    private boolean isCursorExtends;

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
    private boolean isMouseInside;
    private boolean isMouseOnCorner;
    
    public GameCanvas() {
	this.addMouseListener(new MouseListener() {

	    @Override
	    public void mouseReleased(MouseEvent e) {
		if (isMouseInside && mouseY < 20 && mouseX > getWidth() - 29)
		    System.exit(0);
		if (isMouseInside && mouseY < 20 && mouseX > getWidth() - 58
			&& mouseX < getWidth() - 29)
		    DungeonExplorer.frame.minimize();
		if (isMouseInside && mouseY < 20 && mouseX < 29
			&& !(state instanceof ControlState))
		    state = new ControlState(state);
		isMouseOnCorner = false;
	    }

	    @Override
	    public void mousePressed(MouseEvent e) {
		DungeonExplorer.frame.bump();
		isMouseOnCorner = (e.getY() > getHeight() - 20 && e.getX() > getWidth() - 20);
	    }

	    @Override
	    public void mouseExited(MouseEvent e) {
		isMouseInside = false;
	    }

	    @Override
	    public void mouseEntered(MouseEvent e) {
		isMouseInside = true;
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
		if (isMouseOnCorner) {
		    DungeonExplorer.frame.changeSize(
			    (e.getX() + 10 > 240) ? e.getX() + 10 : 240,
			    (e.getY() + 10 > 180) ? e.getY() + 10 : 180);
		}
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
		if(e.getKeyCode() == KeyEvent.VK_F11)
			PlayerInfo.DEBUGMODE = !PlayerInfo.DEBUGMODE;
	    }
	});
    }

    @Override
    public void update(Graphics g) {
	if ((mouseY > getHeight() - 20 && mouseX > getWidth() - 20)
		|| this.isMouseOnCorner) {
	    if (!isCursorExtends) {
		this.setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
		isCursorExtends = true;
	    }
	} else if (isCursorExtends) {
	    this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	    isCursorExtends = false;
	}

	BufferStrategy bs = this.getBufferStrategy();
	Graphics gr = bs.getDrawGraphics();
	gr.setColor(Color.WHITE);
	gr.fillRect(0, 0, getWidth(), getHeight());
	gr.drawImage(
		(isMouseInside && mouseY < 20 && mouseX > getWidth() - 29) ? Res_Frame.close_active
			: Res_Frame.close, getWidth() - 29, 0, null);
	gr.drawImage(
		(isMouseInside && mouseY < 20 && mouseX < 29) ? Res_Frame.controls_active
			: Res_Frame.controls, 0, 0, null);
	gr.drawImage(
		(isMouseInside && mouseY < 20 && mouseX > getWidth() - 58 && mouseX < getWidth() - 29) ? Res_Frame.minimize_active
			: Res_Frame.minimize, getWidth() - 58, 0, null);
	gr.setColor(Color.GRAY);
	if (coordsHack)
	    gr.drawString("Zelda : the minish cap | Coords Hack : "
		    + (int) (PlayerInfo.posX) + "/" + (int) (PlayerInfo.posY),
		    35, 15);
	else
	    gr.drawString("Zelda : the minish cap", 35, 15);
	BufferedImage doublebuffer = new BufferedImage(ScreenWidth,
		ScreenHeight, BufferedImage.TYPE_INT_RGB);
	Graphics2D doublebuffergraphics = doublebuffer.createGraphics();
	state.print(doublebuffergraphics);
	gr.drawImage(doublebuffer, 0, 20, getWidth(), getHeight() - 20, null);
	bs.show();
	gr.dispose();
    }

}
