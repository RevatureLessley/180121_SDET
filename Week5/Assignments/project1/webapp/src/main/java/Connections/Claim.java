package Connections;

import java.io.ByteArrayInputStream;

public class Claim {
	private int id;
	private int claim_employee;
	private int claim_grade_id;
	private String date_of_event;
	private int time_of_event;
	private String location;
	private String description;
	private float cost;
	private String event_type;
	private ByteArrayInputStream data;
	private int missed_days;
	private int urgent;
	
	
	public int getUrgent() {
		return urgent;
	}
	public void setUrgent(int urgent) {
		this.urgent = urgent;
	}
	public Claim(int urgent) {
		super();
		this.urgent = urgent;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getClaim_employee() {
		return claim_employee;
	}
	public void setClaim_employee(int claim_employee) {
		this.claim_employee = claim_employee;
	}
	public int getClaim_grade_id() {
		return claim_grade_id;
	}
	public void setClaim_grade_id(int claim_grade_id) {
		this.claim_grade_id = claim_grade_id;
	}
	public String getDate_of_event() {
		return date_of_event;
	}
	public void setDate_of_event(String date_of_event) {
		this.date_of_event = date_of_event;
	}
	public int getTime_of_event() {
		return time_of_event;
	}
	public void setTime_of_event(int time_of_event) {
		this.time_of_event = time_of_event;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public String getEvent_type() {
		return event_type;
	}
	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}
	public ByteArrayInputStream getData() {
		return data;
	}
	public void setData(ByteArrayInputStream data) {
		this.data = data;
	}
	public int getMissed_days() {
		return missed_days;
	}
	public void setMissed_days(int missed_days) {
		this.missed_days = missed_days;
	}
	public Claim(int id, int claim_employee, int claim_grade_id, String date_of_event, int time_of_event,
			String location, String description, float cost, String event_type, ByteArrayInputStream data,
			int missed_days) {
		this.id = id;
		this.claim_employee = claim_employee;
		this.claim_grade_id = claim_grade_id;
		this.date_of_event = date_of_event;
		this.time_of_event = time_of_event;
		this.location = location;
		this.description = description;
		this.cost = cost;
		this.event_type = event_type;
		this.data = new ByteArrayInputStream(null);
		this.missed_days = missed_days;
	}
	public Claim(int claim_employee, int claim_grade_id, String date_of_event, int time_of_event, String location,
			String description, float cost, String event_type, ByteArrayInputStream data, int missed_days) {
		this.claim_employee = claim_employee;
		this.claim_grade_id = claim_grade_id;
		this.date_of_event = date_of_event;
		this.time_of_event = time_of_event;
		this.location = location;
		this.description = description;
		this.cost = cost;
		this.event_type = event_type;
		this.data = new ByteArrayInputStream(null);
		this.missed_days = missed_days;
	}
	
	
}
