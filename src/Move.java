
public class Move {

	/**
	 * constructor for the Move
	 * @param vehlcle
	 * @param direction
	 * @param noOfMove
	 */
	
	public Move(Vehicle vehlcle, int direction, int noOfMove) {
		super();
		this.vehlcle = vehlcle;
		this.direction = direction;
		NoOfMove = noOfMove;
	}
	
	public Vehicle getVehlcle() {
		return vehlcle;
	}
	public void setVehlcle(Vehicle vehlcle) {
		this.vehlcle = vehlcle;
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public int getNoOfMove() {
		return NoOfMove;
	}
	public void setNoOfMove(int noOfMove) {
		NoOfMove = noOfMove;
	}

	private Vehicle vehlcle;
	private int direction;
	private int NoOfMove;
}
