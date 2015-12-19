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
	this.shadows.setLocation(x - 10, y - 10);
    }

    public void minimize() {
	shadows.setVisible(false);
	frame.setState(JFrame.ICONIFIED);
    }

    /** Bump this frame to the top. */
    public void bump() {
	shadows.setVisible(true);
	frame.setVisible(true);
    }

    /**
     * Changes the size of the frame the the desired value. This method changes
     * the size of the frame, the inner canvas and the shadows.
     */
    public void changeSize(int width, int height) {
	this.frame.setSize(width, height);
	this.shadows.setSize(width + 20, height + 20);
	this.canvas.setSize(width, height);
    }
}
