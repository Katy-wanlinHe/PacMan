/**
 * the super class that includes Dot and Maze In this class, the step is locked
 * to 20, and the direction of the could only be set to null
 */
public abstract class StationaryObj extends GameObj {
    private static final int STEP = 20;

    public StationaryObj(int locX, int locY, int width, int height,
            int courtWidth, int courtHeight) {
        super(locX, locY, STEP, width, height, courtWidth, courtHeight);
    }
}
