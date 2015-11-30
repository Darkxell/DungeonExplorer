package display;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import res.images.Res_Frame;

/** Static class that holds statics methods to generate shadows. */
public class ShadowUtil {
    public static void applyQualityRenderingHints(Graphics2D g2d) {
	g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
		RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
	g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);
	g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
		RenderingHints.VALUE_COLOR_RENDER_QUALITY);
	g2d.setRenderingHint(RenderingHints.KEY_DITHERING,
		RenderingHints.VALUE_DITHER_ENABLE);
	g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
		RenderingHints.VALUE_FRACTIONALMETRICS_ON);
	g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
		RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
		RenderingHints.VALUE_RENDER_QUALITY);
	g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
		RenderingHints.VALUE_STROKE_PURE);
    }

    public static BufferedImage createShadow(int width, int height) {
	BufferedImage toreturn = new BufferedImage(width, height,
		BufferedImage.TYPE_INT_ARGB);
	Graphics g = toreturn.getGraphics();

	g.drawImage(Res_Frame.swadow_topleft, 0, 0, null);
	for (int i = 10; i < width - 10; ++i) {
	    g.drawImage(Res_Frame.swadow_top, i, 0, null);
	    g.drawImage(Res_Frame.swadow_bot, i, height - 9, null);
	}
	g.drawImage(Res_Frame.swadow_topright, width - 10, 0, null);
	g.drawImage(Res_Frame.swadow_botleft, 0, height - 10, null);
	g.drawImage(Res_Frame.swadow_botright, width - 10, height - 10, null);
	for (int i = 10; i < height - 10; ++i) {
	    g.drawImage(Res_Frame.swadow_left, 0, i, null);
	    g.drawImage(Res_Frame.swadow_right, width - 9, i, null);
	}
	return toreturn;
    }
}
