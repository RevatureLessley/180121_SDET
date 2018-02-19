package dao;

import bean.ToYear;

public interface ToYearDao {
	public ToYear getRecord(String username);
	public int getMaxClaim(ToYear ty, int amount);
	
}
