package junit;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import beans.Pilot;
import beans.Ship;
import dao.PilotDao;
import dao.PilotDaoImpl;
import dao.ShipDao;
import dao.ShipDaoImpl;


public class MiniProjectTestClass {
	
	static Scanner scan;
	static Connection conn;
	static Pilot p;
	
	final static Logger logger = Logger.getLogger(MiniProjectTestClass.class);
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	@Ignore
	public void testInsertPilot() {
		Pilot p = new Pilot("Connor Mcgregor", 34567, "connor");
		PilotDao pDao = new PilotDaoImpl();
		pDao.createPilot(p, scan);
		List<Pilot> pilots = pDao.getAllPilots();
		assertEquals(true, pilots.contains(p));
	}
	
	@Test
	public void testAddFuel() {
		ShipDao sDao = new ShipDaoImpl();
		List<Ship> ships = sDao.getAllShips();
		Ship ship = null;
		for(Ship testShip : ships) {
			if(testShip.getFuel_level() < 9000) {
				ship = testShip;
				break;
			}
		}
		double fuel_level = ship.getFuel_level();
		assertEquals(true, (fuel_level + 300) == sDao.addFuel(ship, 300));
	}
	
	@Test
	public void testjunit() {
		assertEquals(true, true);
	}
	

}
