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
	public Vehicle(int length, int id, int posisionx, int posisiony, int direction) {
		super();
		this.length = length;
		Id = id;
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
	public void move(String directions, int moves) {
		if(this.direction == 1) {
			if(!(directions.equals("left") || directions.equals("right"))) {
				System.out.println("Invalid direction");
				return;
			}
			else {
				this.posisionx = this.posisionx + moves;
			}
		}
		else {
			if(!(directions.equals("up") || directions.equals("down"))) {
				System.out.println("Invalid direction");
				return;
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
		if(this.direction == 1) {
			for(int i = 0; i < this.length; i++) {
				if(Board[this.posisionx+i][this.posisiony] == 0) {
					Board[this.posisionx + i][this.posisiony] = this.Id;
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
					Board[this.posisionx][this.posisiony + j] = this.Id;
				}
				else {
					System.out.println("Invalid vehicle");
					break;
				}
			}
		}		
	}
	
	
	private int length;
	private int Id;
	private int posisionx;
	private int posisiony;
	/**
	 * 1 is horizontal and 2 is vertical
	 */
	private int direction;
}
