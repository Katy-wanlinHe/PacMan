import java.awt.*;

/**
 * Stationary game objects with set locations in the game, represented by the
 * value 0 in the Maze. It is displayed as gray square.
 */
public class Wall extends StationaryObj {
    public static final int SIZE = 20;
    public static final Color COLOR = Color.GRAY;

    public Wall(int locX, int locY, int courtW, int courtH) {
        super(locX, locY, SIZE, SIZE, courtW, courtH);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(COLOR);
        g.fillRect(this.getlocY() * this.getStep(),
                this.getlocX() * this.getStep(), this.getWidth(),
                this.getHeight());
    }

}
