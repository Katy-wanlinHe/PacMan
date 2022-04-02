/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * A basic game object starting in the upper left corner of the game court. It
 * is displayed as the classic 1980 arcade edition of the Pac Man.
 */
public class PacMan extends MovableObj {
    public static final String IMG_FILE = "files/Pacman.png";
    public static final int SIZE = 20;
    public static final int INIT_POS_X = 0;
    public static final int INIT_POS_Y = 0;
    public static final int INIT_STEP = 20;
    private static BufferedImage img;

    /**
     * Setter for the class, takes in the Pac Man icon from files
     */
    public PacMan(int courtWidth, int courtHeight) {
        super(INIT_POS_X, INIT_POS_Y, INIT_STEP, SIZE, SIZE, courtWidth,
                courtHeight);

        try {
            if (img == null) {
                img = ImageIO.read(new File(IMG_FILE));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img, this.getlocX() * this.getStep(),
                this.getlocY() * this.getStep(), this.getWidth(),
                this.getHeight(), null);
    }
}