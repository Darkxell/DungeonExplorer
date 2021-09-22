package display;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JWindow;

/** Object that represents a frame shadows. */
public class ShadowWindow extends JWindow {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a ShadowWindow. Constructed shadowWindow has a 10px border
	 * containing the shadows.
	 * 
	 * @param width  : the width of the contained frame.
	 * @param height : the height of the contained frame.
	 */
	public ShadowWindow(int width, int height) {
		super();
		setSize(width + 20, height + 20);
		setBackground(new Color(0, 0, 0, 0));
		setContentPane(new ShadowPane());
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
	}

}
