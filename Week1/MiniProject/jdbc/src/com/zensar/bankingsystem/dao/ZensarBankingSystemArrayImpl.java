package com.zensar.bankingsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.zensar.bankingsystem.beans.Account;
import com.zensar.bankingsystem.beans.Customer;
import com.zensar.bankingsystem.beans.Transaction;
import com.zensar.bankingsystem.repoprovider.ConnectionProvider;

public class ZensarBankingSystemArrayImpl implements BankingRepositoryServices {

	static int custId = 100, accId = 50, tranId = 1;
	private Connection con = null;
	private PreparedStatement psmt = null;
	java.sql.Statement smt = null;
	ResultSet rs = null;
	int result = 0;
	int customerId = 0;

	public int count(String sql) {
		try {
			smt = con.createStatement();
			rs = smt.executeQuery(sql);
			rs.next();
			System.out.println(rs.getInt(1));
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public ZensarBankingSystemArrayImpl() {
		con = ConnectionProvider.getConnection();
	}

	@Override
	public int insertCustomer(Customer customer) {

		String customerTableQuery = "INSERT INTO miniproject2.CUSTOMER VALUES(miniproject2.CUSTID.NEXTVAL,?)";
		String addressTableQuery = "INSERT INTO miniproject2.ADDRESS VALUES(miniproject2.CUSTID.CURRVAL,?,?,?,?,?,?)";
		String customerIdQuery = "SELECT miniproject2.CUSTID.CURRVAL FROM DUAL";
		try {

			psmt = con.prepareStatement(customerTableQuery);
			psmt.setString(1, customer.getCustomerName());
			result = psmt.executeUpdate();

			psmt = con.prepareStatement(addressTableQuery);
			psmt.setString(1, customer.getLaddress().getCity());
			psmt.setString(2, customer.getLaddress().getState());
			psmt.setInt(3, customer.getLaddress().getPinCode());
			psmt.setString(4, customer.getHaddress().getCity());
			psmt.setString(5, customer.getHaddress().getState());
			psmt.setInt(6, customer.getHaddress().getPinCode());
			result = psmt.executeUpdate();

			if (result > 0) {
				customer.setCustomerId(count(customerIdQuery));
				return customer.getCustomerId();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public Customer getCustomer(int custId) {

		Customer customer = new Customer();
		customer.setCustomerId(custId);

		String sql = "SELECT * from miniproject2.CUSTOMER WHERE customerId=?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, custId);
			rs = psmt.executeQuery();

			if (rs.isBeforeFirst() == false) {
				return null;
			}
			while (rs.next()) {
				customer.setCustomerName(rs.getString(1));
			}
			return customer;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateTransaction(int custId, int accNo,
			Transaction transaction) {

		String sql = "INSERT INTO miniproject2.TRANSACTION VALUES(miniproject2.TRANID.NEXTVAL,?,?,?)";
		String transactionIdQuery = "SELECT miniproject2.CUSTID.CURRVAL FROM DUAL";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, accNo);
			psmt.setString(2, transaction.getTransactionType());
			psmt.setInt(3, transaction.getTransactionAmount());
			result = psmt.executeUpdate();
			if (result > 0) {
				transaction.setTransactionID(count(transactionIdQuery));
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Account getAccount(int custId, int accNo) {

		Account account = new Account();
		account.setAccountId(accNo);

		String sql = "SELECT * FROM miniproject2.ACCOUNT WHERE accountId=? AND customerId=?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, accNo);
			psmt.setInt(2, custId);
			rs = psmt.executeQuery();
			if (rs.isBeforeFirst() == false) {
				return null;
			}

			while (rs.next()) {
				account.setAccountType(rs.getString(3));
				account.setBalance(rs.getInt(4));
			}
			return account;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public ArrayList<Account> getAccountList(int custId) {

		Account account = null;
		ArrayList<Account> acc = new ArrayList<Account>();
		String sql = "SELECT * FROM miniproject2.ACCOUNT WHERE customerId=?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, custId);
			rs = psmt.executeQuery();
			if (rs.isBeforeFirst() == false) {
				return null;
			}
			while (rs.next()) {
				account = new Account();
				account.setAccountId(rs.getInt(1));
				account.setAccountType(rs.getString(3));
				account.setBalance(rs.getInt(4));
				acc.add(account);
			}
			return acc;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	@Override
	public ArrayList<Transaction> getTransactions(int custId, int accNo) {

		Transaction transaction = null;
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		String sql = "SELECT * FROM miniproject2.TRANSACTION WHERE accountId="
				+ "(SELECT accountId FROM miniproject2.ACCOUNT WHERE customerId=? AND accountId=?)";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, custId);
			psmt.setInt(2, accNo);
			rs = psmt.executeQuery();
			if (rs.isBeforeFirst() == false) {
				return null;
			}
			while (rs.next()) {
				transaction = new Transaction();
				transaction.setTransactionID(rs.getInt(1));
				transaction.setTransactionType(rs.getString(3));
				transaction.setTransactionAmount(rs.getInt(4));
				transactions.add(transaction);
			}
			return transactions;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return transactions;
	}

	@Override
	public boolean deleteCustomer(int custId) {
		String sql = "DELETE FROM miniproject2.CUSTOMER WHERE customerId=? CASCADE";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, custId);
			result = psmt.executeUpdate();
			if (result > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteAccount(int custId, int accNo) {
		String sql = "DELETE FROM miniproject2.ACCOUNT WHERE customerId=? AND accountId=?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, custId);
			psmt.setInt(2, accNo);
			result = psmt.executeUpdate();
			if (result > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int insertAccount(Account account, int custId) {

		String accountTableQuery = "INSERT INTO miniproject2.ACCOUNT VALUES(miniproject2.ACCID.NEXTVAL,?,?,?)";
		try {
			psmt = con.prepareStatement(accountTableQuery);
			// psmt.setInt(1, accId);
			psmt.setInt(1, custId);
			psmt.setString(2, account.getAccountType());
			psmt.setInt(3, account.getBalance());
			result = psmt.executeUpdate();
			if (result > 0) {
				return accId++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public boolean updateAccount(int custId, Account account) {
		String sql = "UPDATE miniproject2.ACCOUNT SET balance=? WHERE customerId=? AND accountId=?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, account.getBalance());
			psmt.setInt(2, custId);
			psmt.setInt(3, account.getAccountId());
			result = psmt.executeUpdate();
			if (result > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}