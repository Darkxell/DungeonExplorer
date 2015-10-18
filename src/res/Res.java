package res;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Res {

    public static final String FOLDER_PATH = "C:\\Users\\Darkxell_mc\\Desktop\\ressources\\";

    public static BufferedImage createimage(String filepath, int x, int y,
	    int width, int height) {
	try {
	    BufferedImage start = ImageIO.read(new File(filepath));
	    return start.getSubimage(x, y, width, height);
	} catch (IOException e) {
	    e.printStackTrace();
	    return null;
	}
    }
}
