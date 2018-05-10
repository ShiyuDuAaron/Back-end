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
	
	public Boolean checkMove(Vehicle v, int moves) {
	
		if(v.getDirection() == Vehicle.Horizontal) {
			
			for(int i = 0; i < moves; i++) {
				if(v.getPosisionx()+i == v.getId() || v.getPosisionx()+i == 0) {
					continue;
				}
				else {
					return false;
				}
			}
		}
		else {
			for(int i = 0; i < moves; i++) {
				if(v.getPosisiony()+i == v.getId() || v.getPosisiony()+i == 0) {
					continue;
				}
				else {
					return false;
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
