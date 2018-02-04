package beans;

public class Ship {

	private int ship_id; //primary key
	private String name;
	private boolean isApproved;
	private float fuel_level;
	
	public Ship() {
		super();
	}
	public Ship(int ship_id) {
		super();
		this.ship_id = ship_id;
	}
	public Ship(int ship_id, String name, boolean isApproved, float fuel_level) {
		super();
		this.ship_id = ship_id;
		this.name = name;
		this.isApproved = isApproved;
		this.fuel_level = fuel_level;
	}
	public double getFuel_level() {
		return fuel_level;
	}
	public void setFuel_level(float fuel_level) {
		this.fuel_level = fuel_level;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getShip_id() {
		return ship_id;
	}
	public void setShip_id(int ship_id) {
		this.ship_id = ship_id;
	}
	public boolean isApproved() {
		return isApproved;
	}
	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
	@Override
	public String toString() {
		return name + ": ship_id=" + ship_id +  ", isApproved=" + isApproved + ", fuel_level="
				+ fuel_level + "\n\r";
	}

	
	
}
