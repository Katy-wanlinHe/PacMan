import org.junit.jupiter.api.*;
import javax.swing.*;
import static org.junit.jupiter.api.Assertions.*;

/** 
 *  You can use this file (and others) to test your
 *  implementation.
 */

public class GameTest {
    /*GameObj Methods Tests*/
    @Test
    public void hitBoundaryTest() {
        Maze m = new Maze(400, 400);
        PacMan man = new PacMan(400, 400);
        man.setDir(Direction.UP);
        man.move(m);
        assertEquals(man.getlocX(), 0);
        assertEquals(man.getlocY(), 0);
        assertTrue(man.hitWall(m));
    }
    
    @Test
    public void hitWallTest() {
        Maze m = new Maze(400, 400);
        PacMan man = new PacMan(400, 400);
        man.setDir(Direction.DOWN);
        man.move(m);
        man.setDir(Direction.RIGHT);
        man.move(m);
        assertEquals(man.getlocX(), 0);
        assertEquals(man.getlocY(), 1);
        assertTrue(man.hitWall(m));
    }
    
    @Test
    public void nextLocNormaltest() {
        PacMan man = new PacMan(400, 400);
        man.setDir(Direction.DOWN);
        assertEquals(man.nextLoc()[1], 1);
        assertEquals(man.nextLoc()[0], 0);
    }
    
    @Test
    public void nextLocInvalidtest() {
        Maze m = new Maze(400, 400);
        PacMan man = new PacMan(400, 400);
        man.setDir(Direction.DOWN);
        man.move(m);
        man.setDir(Direction.RIGHT);
        assertEquals(man.nextLoc()[1], 1);
        assertEquals(man.nextLoc()[0], 1);
    }
    
    /* Objects Interactions test */
    @Test
    public void intersectNormaltest() {
        Maze m = new Maze(400, 400);
        PacMan man = new PacMan(400, 400);
        assertTrue(man.intersects(m.getObj(0, 0), m));
    }
    
    @Test
    public void intersectGhostTest() {
        Maze m = new Maze(400, 400);
        PacMan man = new PacMan(400, 400);
        Ghost ghost = new Ghost(400, 400, 20, 20, "files/ghost1.png");
        ghost.setlocX(0);
        ghost.setlocY(0);
        assertTrue(man.intersects(ghost, m));
    }
    
    @Test
    public void scoreTest() {
        Maze m = new Maze(400, 400);
        PacMan man = new PacMan(400, 400);
        man.setDir(Direction.DOWN);
        if (man.intersects(m.getObj(man.getlocX(), man.getlocY()), m)) {
            m.retrived(m.getObj(man.getlocX(), man.getlocY()));
        }
        man.move(m);
        if (man.intersects(m.getObj(man.getlocX(), man.getlocY()), m)) {
            m.retrived(m.getObj(man.getlocX(), man.getlocY()));
        }
        man.move(m);
        if (man.intersects(m.getObj(man.getlocX(), man.getlocY()), m)) {
            m.retrived(m.getObj(man.getlocX(), man.getlocY()));
        }
        assertEquals(m.getScore(), 3);
    }
    
    @Test
    public void scoreOverlapTest() {
        Maze m = new Maze(400, 400);
        PacMan man = new PacMan(400, 400);
        man.setDir(Direction.DOWN);
        if (man.intersects(m.getObj(man.getlocX(), man.getlocY()), m)) {
            m.retrived(m.getObj(man.getlocX(), man.getlocY()));
        }
        man.move(m);
        if (man.intersects(m.getObj(man.getlocX(), man.getlocY()), m)) {
            m.retrived(m.getObj(man.getlocX(), man.getlocY()));
        }
        man.setDir(Direction.UP);
        man.move(m);
        if (man.intersects(m.getObj(man.getlocX(), man.getlocY()), m)) {
            m.retrived(m.getObj(man.getlocX(), man.getlocY()));
        }
        assertEquals(m.getScore(), 2);
    }
    
