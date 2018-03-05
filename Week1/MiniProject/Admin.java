package com.JawaunBank;
import java.util.*;

public class Admin extends Users {
	
	protected Admin(int id) {
		this.IDNUM = setIdnum(id);
	}
	protected Admin() {
		
	}
	
	protected Admin(String uname, String pword) {
		Admin adi = new Admin();
		adi.UNAME=uname;
		adi.PWORD=pword;
	}
	
	protected void approveAccts(HashMap<Integer, Account> map) { //approves disproved new accounts
		if (map.equals(null)) {
			System.out.println("insufficient accounts for approval");
		} else {
			for(HashMap.Entry<Integer, Account> entry : map.entrySet()){ //go through every entry in the account list map
				Account act = entry.getValue();
				if(act.isApproved() == true) {
					if(act.getBalance()>0) { //checks that acct has value
						act.approved = true; //if so you can be an approved member
					} else { //without a balance of >=1 you can't get approved
						System.out.println("insufficient balance for approval");
						System.out.println("please add money");
						//write approve() method
					}
				} else {
					break; //do nothing if approved
				}
		     }		
	}
	}
	
	protected void menuop() {
		System.out.println("Would you like to approve accounts or leave?");
		System.out.println("Press 1 to approve accounts");
		int i = ino.nextInt();
		if (i == 1) {
			approveAccts(this.BANK.accountList);
			System.out.println("All funded accounts have been approved");
		} else {
			this.BANK.initi();
		}
	}
}
