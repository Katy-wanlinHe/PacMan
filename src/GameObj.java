/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

import java.awt.Graphics;

/**
 * An object in the game.
 *
 * Game objects exist in the game court. They have a locX, locY, step, size and
 * direction. Their velocity controls how they move; their position should
 * always be within their bounds.
 */
public abstract class GameObj {
    /*
     * Current position of the object (in terms of graphics coordinates)
     * 
     * Coordinates are given by the upper-left hand corner of the object. This
     * position should always be within bounds. 0 <= px <= maxX 0 <= py <= maxY
     */

    /* Location of the object in maze map */
    private int locX;
    private int locY;

    /* Size of object, in pixels. */
    private int width;
    private int height;

    /* step: number of pixels to move every time move() is called. */
    private int step;

    /*
     * Upper bounds of the area in which the object can be positioned. Maximum
     * permissible x, y positions for the upper-left hand corner of the object.
     */
    private int maxX;
    private int maxY;

    /**
     * Constructor
     */
    public GameObj(int locX, int locY, int step, int width, int height,
            int courtWidth, int courtHeight) {
        this.locX = locX;
        this.locY = locY;
        this.step = step;
        this.width = width;
        this.height = height;

        // take the width and height into account when setting the bounds for
        // the upper left corner
        // of the object.
        this.maxX = courtWidth - width;
        this.maxY = courtHeight - height;
    }

    /***
     * GETTERS
     **********************************************************************************/
    public int getStep() {
        return step;
    }

    public int getlocX() {
        return locX;
    }

    public int getlocY() {
        return locY;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
    
    public int getMaxX() {
        return maxX;
    }
    
    public int getMaxY() {
        return maxY;
    }

    /***
     * SETTERS
     **********************************************************************************/
    public void setlocX(int px) {
        this.locX = px;
        clip();
    }

    public void setlocY(int py) {
        this.locY = py;
        clip();
    }

    public void setStep(int step) {
        this.step = step;
    }

    /***
     * UPDATES AND OTHER METHODS
     ****************************************************************/

    /**
     * Prevents the object from going outside of the bounds of the area
     * designated for the object. (i.e. Object cannot go outside of the active
     * area the user defines for it).
     */
    public void clip() {
        this.locX = Math.min(Math.max(this.locX * step, 0), this.maxX) / 20;
        this.locY = Math.min(Math.max(this.locY * step, 0), this.maxY) / 20;
    }


    /**
     * Default draw method that provides how the object should be drawn in the
     * GUI. This method does not draw anything. Subclass should override this
     * method based on how their object should appear.
     * 
     * @param g
     *            The <code>Graphics</code> context used for drawing the object.
     *            Remember graphics contexts that we used in OCaml, it gives the
     *            context in which the object should be drawn (a canvas, a
     *            frame, etc.)
     */
    public abstract void draw(Graphics g);
}