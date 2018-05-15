/**
 * @author tony
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements MouseListener, MouseMotionListener{

	
	public void init(ArrayList<Vehicle> vehicleList) {
		this.vehicleList = vehicleList;
	}
	
	
	private ArrayList<Vehicle> vehicleList;
	private int mouseX, mouseY, newMouseX, newMouseY;
	
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.LIGHT_GRAY);
		this.setBounds(200, 0, 600, 600);
		Image carImage = null;
		System.out.println("print game state");
		
		for(Vehicle curVehicle : vehicleList) {
			switch(curVehicle.getType()) {
			case 0 : carImage = new ImageIcon("type0.png").getImage();
					g.drawImage(carImage,curVehicle.getPositionX()*100,
					curVehicle.getPositionY()*100,null);
					break;
			case 1: carImage = new ImageIcon("type1.png").getImage();
					g.drawImage(carImage,curVehicle.getPositionX()*100,
					curVehicle.getPositionY()*100,null);
					break;
			case 2: carImage = new ImageIcon("type2.png").getImage();
			g.drawImage(carImage,curVehicle.getPositionX()*100,
			curVehicle.getPositionY()*100,null);
			break;
			
			case 3: carImage = new ImageIcon("type3.png").getImage();
			g.drawImage(carImage,curVehicle.getPositionX()*100,
			curVehicle.getPositionY()*100,null);
			break;
			
			case 4: carImage = new ImageIcon("type4.png").getImage();
			g.drawImage(carImage,curVehicle.getPositionX()*100,
			curVehicle.getPositionY()*100,null);
			break;
			
			}
			
			
			
		}
		
		


    }




	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		
		
		
		if (true) {
			mouseX=e.getX();
			mouseY=e.getY();
			System.out.println("Mouse Pressed at: (" + mouseX
				+ ", " + mouseY + ")");
			
			
			
			
			repaint();
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		
		newMouseX = e.getX();
		newMouseY = e.getY();
		System.out.println("Mouse Released at: (" + newMouseX
				+ ", " + newMouseY + ")");
		
		int pressGridX=0;
		int pressGridY=0;
		
		int releaseGridX=0;
		int releaseGridY=0;
		
		int moveNumber=0;
		
		Vehicle currVehicle = null;
		if(e.getButton()==MouseEvent.BUTTON1) {
			
			currVehicle = findVehicle(mouseX,mouseY);
			if(currVehicle!=null) {
				//the user press on a vehicle,find the release position
				pressGridX = toGrid(mouseX);
				pressGridY = toGrid(mouseY);
				
				releaseGridX = toGrid(newMouseX);
				releaseGridY = toGrid(newMouseY);
				
				
				GameFrame g = new GameFrame();
				g.setVehicleList(vehicleList);
				
				g.curBoard();
				g.printBoard();
				if(currVehicle.getOrientation() ==Vehicle.HORIZONTAL) {//horizontal car
					if(releaseGridY == pressGridY) {//horizontal moving
						if(releaseGridX > pressGridX) {//moving right
							moveNumber = releaseGridX - pressGridX;
							if (g.isValidMove(currVehicle, Vehicle.RIGHT, moveNumber)) {
								System.out.println("moving right");
								currVehicle.move(Vehicle.RIGHT, moveNumber);
							}
						}
						if(releaseGridX < pressGridX) {//moving left
							moveNumber = pressGridX - releaseGridX;
							if (g.isValidMove(currVehicle, Vehicle.LEFT, moveNumber)) {
								System.out.println("moving left");
								currVehicle.move(Vehicle.LEFT, moveNumber);
							}
						}
					}
				}else if(currVehicle.getOrientation()==Vehicle.VERTICAL){//vertical car
					if(releaseGridX == pressGridX) {//vertical moving
						if(releaseGridY > pressGridY) {//moving down
							moveNumber = releaseGridY - pressGridY;
							if (g.isValidMove(currVehicle, Vehicle.DOWN, moveNumber)) {
								System.out.println("moving down");
								currVehicle.move(Vehicle.DOWN, moveNumber);
							}
						}
						if(releaseGridY < pressGridY) {//moving up
							moveNumber = pressGridY - releaseGridY;
							if (g.isValidMove(currVehicle, Vehicle.UP, moveNumber)) {
								System.out.println("moving up");
								currVehicle.move(Vehicle.UP, moveNumber);
							}
						}
					}
				}
			}
		}
		repaint();
		for(Vehicle currV : vehicleList) {
			if(currV.getType()==0 && currV.getPositionX() >= 4&&currV.getPositionY()==2) {
				System.out.print("You win!!!!!!!!\n\n\n");
				break;
			}
		}
		
		
	}
	
	public Vehicle findVehicle(int x, int y) {
		int topleftx =0;//in grid
		int toplefty= 0;//in grid
		
		int rightbotx=0;//in pixel
		int rightboty=0;//in pixel
		
		
		for (Vehicle currVehicle : this.vehicleList) {
			topleftx = currVehicle.getPositionX()*100;
			toplefty = currVehicle.getPositionY()*100;
			
			//calculate the right bot pixel of the vehicle
			if(currVehicle.getOrientation()==Vehicle.HORIZONTAL) {
				//horizontal vehicle
				rightbotx = topleftx + currVehicle.getLength()*100;
				rightboty = toplefty + 100;
			}
			else if(currVehicle.getOrientation()==Vehicle.VERTICAL) {
				//horizontal vehicle
				rightbotx = topleftx + 100;
				rightboty = toplefty + currVehicle.getLength()*100;
			}
			else {
				System.out.println("Orientation is "+ currVehicle.getOrientation());
				System.out.println("Fuckckckckckckckc!!!!!");
			}
			if(x > topleftx && x < rightbotx && y > toplefty && y< rightboty) {
				return currVehicle;
			}
		}
		return null;
	}
	
	public int toGrid(int pixel) {
		return pixel/100;
		
	}
	

}






