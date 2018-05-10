
public class Test {

	public static void main(String[ ] args) {
		
		Game g = new Game();
		Vehicle v = new Vehicle(2,1,0,2,Vehicle.HORIZONTAL,Vehicle.REDCAR);
	
		g.getGameframe().addVehicle(v);
		v.printVehicle(g.getGameframe().getBoard());	
		g.getGameframe().PrintBoard();
		
		System.out.println("\n");	
		
		//System.out.println(g.getGameframe().getVehicleList().size());
		
		g.MoveVehicle(v, Vehicle.RIGHT, 2);
		
		System.out.println("\n");	
		
		//System.out.println(g.getGameframe().getVehicleList().size());
		//System.out.println(g.getGameframe().getVehicleList().get(0).getPosisionx());
		//System.out.println(g.getGameframe().getVehicleList().get(0).getPosisiony());
		
		g.MoveVehicle(v, Vehicle.RIGHT, 2);
	}
	
}
