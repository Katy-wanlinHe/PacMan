/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * GameCourt
 * 
 * This class holds the primary game logic for how different objects interact
 * with one another. Take time to understand how the timer interacts with the
 * different methods and how it repaints the GUI on every tick().
 */
@SuppressWarnings("serial")
public class GameCourt extends JPanel {

    // the state of the game logic
    private PacMan square; // the yellow PacMan, keyboard control
    private Ghost poison; // the first ghost that chases you
    private Ghost poison2; // the second ghost that chases you
    private Maze maze; // the maze in the game, does not move

    private boolean playing = false; // whether the game is running
    private JLabel status; // Current status text, i.e. "Running..."
    private JLabel score;

    // Game constants
    public static final int COURT_WIDTH = 400;
    public static final int COURT_HEIGHT = 400;
    public static final int VELOCITY = 20; // the interval which the object moves

    // Interval for the movement of the ghost
    public static final int TURN_INTERVAL = 3; // turn every three after three move()
    private int count1 = 0;
    private int count2 = 0;

    // Update interval for timer, in milliseconds
    public static final int INTERVAL = 60;

    public GameCourt(JLabel status, JLabel score) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // creates maze
        maze = new Maze(COURT_WIDTH, COURT_HEIGHT);

        // The timer is an object which triggers an action periodically with the
        // given INTERVAL. We
        // register an ActionListener with this timer, whose actionPerformed()
        // method is called each
        // time the timer triggers. We define a helper method called tick() that
        // actually does
        // everything that should be done in a single timestep.
        Timer timer = new Timer(INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });

        timer.start(); // MAKE SURE TO START THE TIMER!

        // Enable keyboard focus on the court area.
        // When this component has the keyboard focus, key events are handled by
        // its key listener.
        setFocusable(true);

        // This key listener allows the square to move as long as an arrow key
        // is pressed, by
        // changing the square's velocity accordingly. (The tick method below
        // actually moves the
        // square.)
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    square.setDir(Direction.LEFT);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    square.setDir(Direction.RIGHT);
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    square.setDir(Direction.DOWN);
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    square.setDir(Direction.UP);
                }
            }

            public void keyReleased(KeyEvent e) {
                square.setDir(null);
            }
        });

        this.status = status;
        this.score = score;
    }

    /**
     * (Re-)set the game to its initial state.
     */
    public void reset() {
        square = new PacMan(COURT_WIDTH, COURT_HEIGHT);
        poison = new Ghost(COURT_WIDTH, COURT_HEIGHT, 200, 200,
                "files/ghost2.png");
        poison2 = new Ghost(COURT_WIDTH, COURT_HEIGHT, 180, 200,
                "files/ghost2.png");
        maze = new Maze(COURT_WIDTH, COURT_HEIGHT);
        playing = true;
        status.setText("Running...");

        // Make sure that this component has the keyboard focus
        requestFocusInWindow();
    }

    /**
     * Resume the game by setting the ghost that collide with the PacMan to the
     * respawn location.
     */
    public void revive() {
        if (!playing && square.intersects(poison, maze)) {
            poison.reborn();
            playing = true;
            status.setText("Running...");
            requestFocusInWindow();
        } else if (!playing && square.intersects(poison2, maze)) {
            poison2.reborn();
            playing = true;
            status.setText("Running...");
            requestFocusInWindow();
        }
    }

    /**
     * This method is called every time the timer defined in the constructor
     * triggers.
     */
    void tick() {
        if (playing) {
            // advance the square in their current direction.
            square.move(maze);

            // advance the ghost1 to the next location or turn
            if (count1 == 0) {
                poison.randomTurn(maze);
                poison.move(maze);
                count1 = TURN_INTERVAL;
            } else if (poison.willIntersect(poison2)) {
                poison.randomTurn(maze);
                poison.move(maze);
                count1 = TURN_INTERVAL;
            } else {
                poison.move(maze);
                count1--;
            }

            // advance ghost2 to the next location
            if (count2 == 0) {
                poison2.randomTurn(maze);
                poison2.move(maze);
                count2 = TURN_INTERVAL;
            } else {
                poison2.move(maze);
                count2--;
            }

            // check for the game end conditions
            if (square.intersects(poison, maze)
                    || square.intersects(poison2, maze)) {
                playing = false;
                status.setText("You lose!");
            } else if (maze.getScore() == maze.getTotalScore()) {
                playing = false;
                status.setText("You Win!");
            } else if (square.intersects(
                    maze.getObj(square.getlocX(), square.getlocY()), maze)) {
                maze.retrived(maze.getObj(square.getlocX(), square.getlocY()));
                score.setText("Your Score: " + getScore());
            }

            // update the display
            repaint();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        maze.draw(g);
        square.draw(g);
        poison.draw(g);
        poison2.draw(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }

    /*
     * Testing Methods these include getters for the location for different
     * objects, accessing game state and score, manually setting the direction
     * and movement for the two ghosts for testing
     */
    public void poisonSetLocation(int x, int y) {
        poison.setlocX(x);
        poison.setlocY(y);
        poison.setDir(Direction.LEFT);
        count1 = 5;
    }
    
    public void changePManDirection(Direction dir) {
        square.setDir(dir);
    }

    public void setGhostDirection(Direction r) {
        poison.setDir(r);
    }

    public void manSetLocation(int x, int y) {
        square.setlocX(x);
        square.setlocY(y);
        square.setDir(Direction.RIGHT);
    }
    
    public int[] getPoison1Loc() {
        int[] target = new int[2];
        target[0] = poison.getlocX();
        target[1] = poison.getlocY();
        return target;
    }

    public int[] getPoison2Loc() {
        int[] target = new int[2];
        target[0] = poison2.getlocX();
        target[1] = poison2.getlocY();
        return target;
    }

    public int[] getPacManLoc() {
        int[] target = new int[2];
        target[0] = square.getlocX();
        target[1] = square.getlocY();
        return target;
    }

    public int getScore() {
        return maze.getScore();
    }

    public boolean getPlaying() {
        return playing;
    }

    
    public int getCount1() {
        return count1;
    }
    
    public int getCount2() {
        return count2;
    }
}