package SAS.Beans;
import java.io.ByteArrayInputStream;
import java.sql.Date;

public class Claims{
	
	private int claimreid;
	private int claim_event_id;
	private int claim_cost;
	private int claim_projreimb;
	private int claim_w_id;
	private int grade_formid;
	private int claim_grade;
	private int claim_passgrade;
	private String claim_reason;
	private Date claim_date;
	private Date timenowis;
	private int claim_urgent;
	private int approv_stat;
	private int claim_approved;
	private ByteArrayInputStream blob;
	private String [] messages;

	
	
	public Claims(int claim_event_id, int claim_cost, int claim_projreimb, int claim_w_id,
			int grade_formid, int claim_grade, int claim_passgrade, String claim_reason, Date claim_date,
			Date timenowis, int claim_urgent, int claim_approved, ByteArrayInputStream blob, String [] messages) {
		super();
		this.claim_event_id = claim_event_id;
		this.claim_cost = claim_cost;
		this.claim_projreimb = claim_projreimb;
		this.claim_w_id = claim_w_id;
		this.grade_formid = grade_formid;
		this.claim_grade = claim_grade;
		this.claim_passgrade = claim_passgrade;
		this.claim_reason = claim_reason;
		this.claim_date = claim_date;
		this.timenowis = timenowis;
		this.claim_urgent = claim_urgent;
		this.claim_approved = claim_approved;
		this.blob = blob;
		this.messages = messages;

	}
	
	public Claims(int claimreid, int claim_event_id, int claim_cost, int claim_projreimb, int claim_w_id,
			int grade_formid, int claim_grade, int claim_passgrade, String claim_reason, Date claim_date,
			Date timenowis, int claim_urgent, int approv_stat, int claim_approved) {
		super();
		this.claimreid = claimreid;
		this.claim_event_id = claim_event_id;
		this.claim_cost = claim_cost;
		this.claim_projreimb = claim_projreimb;
		this.claim_w_id = claim_w_id;
		this.grade_formid = grade_formid;
		this.claim_grade = claim_grade;
		this.claim_passgrade = claim_passgrade;
		this.claim_reason = claim_reason;
		this.claim_date = claim_date;
		this.timenowis = timenowis;
		this.claim_urgent = claim_urgent;
		this.approv_stat = approv_stat;
		this.claim_approved = claim_approved;
	}
	
	public Claims() {
		super();
	}
	public int getClaimreid() {
		return claimreid;
	}
	public void setClaimreid(int claimreid) {
		this.claimreid = claimreid;
	}
	public int getClaim_event_id() {
		return claim_event_id;
	}
	public void setClaim_event_id(int claim_event_id) {
		this.claim_event_id = claim_event_id;
	}
	public int getClaim_cost() {
		return claim_cost;
	}
	public void setClaim_cost(int claim_cost) {
		this.claim_cost = claim_cost;
	}
	public int getClaim_projreimb() {
		return claim_projreimb;
	}
	public void setClaim_projreimb(int claim_projreimb) {
		this.claim_projreimb = claim_projreimb;
	}
	public int getClaim_w_id() {
		return claim_w_id;
	}
	public void setClaim_w_id(int claim_w_id) {
		this.claim_w_id = claim_w_id;
	}
	public int getGrade_formid() {
		return grade_formid;
	}
	public void setGrade_formid(int grade_formid) {
		this.grade_formid = grade_formid;
	}
	public int getClaim_grade() {
		return claim_grade;
	}
	public void setClaim_grade(int claim_grade) {
		this.claim_grade = claim_grade;
	}
	public int getClaim_passgrade() {
		return claim_passgrade;
	}
	public void setClaim_passgrade(int claim_passgrade) {
		this.claim_passgrade = claim_passgrade;
	}
	public String getClaim_reason() {
		return claim_reason;
	}
	public void setClaim_reason(String claim_reason) {
		this.claim_reason = claim_reason;
	}
	public Date getClaim_date() {
		return claim_date;
	}
	public void setClaim_date(Date claim_date) {
		this.claim_date = claim_date;
	}
	public Date getTimenowis() {
		return timenowis;
	}
	public void setTimenowis(Date timenowis) {
		this.timenowis = timenowis;
	}
	public int getClaim_urgent() {
		return claim_urgent;
	}
	public void setClaim_urgent(int claim_urgent) {
		this.claim_urgent = claim_urgent;
	}
	public int getApprov_stat() {
		return approv_stat;
	}
	public void setApprov_stat(int approv_stat) {
		this.approv_stat = approv_stat;
	}
	public int getClaim_approved() {
		return claim_approved;
	}
	public void setClaim_approved(int claim_approved) {
		this.claim_approved = claim_approved;
	}
	public ByteArrayInputStream getBlob() {
		return blob;
	}

	public void setBlob(ByteArrayInputStream blob) {
		this.blob = blob;
	} 
	
	
	
}
