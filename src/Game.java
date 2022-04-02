/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

// imports necessary libraries for Java swing
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {
    public void run() {
        // frame and game name
        final JFrame frame = new JFrame("Pac Man!");
        frame.setLocation(300, 300);

        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Running...");
        status_panel.add(status);
        
        //Display Score
        final JLabel score = new JLabel("Your Score: " + 0);
        status_panel.add(score);

        // Main playing area
        final GameCourt court = new GameCourt(status, score);
        frame.add(court, BorderLayout.CENTER);
        

        // The top panel
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);
        control_panel.setLayout(new BoxLayout(control_panel, BoxLayout.Y_AXIS));

        final JPanel text_panel = new JPanel();
        control_panel.add(text_panel);
        text_panel.setLayout(new GridLayout(4, 1));

        // game description
        final JLabel description1 = new JLabel(
                "Move the yellow Pac Man around the maze using the arrow keys");
        text_panel.add(description1);
        final JLabel description2 = new JLabel(
                "Try collecting all the dots without being caught by the ghosts!");
        text_panel.add(description2);
        final JLabel description3 = new JLabel(
                "What if you collide with the ghost?");
        text_panel.add(description3);
        final JLabel description4 = new JLabel(
                "Press Respawn and give yourself another chance!");
        text_panel.add(description4);

        final JPanel sub_panel = new JPanel();
        control_panel.add(sub_panel);

        // the reset button, set the game to its original state
        final JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                court.reset();
            }
        });
        sub_panel.add(reset);

        // the respawn button,
        // send the ghost back to its birth location and gives continues the
        // game
        final JButton revive = new JButton("Respawn");
        revive.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                court.revive();
            }
        });
        sub_panel.add(revive);

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start game
        court.reset();
    }

    /**
     * Main method run to start and run the game. Initializes the GUI elements
     * specified in Game and runs it. IMPORTANT: Do NOT delete! You MUST include
     * this in your final submission.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}