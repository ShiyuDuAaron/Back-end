import java.util.Stack;


public class Game {
	
	
	public Game() {
		this.stack = new Stack<Move>();
		this.gameframe = new GameFrame();
	}
	
	/**
	 * 
	 */
	
	public void undo() {

	}
	
	/**
	 * 
	 */
	
	public void ScoreCaculate() {
			
	}
	
	public void MoveVehicle(Vehicle v, int directions, int moves) {
		
		if(v.isValidMove(gameframe.getBoard(), directions, moves)) {
			System.out.println("Invalid move");
			return;
		}
		//gameframe.getVehicleList().remove(v);
		v.move(directions, moves);
		//gameframe.getVehicleList().add(v);
		
		//System.out.println(gameframe.getVehicleList().size());
		gameframe.iniBoard();
		for(Vehicle newV: gameframe.getVehicleList()) {
			newV.printVehicle(gameframe.getBoard());
		}
		gameframe.PrintBoard();	
	}
	
	
	
	
	
	public void addMove(Move m) {
		this.stack.add(m);
	}

	
	
	public int getScore() {
		return score;
	}
	
	public GameFrame getGameframe() {
		return gameframe;
	}
	
	public void setGameframe(GameFrame gameframe) {
		this.gameframe = gameframe;
	}


	private Stack<Move> stack;
	private int score;
	private GameFrame gameframe;
}
