/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/**
 * Class for the Ghost objects, inherits methods from GameObj class. It location
 * could be set using the randomTrun method. Each ghost object intakes a file as
 * its display
 */
public class Ghost extends MovableObj {
    public static final int SIZE = 20;
    public static final int REBORN_LOC = 10;
    public static final int INIT_STEP = 20;
    private ArrayList<Direction> dirList;
    private int num;

    private static BufferedImage img;

    public Ghost(int courtWidth, int courtHeight, int posX, int posY,
            String file) {
        super(posX / 20, posY / 20, INIT_STEP, SIZE, SIZE, courtWidth,
                courtHeight);
        dirList = new ArrayList<Direction>();
        dirList.add(Direction.UP);
        dirList.add(Direction.DOWN);
        dirList.add(Direction.LEFT);
        dirList.add(Direction.RIGHT);
        num = 4;

        try {
            if (img == null) {
                img = ImageIO.read(new File(file));
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

    /* send the ghost object back to its reborn location */
    public void reborn() {
        this.setlocX(REBORN_LOC);
        this.setlocY(REBORN_LOC);
    }

    /*
     * turn the ghost in a random direction To make sure the ghost makes a valid
     * turn each time the method is called the method is recursive
     */
    public void randomTurn(Maze m) {
        Direction toTurn = dirList.get((int) (Math.random() * num));
        setDir(toTurn);
        
        if (hitWall(m)) {
            dirList.remove(toTurn);
            num--;
            randomTurn(m);
        } else {
            dirList.clear();
            dirList.add(Direction.UP);
            dirList.add(Direction.DOWN);
            dirList.add(Direction.LEFT);
            dirList.add(Direction.RIGHT);
            num = 4;
        }
    }
}
