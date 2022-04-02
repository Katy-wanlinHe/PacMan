import java.awt.Graphics;

//the maze of the game
public class Maze {
    /* the design of the maze as an int[][] */
    private int[][] map = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1},
            {1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
            {1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1},
            {1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1},
            {1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1},
            {1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1},
            {1, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1},
            {1, 0, 1, 1, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1},
            {1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1},
            {1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1},
            {1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1},
            {1, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1},
            {1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1},
            {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1},
            {1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

    /* projection of the maze design as an 2D array of GameObj */
    private GameObj[][] gameMap;
    private int score;
    private int courtW;
    private int courtH;
    private int scoreTotal;

    /* Setter for the Maze */
    public Maze(int courtW, int courtH) {
        gameMap = new GameObj[20][20];
        this.courtW = courtW;
        this.courtH = courtH;
        int count = 0;
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                if (map[y][x] == 1) {
                    gameMap[y][x] = new Dot(y, x, courtW, courtH);
                    count++;
                } else {
                    gameMap[y][x] = new Wall(y, x, courtW, courtH);
                }
            }
        }
        score = 0;
        scoreTotal = count;
    }

    /*
     * GETTERS
     ********************************************************************************/
    public int getScore() {
        return score;
    }

    public GameObj getObj(int x, int y) {
        return gameMap[x][y];
    }

    public int getObjVal(MovableObj b) {
        int posY = b.nextLoc()[1];
        int posX = b.nextLoc()[0];

        return map[posY][posX];
    }
    
    public int getTotalScore() {
        return scoreTotal;
    }
    
    public int getCourtH() {
        return courtH;
    }
    
    public int getCourtW() {
        return courtW;
    }

    /*
     * RETRIVE and PAINT
     ************************************************************************/

    /* updates score and change maze setting */
    public void retrived(GameObj b) {
        if (map[b.getlocY()][b.getlocX()] == 1) {
            map[b.getlocY()][b.getlocX()] = -1;
            score++;
        }
    }

    /* draw the map graphics to screen */
    public void draw(Graphics g) {
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                if (map[y][x] != -1) {
                    gameMap[y][x].draw(g);
                }
            }
        }
    }

}
