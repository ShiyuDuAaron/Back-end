import java.util.ArrayList;


public class Vehicle {

	/**
	 * constructor
	 * @param length
	 * @param id
	 * @param posisionx
	 * @param posisiony
	 * @param direction
	 */
	public Vehicle(int length, int id, int posisionx, int posisiony, int direction,int type) {
		super();
		this.length = length;
		Id = id;
		this.type = type;
		this.posisionx = posisionx;
		this.posisiony = posisiony;
		this.direction = direction;
	}
	
	
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getPosisionx() {
		return posisionx;
	}
	public void setPosisionx(int posisionx) {
		this.posisionx = posisionx;
	}
	public int getPosisiony() {
		return posisiony;
	}
	public void setPosisiony(int posisiony) {
		this.posisiony = posisiony;
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}

	/**
	 * move the vehicle;
	 * @param direction the direction vehicle need to move;
	 * @param moves the numbers of move;
	 */
	public void move(int directions, int moves) {
		if(this.direction == Horizontal) {
			if(directions != LEFT && directions != RIGHT) {
				System.out.println("Invalid direction");
				return;
			}
			else if(directions == LEFT){
				this.posisionx = this.posisionx - moves;
			}
			else {
				this.posisionx = this.posisionx + moves;
			}
		}
		else {
			if(directions != UP && directions != DOWN) {
				System.out.println("Invalid direction");
				return;
			}
			else if(directions == UP) {
				this.posisiony = this.posisiony - moves;
			}
			else {
				this.posisiony = this.posisiony + moves;
			}
		}		
	}
	
	/**
	 * print the current vehicle in the map 
	 */
	
	public void printVehlcle(int[][] Board) {
		//if it is horizontal
		if(this.direction == Horizontal) {
			for(int i = 0; i < this.length; i++) {
				if(Board[this.posisionx][this.posisiony+i] == 0) {
					Board[this.posisionx+i][this.posisiony] = this.Id;
				}
				else {
					System.out.println("Invalid vehicle");
					break;
				}
			}
		}
		//vertical
		else {
			for(int j = 0; j < this.length; j++) {
				if(Board[this.posisionx][this.posisiony+j] == 0) {
					Board[this.posisionx][this.posisiony+j] = this.Id;
				}
				else {
					System.out.println("Invalid vehicle");
					break;
				}
			}
		}		
	}
	
	
	/**
	 * 
	 * @param Board
	 * @param vehicle
	 * @return
	 */
	
	public int checkValid(ArrayList<Vehicle> List, int moves) {
		
		return 0;	
	}
	
	
	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	private int length;
	private int Id;
	private int posisionx;
	private int posisiony;
	private int direction;
	private int type;
	public static final int Horizontal = 1;
	public static final int Vertical = 2;
	public static final int RIGHT = 3;
	public static final int LEFT = 4;
	public static final int UP = 5;
	public static final int DOWN = 6;
}
