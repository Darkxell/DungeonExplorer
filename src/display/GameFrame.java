package display;

import javax.swing.JFrame;

/** The game frame */
public class GameFrame {

    /** The frame object used for pretty much the whole thing. */
    private JFrame frame;
    /** The frame object used to display the shadows around the frame. */
    private ShadowWindow shadows;
    private GameCanvas canvas;

    public static final int WIDTH = 720;
    public static final int HEIGHT = 500;

    public GameFrame() {
	this.frame = new JFrame("Dungeon Explorer");
	this.frame.setSize(WIDTH, HEIGHT);
	this.frame.setUndecorated(true);
	this.frame.setLocationRelativeTo(null);
	this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	this.shadows = new ShadowWindow(WIDTH, HEIGHT);
	this.shadows.setLocationRelativeTo(null);

	this.frame.setVisible(true);
	this.shadows.setVisible(true);

	this.canvas = new GameCanvas();
	this.canvas.setSize(WIDTH, HEIGHT);
	this.frame.add(this.canvas);

	this.canvas.createBufferStrategy(3);
    }

    /** Gets the GameCanvas Object. */
    public GameCanvas getCanvas() {
	return this.canvas;
    }

    /** Moves the frame to the specified X and Y coordinates on your screen. */
    public void moveTo(int x, int y) {
	this.frame.setLocation(x, y);
	this.shadows.setLocation(x-10, y-10);
    }

}
