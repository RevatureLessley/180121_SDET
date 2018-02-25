package dao;

import java.sql.Date;
import java.util.List;

import bean.Reimbursement;

public interface ReimbursementDao {
	public void insertR(String username, String supername, Date submitDate, Date startDate, int isUrgent, int adjustedAmount, int event);
	public int isUrgent(String submitDate, String startDate);
	public List<Reimbursement> getR(String username, int level);
	public int getUniqueId();
	public Reimbursement getR(int id);
	public void approveR(int id, int level, String reason);
	public void rejectR(int id, int level, String reason);
	public void uploadFile(byte[] file, int id);
	public byte[] downloadFile(int id);
	public void addComment(String comment, int level, int id);
	public String getApprovalString(int num);
}
