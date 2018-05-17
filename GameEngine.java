import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

// import javafx.util.Pair;

public class GameEngine {

	public static final int GOALPOSITION = 4;

	public GameEngine() {

	}

	/**
	 * next step from initial state marked as reachable, then find the shortest path to that reachable(dump the other paths)
	 * take the newly marked reachable and figure out which states are reachable from those, till reach the goal state
	 * @param g
	 * @return linked list of moves to get to goal state from current frame, 
	 *         ////////store in reverse order (ie. first element is the last move)///////
	 */
	public LinkedList<Move> aStarSearch(GameFrame g) throws CloneNotSupportedException {

		PriorityQueue<GameFrame> frameQueue = new PriorityQueue<GameFrame>();  // priority queue for storing the frames
		frameQueue.add(g);

		int i;
		int j;

		Map<GameFrame, Move> prevMoves = new HashMap<GameFrame, Move>();  // store the list of previous moves to get to current state
		// put a fake move as the start of the previous move so that we can know that it reaches the start when we traverse back later
		//Vehicle fakeV = new Vehicle(-1, 0, 0, -1);
		prevMoves.put(g, null);  
		Set<GameFrame> visited = new HashSet<GameFrame>();  // store the frames that have already been visited

		int k = 0;
		// loop while we have a queue
		while (frameQueue.size() > 0 && k < 10) {
			k++;
			GameFrame curFrame = frameQueue.poll();
			System.out.println("queue size now: " + frameQueue.size());
			// check if at goal state
			GoalCheckLoop:
				for (Vehicle v : curFrame.getVehicleList()) {
					if (v.getType() == Vehicle.REDCAR) {
						for (i = v.getPositionX() + v.getLength(); i < GameFrame.BOARDSIZE; i++) {
							if (curFrame.getBoard()[i][v.getPositionY()] != Vehicle.EMPTY) {
								// have obstacles in front of red car, so not the goal state yet
								break GoalCheckLoop;
							}
						}
						// have no obstacles in front of red car, so at the goal state
						System.out.println("solved");
						curFrame.printBoard();
						LinkedList<Move> solution = new LinkedList<Move>();
						Move curMove = prevMoves.get(curFrame);
						solution.addFirst(curMove);
						while (curMove != null) {
							curFrame.printBoard();
							for (Vehicle tempV : curFrame.getVehicleList()) {
								if (tempV.getId() == curMove.getVehlcle().getId()) {
									if (curMove.getDirection() == Vehicle.UP) {
										tempV.setPositionY(tempV.getPositionY() + curMove.getNoOfMove());
										System.out.println("curMove vehicle = " + tempV.getId()+ " up"+" numofmove = "+curMove.getNoOfMove());
										break;
									} else if (curMove.getDirection() == Vehicle.DOWN) {
										tempV.setPositionY(tempV.getPositionY() - curMove.getNoOfMove());
										System.out.println("curMove vehicle = " + tempV.getId()+ " down"+" numofmove = "+curMove.getNoOfMove()+ " posy = "+tempV.getPositionY());
										break;
									} else if (curMove.getDirection() == Vehicle.RIGHT) {
										tempV.setPositionY(tempV.getPositionX() - curMove.getNoOfMove());
										System.out.println("curMove vehicle = " + tempV.getId()+ " right");
										break;
									} else if (curMove.getDirection() == Vehicle.LEFT) {
										tempV.setPositionY(tempV.getPositionX() + curMove.getNoOfMove());
										System.out.println("curMove vehicle = " + tempV.getId()+ " left");
										break;
									}
								}
							}
							curFrame.curBoard();
							curFrame.printBoard();
							curMove = prevMoves.get(curFrame);
							solution.addFirst(curMove);
						}
						return solution;
					}
				}

			// check if we have visited this state or not
			if (visited.contains(curFrame)) {
				System.out.println("duplicates here");
				continue;
			}
			// if not visited yet, put into the visited set
			visited.add(curFrame);

			// check each empty grid and check if and vehicle can reach that grid for the next move,
			// if can reach, store it as new state
			// -------- cloning ---------
//			GameFrame tempFrame = new GameFrame();
//			tempFrame.setVehicleList(curFrame.getVehicleList());
//			tempFrame.setBoard(curFrame.getBoard());
			// ----------------------------
			GameFrame tempFrame ;//= (GameFrame)curFrame.clone();
			for (i = 0; i < GameFrame.BOARDSIZE; i ++) {
				for (j = 0; j < GameFrame.BOARDSIZE; j ++) {
					if (curFrame.getBoard()[i][j] == Vehicle.EMPTY) {
						System.out.println("current empty grid is: X = "+i+", Y = "+j);
						for (Vehicle v : curFrame.getVehicleList()) {
							System.out.println("vehicle id: " + v.getId());
							if (v.getPositionY() == j && v.getOrientation() == Vehicle.HORIZONTAL) {
								// move vehicle to left
								if (v.getPositionX() > i) {
									if (curFrame.isValidMove(v, Vehicle.LEFT, v.getPositionX() - i)) {
										System.out.println("be4 no.5 pos X ::::" + curFrame.getVehicleList().get(4).getPositionX());
										System.out.println("be4 no.5 pos Y ::::" + curFrame.getVehicleList().get(4).getPositionY());
										System.out.println("curFrame1 ::::");
										curFrame.printBoard();
										tempFrame = (GameFrame)curFrame.clone();
										int p = tempFrame.indexOfVehicle(v);
										assert(p != -1);
										System.out.println("aft no.5 pos X ::::" + curFrame.getVehicleList().get(4).getPositionX());
										System.out.println("aft no.5 pos Y ::::" + curFrame.getVehicleList().get(4).getPositionY());
										tempFrame.getVehicleList().get(p).setPositionX(i);  // set the new position of vehicle
										tempFrame.curBoard();  // set the new board
										frameQueue.add(tempFrame);  // add the new frame to queue
										prevMoves.put(tempFrame, new Move(v, Vehicle.LEFT, v.getPositionX() - i));  // store this move
										//curFrame = tempFrame;
										
										System.out.println("curFrame ::::");
										curFrame.printBoard();
										System.out.println("tempFrame ::::");
										tempFrame.printBoard();
									}
									// move vehicle to right
								} else if (v.getPositionX() < i) {
									
									System.out.println("curFrame0 ::::");
									curFrame.printBoard();
									System.out.println("no.5 pos X ::::" + curFrame.getVehicleList().get(4).getPositionX());
									System.out.println("no.5 pos Y ::::" + curFrame.getVehicleList().get(4).getPositionY());
									if (curFrame.isValidMove(v, Vehicle.RIGHT, i - v.getLength() - v.getPositionX() + 1)) {
										System.out.println("v old position X: " + v.getPositionX());
										//v.setPositionX(i - v.getLength() + 1);
										System.out.println("curFrame1 ::::");
										curFrame.printBoard();
										tempFrame = (GameFrame)curFrame.clone();
										System.out.println("tempFrame1 ::::");
										tempFrame.printBoard();
										int p = tempFrame.indexOfVehicle(v);
										assert(p != -1);
										tempFrame.getVehicleList().get(p).setPositionX(i - v.getLength() + 1);
										System.out.println("v new position X: " + v.getPositionX());
//										curFrame.curBoard();
//										curFrame.printBoard();
//										frameQueue.add(curFrame);
//										prevMoves.put(curFrame, new Move(v, Vehicle.RIGHT, i - v.getLength()));
//										curFrame = tempFrame;
										tempFrame.curBoard();
										frameQueue.add(tempFrame); 
										prevMoves.put(tempFrame, new Move(v, Vehicle.RIGHT, i - v.getLength() - v.getPositionX() + 1)); 
										System.out.println("curFrame ::::");
										curFrame.printBoard();
										System.out.println("tempFrame ::::");
										tempFrame.printBoard();
									}
								} 
								//GameFrame.BOARDSIZE - v.getPositionX() - 1
							} else if (v.getPositionX() == i && v.getOrientation() == Vehicle.VERTICAL) {
								// move vehicle to up
								if (v.getPositionY() > j) {
									if (curFrame.isValidMove(v, Vehicle.UP, v.getPositionY() - j)) {
//										v.setPositionY(j);
//										curFrame.curBoard();
//										frameQueue.add(curFrame);
//										prevMoves.put(curFrame, new Move(v, Vehicle.UP, v.getPositionY() - j));
//										curFrame = tempFrame;
										tempFrame = (GameFrame)curFrame.clone();
										int p = tempFrame.indexOfVehicle(v);
										assert(p != -1);
										tempFrame.getVehicleList().get(p).setPositionY(j);
										tempFrame.curBoard();
										frameQueue.add(tempFrame); 
										prevMoves.put(tempFrame, new Move(v, Vehicle.UP, v.getPositionY() - j));
										System.out.println("curFrame ::::");
										curFrame.printBoard();
										System.out.println("tempFrame ::::");
										tempFrame.printBoard();
									}
									// move vehicle to down
								} else if (v.getPositionY() < j) {
									if (curFrame.isValidMove(v, Vehicle.DOWN, j - v.getLength() - v.getPositionY() + 1)) {
//										v.setPositionY(j - v.getLength() + 1);
//										curFrame.curBoard();
//										frameQueue.add(curFrame);
//										prevMoves.put(curFrame, new Move(v, Vehicle.DOWN, j - v.getLength()));
//										curFrame.printBoard();
//										System.out.println("before == tempFrame");
//										curFrame = tempFrame;
//										curFrame.printBoard();
										tempFrame = (GameFrame)curFrame.clone();
										int p = tempFrame.indexOfVehicle(v);
										assert(p != -1);
										tempFrame.getVehicleList().get(p).setPositionY(j - v.getLength() + 1);
										tempFrame.curBoard();
										frameQueue.add(tempFrame); 
										prevMoves.put(tempFrame, new Move(v, Vehicle.DOWN, j - v.getLength() - v.getPositionY() + 1));
										System.out.println("curFrame ::::");
										curFrame.printBoard();
										System.out.println("tempFrame ::::");
										tempFrame.printBoard();
									}
								}
							}
						}
					}
				}
			}
		}
		return null;  // no solution found
	}
}