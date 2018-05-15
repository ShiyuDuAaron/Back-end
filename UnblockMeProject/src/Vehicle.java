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
	 * @param orientation horizontal or vertical direction the vehicle is
	 */
	public Vehicle(int id, int positionX, int positionY, int type) {
		this.id = id;
		this.type = type;
		this.positionX = positionX;
		this.positionY = positionY;
		if (type == VERTICALCAR) {
			this.orientation = VERTICAL;
			this.length = 2;
		} else if (type == VERTICALTRUCK) {
			this.orientation = VERTICAL;
			this.length = 3;
		} else if (type == HORIZONTALCAR || type == REDCAR) {
			this.orientation = HORIZONTAL;
			this.length = 2;
		} else if (type == HORIZONTALTRUCK) {
			this.orientation = HORIZONTAL;
			this.length = 3;
		} 
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
	
	public int getOrientation() {
		return orientation;
	}
	
	public void setOrientation(int orientation) {
		this.orientation = orientation;
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
		if (this.orientation == HORIZONTAL) {
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
		else if (this.orientation == VERTICAL) {
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


	private int length;
	private int id;
	private int positionX;
	private int positionY;
	private int orientation;
	private int type;
	public static final int EMPTY = 0;
	public static final int VERTICAL = 1;
	public static final int HORIZONTAL = 2;
	public static final int RIGHT = 3;
	public static final int LEFT = 4;
	public static final int UP = 5;
	public static final int DOWN = 6;
	/* 
	 * constants for types
	 * type: red car    -> 0
	 * 		 vertical2  -> 1
	 * 		 horizontal2-> 2
	 * 		 vertical3  -> 3
	 * 		 horizontal3-> 4
	 */
	public static final int REDCAR = 0;
	public static final int VERTICALCAR = 1;
	public static final int HORIZONTALCAR = 2;
	public static final int VERTICALTRUCK = 3;
	public static final int HORIZONTALTRUCK = 4;
	
}