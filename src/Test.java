
public class Test {

	public static void main(String[ ] args) {
		
		GameFrame g = new GameFrame();
		Vehicle v = new Vehicle(2,1,0,0,2,Vehicle.Horizontal);
		
		g.addVehicle(v);
		v.printVehlcle(g.getBoard());	
		g.PrintBoard();
		
		System.out.println("\n");	
		
		v.move(Vehicle.RIGHT, 2);
		g.iniBoard();
		v.printVehlcle(g.getBoard());
		g.PrintBoard();
		
		
	}
	
}
