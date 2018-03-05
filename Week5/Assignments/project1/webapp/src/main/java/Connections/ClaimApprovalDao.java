package Connections;

public interface ClaimApprovalDao {
	public void insertClaimApproval(ClaimApproval claimApproval);
    public ClaimApproval getClaimApproval(Employee employee);
    public ClaimApproval getClaimApproval(int id);
    public boolean updateClaimApprovalById(ClaimApproval claimApproval);
}
