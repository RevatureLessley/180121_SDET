package Connections;

public interface ClaimDao {
	public void insertClaim(Claim claim);
    public Claim getClaim(Employee employee);
    public Claim getClaim(int id);
    public boolean updateClaimById(Claim claim);
}
