import java.awt.*;

/**
 * Class for the Dot objects, inherits methods from GameObj class
 */
public class Dot extends StationaryObj {
    public static final int SIZE = 5;
    public static final Color COLOR = Color.ORANGE;

    // each individual dots in the game
    public Dot(int locX, int locY, int courtW, int courtH) {
        super(locX, locY, SIZE, SIZE, courtW, courtH);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillOval(this.getlocY() * this.getStep() + 8,
                this.getlocX() * this.getStep() + 8, this.getWidth(),
                this.getHeight());
    }

    public boolean isWall() {
        return false;
    }
}
