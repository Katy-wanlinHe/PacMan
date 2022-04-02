
public abstract class MovableObj extends GameObj {
    private Direction dir;
    
    public MovableObj(int locX, int locY, int step, int width, int height,
            int courtWidth, int courtHeight) {
        super(locX, locY, step, width, height, courtWidth, courtHeight);
        dir = null;
    }
    
    /*Getter*/
    public Direction getDir() {
        return dir;
    }
    
    /*Setter*/
    public void setDir(Direction dir) {
        this.dir = dir;
    }
    
    /**
     * Moves the object by one step in the designated direction. Ensures that
     * the object does not go outside its bounds by clipping.
     */
    public void move(Maze m) {
        if (!hitWall(m) && this.dir != null) {
            switch (this.dir) {
                case UP :
                    super.setlocY(super.getlocY() - 1);
                    break;
                case DOWN :
                    super.setlocY(super.getlocY() + 1);
                    break;
                case LEFT :
                    super.setlocX(super.getlocX() - 1);
                    break;
                case RIGHT :
                    super.setlocX(super.getlocX() + 1);
                    break;
                default :
                    break;
            }

        }
        super.clip();
    }
    
    /**
     * gets the locX and locY value of the object after the next move()
     * 
     * @return an int[] representing the location of the GameObj in its next
     *         step, where nextLoc[0] represents locX, and nextLoc[1] represent
     *         locY
     */
    public int[] nextLoc() {
        int thisX = -1;
        int thisY = -1;
        if (this.getDir() == Direction.RIGHT) {
            thisX = super.getlocX() + 1;
            thisY = super.getlocY();
        } else if (this.getDir() == Direction.LEFT) {
            thisX = super.getlocX() - 1;
            thisY = super.getlocY();
        } else if (this.getDir() == Direction.UP) {
            thisX = super.getlocX();
            thisY = super.getlocY() - 1;
        } else if (this.getDir() == Direction.DOWN) {
            thisX = super.getlocX();
            thisY = super.getlocY() + 1;
        }

        int[] result = {thisX, thisY};
        return result;
    }

    /**
     * Determine whether this game object will intersect another in the next
     * time step, assuming that both objects continue with their current
     * velocity.
     * 
     * Intersection is determined by comparing the location of the objection in
     * the int[][] map. If the location overlaps, then an intersection is
     * considered to occur.
     * 
     * @param The other object
     * @return Whether an intersection will occur.
     */
    public boolean willIntersect(MovableObj that) {
        return this.nextLoc().equals(that.nextLoc());
    }
    
    /**
     * Determine whether this game object is currently intersecting another
     * object.
     * 
     * Intersection is determined by comparing bounding boxes. If the bounding
     * boxes overlap, then an intersection is considered to occur.
     * 
     * @param The other object
     * @param the maze which the objects are in
     * @return Whether this object intersects the other object.
     */

    public boolean intersects(GameObj that, Maze m) {
        if (this.getlocX() == that.getlocX() && this.getlocY() == that.getlocY()) {
            return true;
        } else if (this.nextLoc()[0] == that.getlocX()
                && this.nextLoc()[1] == that.getlocY()) {
            this.move(m);
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Check whether the given movable object would hit a wall or the edge of the game board in the 
     * next move
     */
    public Boolean hitWall(Maze m) {
        return ((this.nextLoc()[0] < 0 || this.nextLoc()[0] > this.getMaxX() / 20
                || this.nextLoc()[1] < 0 || this.nextLoc()[1] > this.getMaxY() / 20)
                || (m.getObjVal(this) == 0));
    }
}
