import java.util.ArrayList;

public class GameFrame {
	
	/**
	 * 
	 * @param vehicleList
	 * @param board
	 */
	
	public GameFrame() {
		VehicleList = new ArrayList<Vehicle>();
		setBoard(new int[6][6]);
	
	}
	
	public ArrayList<Vehicle> getVehicleList() {
		return VehicleList;
	}

	public void setVehicleList(ArrayList<Vehicle> vehicleList) {
		VehicleList = vehicleList;
	}

	public void addVehicle(Vehicle v) {
		VehicleList.add(v);
	}
	
	public void iniBoard(){
		for(int i = 0;i < 6; i++) {
			for(int j = 0;j < 6;j++) {
				Board[j][i] = 0;
			}
		}
		
	}
	
	public void PrintBoard() {
		for(int y = 0; y < 6; y++) {
			for(int x = 0; x < 6; x++) {
				System.out.print(this.getBoard()[x][y]);
			}
			System.out.println();
		}
		
		
	}
	
	public Boolean checkMove(Vehicle v,int directions,int moves) {
	
		if(v.getDirection() == Vehicle.Horizontal) {
			
			if(directions == Vehicle.LEFT) {
				for(int i = 0; i < moves; i++) {
					
				}
			
					
			}
		}
		
		
		return true;	
	}
	
	
	
	public int[][] getBoard() {
		return Board;
	}

	public void setBoard(int[][] board) {
		Board = board;
	}



	private ArrayList<Vehicle> VehicleList;
	private int[][] Board;
}
