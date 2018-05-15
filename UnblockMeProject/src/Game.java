/**
 * @author tony
 */
import java.util.ArrayList;

import javax.swing.JFrame;

public class Game extends JFrame{
	//private BufferedImage testImage;
	private ArrayList<Vehicle> vehicleList;
	//run a game
	public Game(String title, int width, int height,ArrayList<Vehicle> vehicleList) {
		//frame settings
		this.setSize(width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		//setup the Vehicle list
		this.vehicleList= vehicleList;
		
		
		
		

		//create game panel
		GamePanel  panel = new GamePanel();
		panel.init(vehicleList);
		panel.addMouseListener(panel);
		panel.addMouseMotionListener(panel);
		this.add(panel);

	}
	
	
	
	
	
	public static void main(String[] args) {
		ArrayList<Vehicle> vehicleList= new ArrayList<Vehicle>();
		vehicleList.add(new Vehicle(1, 0, 2, Vehicle.REDCAR));
		vehicleList.add(new Vehicle(2, 3, 0, Vehicle.VERTICALTRUCK));
		vehicleList.add(new Vehicle(3, 0, 3, Vehicle.VERTICALCAR));
		vehicleList.add(new Vehicle(4, 0, 5, Vehicle.HORIZONTALTRUCK));
		vehicleList.add(new Vehicle(5, 3, 3, Vehicle.HORIZONTALCAR));
		
		Game game = new Game("Unblock Me", 1000, 1000,vehicleList);
		//testImage = ImageLoader.loadImage("ubuntu-logo32.png");
	}

}