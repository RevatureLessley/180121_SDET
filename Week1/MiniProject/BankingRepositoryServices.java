package com.miniprojectbankingsystem.dao;

import java.util.ArrayList;

import com.miniprojectbankingsystem.beans.Account;
import com.miniprojectbankingsystem.beans.Customer;
import com.miniprojectbankingsystem.beans.Transaction;

public interface BankingRepositoryServices {
	int insertCustomer(Customer customer);

	boolean updateCustomer(Customer customer);

	boolean updateTransaction(int custId, int accNo, Transaction transaction);

	Account getAccount(int custId, int accNo);

	ArrayList<Account> getAccountList(int custId);

	Customer getCustomer(int custId);

	ArrayList<Transaction> getTransactions(int custId, int accNo);

	boolean deleteCustomer(int custId);

	boolean deleteAccount(int custId, int accNo);

	int insertAccount(Account account, int custId);

	boolean updateAccount(int custId, Account account);

}