    /* Game Court Interactions Test */
    @Test
    public void courtFunctionTestRegular() {
        JLabel label = new JLabel("Running...");
        JLabel score = new JLabel("Your Score: " + 0);
        GameCourt g = new GameCourt(label, score);
        g.reset();
        g.tick();
        g.tick();
        g.tick();
        int[] p1 = {20, 20};
        int[] p2 = {19, 20};
        assertFalse(g.getPoison1Loc().equals(p1));
        assertFalse(g.getPoison2Loc().equals(p2));
        assertEquals(g.getScore(), 1);
    }
    
    @Test
    public void courtFunctionTestRespawn() {
        JLabel label = new JLabel("Running...");
        JLabel score = new JLabel("Your Score: " + 0);
        GameCourt g = new GameCourt(label, score);
        g.reset();
        g.tick();
        g.tick();
        g.tick();
        g.revive();
        int[] p1 = {10, 10};
        int[] p2 = {9, 10};
        assertFalse(g.getPoison1Loc().equals(p1));
        assertFalse(g.getPoison2Loc().equals(p2));
        assertEquals(g.getScore(), 1);
    }
    
    @Test
    public void courtCollisionTestOddGap() {
        JLabel label = new JLabel("Running...");
        JLabel score = new JLabel("Your Score: " + 0);
        GameCourt g = new GameCourt(label, score);
        g.reset();
        g.poisonSetLocation(19, 0);
        g.manSetLocation(14, 0);
        g.tick();
        g.tick();
        assertEquals(g.getPoison1Loc()[0], 17);
        assertEquals(g.getPoison1Loc()[1], 0);
        assertEquals(g.getPacManLoc()[0], 17);
        assertEquals(g.getPacManLoc()[1], 0);
        assertFalse(g.getPlaying());
    }
    
    @Test
    public void courtCollisionTestEvenGap() {
        JLabel label = new JLabel("Running...");
        JLabel score = new JLabel("Your Score: " + 0);
        GameCourt g = new GameCourt(label, score);
        g.reset();
        g.poisonSetLocation(19, 0);
        g.manSetLocation(15, 0);
        g.tick();
        g.tick();
        assertEquals(g.getPoison1Loc()[0], 17);
        assertEquals(g.getPoison1Loc()[1], 0);
        assertEquals(g.getPacManLoc()[0], 17);
        assertEquals(g.getPacManLoc()[1], 0);
        assertFalse(g.getPlaying());
    }
    
    @Test
    public void courtRegularMove() {
        JLabel label = new JLabel("Running...");
        JLabel score = new JLabel("Your Score: " + 0);
        GameCourt g = new GameCourt(label, score);
        g.reset();
        g.poisonSetLocation(19, 5);
        g.manSetLocation(0, 15);
        g.changePManDirection(Direction.DOWN);
        g.tick();
        g.tick();
        assertEquals(g.getPoison1Loc()[0], 19);
        assertEquals(g.getPoison1Loc()[1], 5);
        assertEquals(g.getPacManLoc()[0], 0);
        assertEquals(g.getPacManLoc()[1], 17);
        assertTrue(g.getPlaying());
    }
    
    @Test
    public void courtCollisionCornerTest() {
        JLabel label = new JLabel("Running...");
        JLabel score = new JLabel("Your Score: " + 0);
        GameCourt g = new GameCourt(label, score);
        g.reset();
        g.poisonSetLocation(17, 19);
        g.manSetLocation(19, 17);
        g.changePManDirection(Direction.DOWN);
        g.setGhostDirection(Direction.RIGHT);
        g.tick();
        g.tick();
        assertEquals(g.getPoison1Loc()[0], 19);
        assertEquals(g.getPoison1Loc()[1], 19);
        assertEquals(g.getPacManLoc()[0], 19);
        assertEquals(g.getPacManLoc()[1], 19);
        assertFalse(g.getPlaying());
    }
    
    
    

}
