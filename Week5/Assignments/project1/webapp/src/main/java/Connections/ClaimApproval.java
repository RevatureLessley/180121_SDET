package Connections;

public class ClaimApproval {
	private int claim_approval_id;
	private int claim_approval_claim_id;
	private int claim_approval_employee_id;
	private int direct_supervisor_approval;
	private String direct_supervisor_message;
	private int department_head_approval;
	private String department_head_message;
	private int benefit_coordinator_approval;
	private String benefit_coordinator_message;
	
	
	
	public int getClaim_approval_employee_id() {
		return claim_approval_employee_id;
	}


	public void setClaim_approval_employee_id(int claim_approval_employee_id) {
		this.claim_approval_employee_id = claim_approval_employee_id;
	}


	public int getClaim_approval_id() {
		return claim_approval_id;
	}


	public void setClaim_approval_id(int claim_approval_id) {
		this.claim_approval_id = claim_approval_id;
	}


	public int getClaim_approval_claim_id() {
		return claim_approval_claim_id;
	}


	public void setClaim_approval_claim_id(int claim_approval_claim_id) {
		this.claim_approval_claim_id = claim_approval_claim_id;
	}


	public int getDirect_supervisor_approval() {
		return direct_supervisor_approval;
	}


	public void setDirect_supervisor_approval(int direct_supervisor_approval) {
		this.direct_supervisor_approval = direct_supervisor_approval;
	}


	public String getDirect_supervisor_message() {
		return direct_supervisor_message;
	}


	public void setDirect_supervisor_message(String direct_supervisor_message) {
		this.direct_supervisor_message = direct_supervisor_message;
	}


	public int getDepartment_head_approval() {
		return department_head_approval;
	}


	public void setDepartment_head_approval(int department_head_approval) {
		this.department_head_approval = department_head_approval;
	}


	public String getDepartment_head_message() {
		return department_head_message;
	}


	public void setDepartment_head_message(String department_head_message) {
		this.department_head_message = department_head_message;
	}


	public int getBenefit_coordinator_approval() {
		return benefit_coordinator_approval;
	}


	public void setBenefit_coordinator_approval(int benefit_coordinator_approval) {
		this.benefit_coordinator_approval = benefit_coordinator_approval;
	}


	public String getBenefit_coordinator_message() {
		return benefit_coordinator_message;
	}


	public void setBenefit_coordinator_message(String benefit_coordinator_message) {
		this.benefit_coordinator_message = benefit_coordinator_message;
	}


	public ClaimApproval(int claim_approval_id, int claim_approval_claim_id, int direct_supervisor_approval,
			String direct_supervisor_message, int department_head_approval, String department_head_message,
			int benefit_coordinator_approval, String benefit_coordinator_message) {
		super();
		this.claim_approval_id = claim_approval_id;
		this.claim_approval_claim_id = claim_approval_claim_id;
		this.direct_supervisor_approval = direct_supervisor_approval;
		this.direct_supervisor_message = direct_supervisor_message;
		this.department_head_approval = department_head_approval;
		this.department_head_message = department_head_message;
		this.benefit_coordinator_approval = benefit_coordinator_approval;
		this.benefit_coordinator_message = benefit_coordinator_message;
	}
	
	
}
