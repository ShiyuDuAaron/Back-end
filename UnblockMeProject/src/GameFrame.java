import java.util.ArrayList;

public class GameFrame implements Comparable<GameFrame> {
	
	/**
	 * 
	 * @param vehicleList a list of vehicles in current frame
	 * @param Board the board of curretn frame
	 */
	public GameFrame() {
		VehicleList = new ArrayList<Vehicle>();
		Board = new int[BOARDSIZE][BOARDSIZE];
	
	}
	
	public ArrayList<Vehicle> getVehicleList() {
		return VehicleList;
	}

	public void setVehicleList(ArrayList<Vehicle> vehicleList) {
		VehicleList = vehicleList;
	}
	
	public int[][] getBoard() {
		return Board;
	}

	public void setBoard(int[][] board) {
		Board = board;
	}

	public void addVehicle(Vehicle v) {
		VehicleList.add(v);
	}
	
	/**
	 * initialize the board
	 */
	public void initialBoard(){
		for(int i = 0;i < BOARDSIZE; i++) {
			for(int j = 0;j < BOARDSIZE;j++) {
				Board[j][i] = Vehicle.EMPTY;
			}
		}
	}
	
	/**
	 * implement the current vehicles into Board
	 */
	public void curBoard() {
		int i;
		this.initialBoard();
		for (Vehicle v : VehicleList) {
			if (v.getOrientation() == Vehicle.HORIZONTAL) {
				for (i = v.getPositionX(); i < v.getPositionX() + v.getLength(); i++) {
					Board[i][v.getPositionY()] = v.getId();
				}
			} if (v.getOrientation() == Vehicle.VERTICAL) {
				for (i = v.getPositionY(); i < v.getPositionY() + v.getLength(); i++) {
					Board[v.getPositionX()][i] = v.getId();
				}
			}
		}
	}
	
	/**
	 * print the board as a matrix
	 */
	public void printBoard() {
		for(int y = 0; y < BOARDSIZE; y++) {
			for(int x = 0; x < BOARDSIZE; x++) {
				System.out.print(Board[x][y]);
			}
			System.out.println();
		}
	}
	
	/**
	 * 
	 * @param Board
	 * @param vehicle
	 * @return
	 */
	// checked
	public boolean isValidMove(Vehicle v, int direction, int moves) {
		int i;
		this.curBoard();
		// check for horizontal direction
		if (v.getOrientation() == Vehicle.HORIZONTAL) {
			if (direction == Vehicle.LEFT) {
				// check if it is moving out of the board
				System.out.println("move small hor");
				if (v.getPositionX() - moves < 0) return false;
				for (i = v.getPositionX() - moves; i < v.getPositionX(); i++) {
					if (Board[i][v.getPositionY()] != Vehicle.EMPTY && Board[i][v.getPositionY()] != v.getId()) {
						System.out.println("have car left");
						return false;
					}
				}
			} else if (direction == Vehicle.RIGHT){
				System.out.println("move big hor");
				if (v.getPositionX() + moves + v.getLength() > BOARDSIZE-1) return false;
				//System.out.println("HHHHHHH");
				for (i = v.getPositionX() + moves + v.getLength(); i > v.getPositionX(); i--) {
					System.out.println("v.pos x "+ v.getPositionX());
					System.out.println("v.pos y "+ v.getPositionY());
					System.out.println("bord val "+ Board[i][v.getPositionY()]);
					if (Board[i][v.getPositionY()] != Vehicle.EMPTY && Board[i][v.getPositionY()] != v.getId()) {
						System.out.println("HAVE CAR RIGHT");
						return false;
					}
				}
			} else {
				System.out.println("Invalid direction");
				return false;
			}
		} 
		// check for vertical direction
		else if (v.getOrientation() == Vehicle.VERTICAL) {
			if (direction == Vehicle.UP) {
				System.out.print("mvoe small");
				if (v.getPositionY() - moves < 0) return false;
				for (i = v.getPositionY() - moves; i < v.getPositionY(); i++) {
					if (Board[v.getPositionX()][i] != Vehicle.EMPTY && Board[v.getPositionX()][i] != v.getId()) {
						System.out.print("have car down");
						return false;
					}
				}
			} else if (direction == Vehicle.DOWN){
				System.out.println("move big");
				if (v.getPositionY() + moves + v.getLength() > BOARDSIZE - 1) return false;
				System.out.println("v.pox y = "+ v.getPositionY() + " move + "+moves);
				for (i = v.getPositionY() + moves + v.getLength(); i > v.getPositionY(); i--) {
					if (Board[v.getPositionX()][i] != Vehicle.EMPTY && Board[v.getPositionX()][i] != v.getId()) {
						System.out.print("have car up");
						return false;
					}
				}
			} else {
				System.out.println("Invalid direction");
				return false;
			}
		}
		return true;	
	}


	@Override
	public int compareTo(GameFrame o) {
		int ret = 0;
		int hasObstacle = 0;
		for (Vehicle v : o.getVehicleList()) {
			if (v.getType() == Vehicle.REDCAR) {
				ret = BOARDSIZE - v.getPositionX();
			} else if (v.getType() == Vehicle.VERTICALTRUCK) {
				for (int j = v.getPositionY() + v.getLength(); j < BOARDSIZE - 1; j++ ) {
					if (Board[v.getPositionX()][j] != Vehicle.EMPTY) {
						hasObstacle = 1;
					}
				}
				if (hasObstacle == 1) {
					ret = ret + 20;
				}
			}
		}
		
		return ret;
	}




	private ArrayList<Vehicle> VehicleList;
	private int[][] Board;
	public static final int BOARDSIZE = 6;
}