package com.request;

public class RequestTR {

	private Integer RequestId;
	private Integer EmployeeId;
	private Integer SupervisorRef;
	private Integer DeptID;
	private String LastName;
	private String FirstName;
	private String Phone;
	private String Email;
	private Integer Amount_Requested;
	private Integer Balance_Available;
	private Integer Pending_Reimbursment;
	private String Event_Type;
	private String Event_Justification;
	private String Grading_Format;
	private Integer Passing_Grade_Provided;
	private Integer Supervisor_Approval;
	private Integer Department_Head_Approval;
	private Integer BenCo_Approval;
	
	
	public RequestTR(Integer requestId, Integer employeeId, Integer supervisorRef, Integer deptID, String lastName,
			String firstName, String phone, String email, Integer amount_Requested, Integer balance_Available,
			Integer pending_Reimbursment, String event_Type, String event_Justification, String grading_Format,
			Integer passing_Grade_Provided, Integer supervisor_Approval, Integer department_Head_Approval,
			Integer benCo_Approval) {
		
		super();
		RequestId = requestId;
		EmployeeId = employeeId;
		SupervisorRef = supervisorRef;
		DeptID = deptID;
		LastName = lastName;
		FirstName = firstName;
		Phone = phone;
		Email = email;
		Amount_Requested = amount_Requested;
		Balance_Available = balance_Available;
		Pending_Reimbursment = pending_Reimbursment;
		Event_Type = event_Type;
		Event_Justification = event_Justification;
		Grading_Format = grading_Format;
		Passing_Grade_Provided = passing_Grade_Provided;
		Supervisor_Approval = supervisor_Approval;
		Department_Head_Approval = department_Head_Approval;
		BenCo_Approval = benCo_Approval;
		
	}


	public RequestTR(Integer employeeId, Integer supervisorRef, String lastName, String firstName, String phone,
			String email, Integer amount_Requested, Integer balance_Available,
			String event_Type, String event_Justification, String grading_Format, Integer passing_Grade_Provided,
			Integer supervisor_Approval, Integer department_Head_Approval, Integer benCo_Approval) {
		
		super();
		EmployeeId = employeeId;
		SupervisorRef = supervisorRef;
		LastName = lastName;
		FirstName = firstName;
		Phone = phone;
		Email = email;
		Amount_Requested = amount_Requested;
		Balance_Available = balance_Available;
		Event_Type = event_Type;
		Event_Justification = event_Justification;
		Grading_Format = grading_Format;
		Passing_Grade_Provided = passing_Grade_Provided;
		Supervisor_Approval = supervisor_Approval;
		Department_Head_Approval = department_Head_Approval;
		BenCo_Approval = benCo_Approval;
	}
	
	
	public RequestTR(Integer requestId, Integer employeeId, String lastName, String firstName, String phone,
			String email, Integer amount_Requested, Integer balance_Available,
			String event_Type, String event_Justification, String grading_Format) {
		
		super();
		RequestId = requestId;
		EmployeeId = employeeId;
		LastName = lastName;
		FirstName = firstName;
		Phone = phone;
		Email = email;
		Amount_Requested = amount_Requested;
		Balance_Available = balance_Available;
		Event_Type = event_Type;
		Event_Justification = event_Justification;
		Grading_Format = grading_Format;
		 
	}
	
	

	public RequestTR(Integer requestId, Integer amount_Requested, String event_Type) {
		super();
		RequestId = requestId;
		Amount_Requested = amount_Requested;
		Event_Type = event_Type;
	}


	public Integer getRequestId() {
		return RequestId;
	}

	public void setRequestId(Integer requestId) {
		RequestId = requestId;
	}

	public Integer getEmployeeId() {
		return EmployeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		EmployeeId = employeeId;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public Integer getAmount_Requested() {
		return Amount_Requested;
	}

	public void setAmount_Requested(Integer amount_Requested) {
		Amount_Requested = amount_Requested;
	}

	public Integer getBalance_Available() {
		return Balance_Available;
	}

	public void setBalance_Available(Integer balance_Available) {
		Balance_Available = balance_Available;
	}

	public Integer getPending_Reimbursment() {
		return Pending_Reimbursment;
	}

	public void setPending_Reimbursment(Integer pending_Reimbursment) {
		Pending_Reimbursment = pending_Reimbursment;
	}

	public String getEvent_Type() {
		return Event_Type;
	}

	public void setEvent_Type(String event_Type) {
		Event_Type = event_Type;
	}

	public String getEvent_Justification() {
		return Event_Justification;
	}

	public void setEvent_Justification(String event_Justification) {
		Event_Justification = event_Justification;
	}

	public String getGrading_Format() {
		return Grading_Format;
	}

	public void setGrading_Format(String grading_Format) {
		Grading_Format = grading_Format;
	}

	public Integer getPassing_Grade_Provided() {
		return Passing_Grade_Provided;
	}

	public void setPassing_Grade_Provided(Integer passing_Grade_Provided) {
		Passing_Grade_Provided = passing_Grade_Provided;
	}

	public Integer getSupervisor_Approval() {
		return Supervisor_Approval;
	}

	public void setSupervisor_Approval(Integer supervisor_Approval) {
		Supervisor_Approval = supervisor_Approval;
	}

	public Integer getDepartment_Head_Approval() {
		return Department_Head_Approval;
	}

	public void setDepartment_Head_Approval(Integer department_Head_Approval) {
		Department_Head_Approval = department_Head_Approval;
	}

	public Integer getBenCo_Approval() {
		return BenCo_Approval;
	}

	public void setBenCo_Approval(Integer benCo_Approval) {
		BenCo_Approval = benCo_Approval;
	}
	
	public Integer getSupervisorRef() {
		return SupervisorRef;
	}


	public void setSupervisorRef(Integer supervisorRef) {
		SupervisorRef = supervisorRef;
	}


	public Integer getDeptID() {
		return DeptID;
	}


	public void setDeptID(Integer deptID) {
		DeptID = deptID;
	}
	
}
