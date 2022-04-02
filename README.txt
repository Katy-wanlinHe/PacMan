===================
=: Core Concepts :=
===================

- Core concepts, the features they implement:

  1. 2D-Array: One key component of my game is the maze where there are regions your PacMan could 
  enter and regions where it could not. By creating an 2D int array initially just of 1 and 0 and 
  linking that to a 2D array of GameObj where the 1 represents a Dot and 0 represents a Wall, I was
  able to connect the underlying data structure with the display of the GameCourt. When the PacMan 
  intersects with a given Dot, the value of that dot in the array would be set to -1 so that it 
  would not be recounted in the score when the object passes through that given area again. In this 
  way I could easily check whether the game needs to proceed and gets the instantaneous score of the 
  game after each move. 

  2. Inheritance/Subtyping: Pac Man is a game that includes many elements and each elements has 
  their own functionality. To make my code effective and readable, I incorporate multiple lays of 
  subtyping in the design of the game. In this game, I creates an abstract class called GameObj 
  which includes all the elements you could see on the GameCourt. Two abstract classes extends the 
  GameObj class - StationaryObj and MovableObj. The MovableObj class includes the move method and a 
  field called direction that allow the object to move in a given step inside a specific maze. 
  The MovableObj class also includes methods that checks the interactions between the given 
  MovableObj and other GameObj like the intersects and hitWall methods. Another abstract class 
  called StationaryObj which also extends the GameObj class. Different from the movable elements 
  where the step for each move is set to a specific value, the step value for a stationary object 
  is locked and could not be changed.

  3. Testable Components: In a maze chase game like Pac Man, interactions between different objects 
  might be complicated and the internal states of the program might be hard to observe from simply
  the display of the GameCourt. Therefore, I add many testing methods in the GameBoard class like
  getPoison1Loc(), getPoison2Loc(), getManLoc(), getScore(), getPlaying() to check for the internal 
  state of the game. I also created set methods in the GameCourt class to put the Ghost and the 
  PacMan in different edge conditions to check for their functionalities in extreme cases like 
  objects intersecting on corner, objects intersecting on edge of game board, moving PacMan into 
  a wall or the edge of the GameBoard etc. 

  4. Recursion: Recursion is employed in the Pac Man game to allow the two ghosts move in a 
  automated but random manner. Due to the facts that all elements are moving inside a maze,
  if I only asks the ghosts to turn in a random direction after a given time interval, there is a 
  good chance that it would turn into a wall or into the edge of the frame or evening bumping into 
  the other ghost object. To avoid such awkward situation from happening, I made the call to the 
  ramdomTurn() method recursive so that when the ramdomTurn Method is called the ghost would first
  turn in a random direction and if that direction if not valid, the direction would be removed from
  the list and based on the updated list another randomTurn() is called again. However, if a valid 
  turn is made, the list is reset to its initial state. Because the invalid direction is removed 
  each time, the ghost object could always make a valid turn within four passes through the method. 
  
=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  Direction: this class holds the four directions UP, DOWN, RIGHT, and LEFT. These four directions
  are later used in the GameObject and Game class. These four directions limit the input from the 
  keyboards and determines the way which the pacMan would move inside the maze
  
  Dot - the tokens that would be collected and disappear if the pacMan intersects with the Dot
  object. The class extends the StationaryObj class.
  
  Game - the place where the game is run. The display of the game window, the keyPressListeners, 
  reset, revive and tick functions as well as the timer are set in that class. It is basically the 
  place where everything comes together. 
  
  GameCourt - the GameCourt class is where each individual game is setup, the pacMan, Maze and the
  two ghosts are constructed in the GameCourt class. The interactions of the game and how the game 
  is being played is also defined in the class. In addition, the class includes many testing methods
  for me to check the interactions between objects, especially that between movable objects and the 
  Maze.
  
  GameObj - an abstract class that is the super class for all the individual elements of the game. 
  The main setter helps to create each individual object; the class also includes different setters
  and getters that helps to update the values of different fields.
  
  Ghost - the class that extends the movableObj class and sets up the thing that chases you in the 
  game. It takes in a pictures as its display and has its own setter and draw methods. The class 
  also contains methods unique only to ghosts like reborn and randomTurn which allows ghosts to move 
  in a random manner in the game.
  
  Maze - the class where all the dots are walls are set up in a designated way to form a maze which
  the pacMan and the ghost has to traverse. The design of the game is based on a 2D int array of 
  numbers, the number map is also updated as the game progresses. 
  
  MovableObj - this class provides all the methods that would use to manipulate the movements of the 
  movable objects including ghosts and Pac Man as the well as the interactions between movable and
  stationary objects. 
  
  PacMan - the object that is controlled by the user in the game. The object takes in an image as
  display in the game and has its own set and draw methods. 
  
  StationaryObj - an abstract class that inherits the GameObj class and is the super class for Dot 
  and Wall. The class locks the step value for the GameObj to 20 so that the size and location could
  not change.
  
  Wall - the object that designates the area where the pacMan and the ghosts could not cross in the 
  game. The object is represented as the int 0 in the maze and extends the StationaryObj class. 

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  One major stumbling blocks I encountered while designing the game is making sure that the objects 
  in the game follows the logic of the map. Initially, I set the location of each object based on 
  their location on the map and dividing the location information by the step to get the nearest or 
  intersecting gameObject. However, I ran into a lot of edge cases. For instance, a part of the 
  pacMan is inside a wall but more than half is not. I later realized that if I make each key press
  corresponds to a change in direction and checks the location of a object based on its location in
  the 2D int array rather than the game canvas I could avoid most of these buggy cases. 

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
  Overall, I believe I did a pretty good job in the design of the game and in the separation of 
  functionality. The MushroomDoom files provides me with a pretty good outline of the things I need 
  to consider in a timer-based game like Pac Man. By keeping key fields like score, map, gameMap 
  and different individual instances of gameObj private, I also believe that my program works well 
  in terms of private state encapsulation. If I am given a chance to redo my code, I would consider 
  add more interesting components to my game like storing a highest score or adding animation that
  would make the visual representation of the game board more interesting. 

========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.
  files/ghost2.png source:
  https://www.hiclipart.com/free-transparent-background-png-clipart-ibppr
  
  files/Pacman.png source:
  https://www.pinclipart.com/pindetail/iToxbim_pac-man-transparent-background-clipart/
  
  
