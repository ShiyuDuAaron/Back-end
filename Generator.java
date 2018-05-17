import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Generator {

	private static final Random Ran = new Random();
	
	
	public ArrayList<Vehicle> generateRandomGame(GameEngine g, int minSteps, int size) throws CloneNotSupportedException{
		
		//construt a list of vehicles for the game
		ArrayList<Vehicle> Vehicles = new ArrayList<Vehicle>();
		
		//ArrayList<Move> path = new ArrayList<Move>();
		
		//add the red car
		Vehicles.add(new Vehicle(1,0,2,Vehicle.REDCAR));
		
		GameFrame newGame = new GameFrame();
		newGame.addVehicle(Vehicles.get(0));
		newGame.curBoard();
		
		int pathLength = 0;
		int type = (int)(Math.random()*4+2);
		int id = 2;
		
		while(pathLength < minSteps) {
			
			if(newGame.getVehicleList().size() < size){
				
				
				//System.out.println(id);
				
				int terms = 50;
				//keep try to add a Vehicle
				while(terms-- > 0) {
					
					Vehicle RandomVehicle = getRamdomVehicle(type,id);
					
					if(isValid(newGame,RandomVehicle)) {
						//add vehicle
						Vehicles.add(RandomVehicle);
						newGame.addVehicle(RandomVehicle);
						//update the Board
						newGame.curBoard();
						
						LinkedList<Move> paths = g.aStarSearch(newGame); 
					
						//if this game still can be solved
						if(paths != null && paths.size() < minSteps) {
							//increase the index
							id++;
							break;
						}
						else {
							Vehicles.remove(RandomVehicle);
							newGame.getVehicleList().remove(RandomVehicle);
							newGame.curBoard();
						}
					}
				}
				
				
			}
		}
			
		return Vehicles;	
	}


	/**
	 * 
	 * @param newGame
	 * @param randomVehicle
	 * @return
	 */
	
	
	private boolean isValid(GameFrame g, Vehicle v) {
		
		if(v.getType() == Vehicle.HORIZONTALCAR || v.getType() == Vehicle.HORIZONTALTRUCK) {
		
			
			for(int i = v.getPositionX(); i < v.getPositionX() + v.getLength(); i++) {
				//if the board is not empty
				
				if(i>=GameFrame.BOARDSIZE || g.getBoard()[v.getPositionY()][i]!=0) return false;
			
			}
		}
		else {
			
			for(int i = v.getPositionY(); i < v.getPositionY() + v.getLength(); i++) {
				
				//if the board is not empty
				if(i >= GameFrame.BOARDSIZE || g.getBoard()[i][v.getPositionX()]!=0) return false;
				
			}
		}
				
		return true;
	}


	/**
	 * 
	 * @param type
	 * @param id
	 * @return
	 */

	private Vehicle getRamdomVehicle(int type, int id) {
		
		int positionX = Ran.nextInt(5);
		int positionY = Ran.nextInt(5);
		
		//horizontal car can not in red car row;
		if((type == Vehicle.HORIZONTALCAR || type == Vehicle.HORIZONTALTRUCK) && positionX == 2) {
			positionX ++;
		}
		
		return new Vehicle(id,positionX,positionY,type);
	}
	
	
}
