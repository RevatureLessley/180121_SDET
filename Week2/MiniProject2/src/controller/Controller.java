package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import beans.Pilot;
import beans.Ship;
import dao.MechanicMethods;
import dao.PilotDao;
import dao.PilotDaoImpl;
import dao.ShipDao;
import dao.ShipDaoImpl;


/*
 * Key features:
 * Users can login with password and change bank balance only after being approved by administrator
 * Data is persisted in a file
 * Prevents duplicate accounts from being created
 * Ensures each id is unique on creation
 * Admin cannot have banking account
 * Admin can login, authorize or delete users
 * 
 */
public class Controller {
	
	final static Logger logger = Logger.getLogger(Controller.class);
		
	public static void main(String[] args) {
		logger.debug("Application started");
		mainMenu();
	}
	
	
	public static void mainMenu() {
		Scanner scan = new Scanner(System.in);
		ShipDao shipDao = new ShipDaoImpl();
		PilotDao pilotDao = new PilotDaoImpl();
		while(true) {
			scan = new Scanner(System.in);
			System.out.println("\n=============================================");
			System.out.println("Welcome to the StarFleet Management Portal. \nYour base number is 12683 in the Andromeda galaxy");
			System.out.println("\nAll spaceships must be approved \nby a mechanic before \nbeing allowed to fly\n");
			System.out.println("1 to login as pilot and fly a ship");
			System.out.println("2 to buy a new spaceship");
			System.out.println("3 to display all ship data");
			System.out.println("4 to charge your electric spaceship");
			System.out.println("5 to create a pilot");
			System.out.println("6 for mechanic login");
			System.out.println("or \'q\'");
			String s = scan.nextLine();
			
			if (s.equals("1")) {
				logger.debug("Chose login");
				pilotDao.logInPilot(pilotDao.getAllPilots(), scan);
			}

			if (s.equals("2")) {
				logger.debug("Chose create");
				Ship ship = new Ship();
				System.out.println("Name: ");
				String input = scan.nextLine();
				ship.setName(input);
				System.out.println("Load it with some fuel (max 10,000): ");
				while(true) {
					if(scan.hasNextInt()) {
						Integer i = scan.nextInt();
						ship.setFuel_level(i);	
						break;
					}
					System.out.println("Enter a number ");
					scan.nextLine();
					
				}
		
				shipDao.addShip(ship, scan);
			}
			if (s.equals("3")) {
				logger.debug("Chose show");
				MechanicMethods.showAllData();
			}
			if (s.equals("4")) {
				logger.debug("Chose show");
				List<Ship> ships = shipDao.getAllShips();
				System.out.println(ships);
				System.out.println("Choose a ship by name to fuel");
				String input = scan.nextLine();
				Ship newShip;
				for (Ship ship : ships) {
					if (ship.getName().equals(input)) {
						System.out.println("Enter an amount");
						if(scan.hasNextInt())
							shipDao.addFuel(ship,scan.nextInt());
						else {
							System.out.println("That wasn't a number");						
						}
						break;
					}
				}

			}
			if (s.equals("5")) {
				logger.debug("Chose create pilot");
				PilotDao pDao = new PilotDaoImpl();
				List<Pilot> pilots = pDao.getAllPilots();
				System.out.println("Here are the current pilots\n" + pilots);
				Pilot p = new Pilot();
				System.out.println("Enter your name: ");
				String input = scan.nextLine();
				if(input.equals("mechanic") || input.equals("Mechanic")) {
					return;
				}
				p.setName(input);
				System.out.println("Enter your password: ");
				p.setPassword(scan.nextLine());

				List<Integer> ids = new ArrayList<>();
				for(Pilot pilot : pilots) {
					ids.add(pilot.getId());
				}
				int randomId = -1;
				while(true) {
					randomId = (int) (Math.random() * 10000);
					if(ids.contains(randomId) == false) {
						break;
					}
				}
				p.setId(randomId);
				pilotDao.createPilot(p, scan);
			}
			if (s.equals("6")) {
				logger.debug("Chose admin");
				MechanicMethods.mechanicLogin(scan);
			}
			if (s.equals("quit") || s.equals("q")) {
				logger.debug("Application closed");
				break;
			}

		}
		scan.close();		
	}
	
	public static void mechanicMenu(Scanner scan) {

		logger.debug("Accessed mechanicMenu");
		ShipDao shipDao = new ShipDaoImpl();
		
		String s = "";
		while(true) {
			System.out.println("Mechanic main menu:");
			System.out.println("0 to inspect a spaceship");
			System.out.println("1 to remove a spaceship when it needs repair");
			System.out.println("2 for main menu");
			s = scan.nextLine();		
			
			if (s.equals("0")) {
				logger.debug("accessing authenticate");
				List<Ship> ships = shipDao.getAllShips();
				for (Ship ship : ships) {
					if(ship.isApproved() == false) {
						System.out.println(ship.getName() + " Inspected: No");						
					}
					if(ship.isApproved()) {
						System.out.println(ship.getName() + " Inspected: Yes");						
					}
				}
				System.out.println("Choose a ship to inspect");
				String input = scan.nextLine();
				for (Ship ship : ships) {
					if (input.equals(ship.getName())) {
						shipDao.approveShip(ship);
						break;
					}
				}
				
			}
			
			if (s.equals("1")) {
				logger.debug("accessing delete");
				List<Ship> ships = shipDao.getAllShips();
				for (Ship ship : ships) {
					if(ship.isApproved() == false) {
						System.out.println(ship.getName() + " Inspected: No");						
					}
					if(ship.isApproved()) {
						System.out.println(ship.getName() + " Inspected: Yes");						
					}
				}
				System.out.println("Choose a ship to remove for inspection");
				String input = scan.nextLine();
				for (Ship ship : ships) {
					if (input.equals(ship.getName())) {
						shipDao.unapproveShip(ship);
						break;
					}
				}				
			}
			
			if (s.equals("2")) {
				logger.debug("quit mechanic menu");
				mainMenu();
				break;
			}
			
		}		
	}
	
}
