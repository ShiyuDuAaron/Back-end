/**
 * Store information of each vehicle in the Gridlock
 * @author user
 *
 */
public class Vehicle {

	/**
	 * constructor: create a new vehicle
	 * @param length the length of vehicle
	 * @param id the unique id of the vehicle
	 * @param positionX the X-coordinate of the first grid of the vehicle
	 * @param positionY the Y-coordinate of the first grid of the vehicle
	 * @param direction horizontal or vertical direction the vehicle is
	 */
	public Vehicle(int length, int id, int positionX, int positionY, int direction, int type) {
		super();
		this.length = length;
		this.id = id;
		this.type = type;
		this.positionX = positionX;
		this.positionY = positionY;
		this.direction = direction;
	}
	
	
	public int getLength() {
		return length;
	}
	
	public void setLength(int length) {
		this.length = length;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getPositionX() {
		return positionX;
	}
	
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}
	
	public int getPositionY() {
		return positionY;
	}
	
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
	
	public int getDirection() {
		return direction;
	}
	
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * move the vehicle to a valid position;
	 * @param directions the direction vehicle need to move (up, down, right and left);
	 * @param moves the number of grids that the vehicle has to move
	 */
	// checked (because i check the horizontal direction in isValidMove(), so we don't have to check again here)
	public void move(int directions, int moves) {
		if (directions == LEFT){
			this.positionX = this.positionX - moves;
		}
		else if (directions == RIGHT) {
			this.positionX = this.positionX + moves;
		} 
		else if (directions == UP) {
			this.positionY = this.positionY - moves;
		}
		else if (directions == DOWN) {
			this.positionY = this.positionY + moves;
		} 	
	}

	/**
	 * print the current vehicle in the map 
	 */
	// checked
	public void printVehicle(int[][] Board) {
		// if it is horizontal
		if (this.direction == HORIZONTAL) {
			for(int i = 0; i < this.length; i++) {
				if(Board[this.positionX+i][this.positionY] == 0) {
					Board[this.positionX+i][this.positionY] = this.id;
				}
				else {
					System.out.println("Invalid vehicle");
					break;
				}
			}
		}
		// vertical
		else if (this.direction == VERTICAL) {
			for(int j = 0; j < this.length; j++) {
				if(Board[this.positionX][this.positionY+j] == 0) {
					Board[this.positionX][this.positionY+j] = this.id;
				}
				else {
					System.out.println("Invalid vehicle");
					break;
				}
			}
		} else {
			System.out.println("Invalid Direction");
		}
	}
	
	
	/**
	 * 
	 * @param Board
	 * @param vehicle
	 * @return
	 */
	// checked
	public boolean isValidMove(int[][] Board, int directions, int moves) {
		int i;
		// check for horizontal direction
		if (this.direction == HORIZONTAL) {
			if (directions == LEFT) {
				if (this.positionX - moves < 0) return false;
				for (i = this.positionX - moves; i < this.positionX; i++) {
					if (Board[i][this.positionY] != EMPTY) {
						return false;
					}
				}
			} else if (directions == RIGHT){
				if (this.positionX + moves > Board.length - 1) return false;
				for (i = this.positionX + moves; i > this.positionX; i--) {
					if (Board[i][this.positionY] != EMPTY) {
						return false;
					}
				}
			} else {
				System.out.println("Invalid direction");
				return false;
			}
		} 
		// check for vertical direction
		else if (this.direction == VERTICAL) {
			if (directions == UP) {
				if (this.positionY - moves < 0) return false;
				for (i = this.positionY - moves; i < this.positionY; i++) {
					if (Board[this.positionX][i] != EMPTY) {
						return false;
					}
				}
			} else if (directions == DOWN){
				if (this.positionY + moves > Board[0].length - 1) return false;
				for (i = this.positionY + moves; i > this.positionY; i--) {
					if (Board[this.positionX][i] != EMPTY) {
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


	private int length;
	private int id;
	private int positionX;
	private int positionY;
	private int direction;
	private int type;
	public static final int EMPTY = 0;
	public static final int HORIZONTAL = 1;
	public static final int VERTICAL = 2;
	public static final int RIGHT = 3;
	public static final int LEFT = 4;
	public static final int UP = 5;
	public static final int DOWN = 6;
	// constants for types
	public static final int REDCAR = 1;
	public static final int CAR = 2;
	public static final int TRUCK = 3;
}