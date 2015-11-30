package display;

import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ShadowPane extends JPanel {
    private static final long serialVersionUID = 1L;

    private BufferedImage shadow;

    public ShadowPane() {
	setOpaque(false);
	setBorder(new EmptyBorder(10, 10, 10, 10));
    }

    @Override
    public void invalidate() {
	shadow = null;
	super.invalidate();
    }

    @Override
    protected void paintComponent(Graphics g) {
	Insets insets = getInsets();
	int x = insets.left;
	int y = insets.top;
	int width = getWidth() - (insets.left + insets.right);
	int height = getHeight() - (insets.top + insets.bottom);
	if (shadow == null)
	    shadow = ShadowUtil.createShadow(getWidth(), getHeight());
	g.drawImage(shadow, 0, 0, this);
	g.clearRect(x, y, width, height);
    }

}
