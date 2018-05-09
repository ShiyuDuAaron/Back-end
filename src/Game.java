import java.util.Stack;


public class Game {
	
	
	public Game(Stack<Move> stack, int score, GameFrame gameframe) {
		this.stack = new Stack<Move>();
		this.score = score;
		this.gameframe = gameframe;
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
