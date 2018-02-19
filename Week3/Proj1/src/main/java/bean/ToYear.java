package bean;

public class ToYear {
	
	private String username;
	private int maximum;
	private int pending;
	private int awarded;
	
	public ToYear() {
		super();
	}
	public ToYear(String username, int maximum, int pending, int awarded) {
		super();
		this.username = username;
		this.maximum = maximum;
		this.pending = pending;
		this.awarded = awarded;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getMaximum() {
		return maximum;
	}
	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}
	public int getPending() {
		return pending;
	}
	public void setPending(int pending) {
		this.pending = pending;
	}
	public int getAwarded() {
		return awarded;
	}
	public void setAwarded(int awarded) {
		this.awarded = awarded;
	}
	
	

}
