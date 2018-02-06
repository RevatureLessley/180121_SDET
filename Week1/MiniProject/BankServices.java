package com.miniprojectbankingsystem.services;

import java.util.ArrayList;

import javax.security.auth.login.AccountNotFoundException;

import com.miniproject.bankingsystem.exceptions.CustomerNotFoundException;
import com.miniproject.bankingsystem.exceptions.InsufficientBalanceException;
import com.miniproject.bankingsystem.exceptions.InvalidAccountNoException;
import com.miniproject.bankingsystem.exceptions.InvalidAccountTypeException;
import com.miniproject.bankingsystem.exceptions.InvalidAmountException;
import com.miniproject.bankingsystem.exceptions.InvalidCustomerIdException;
import com.miniproject.bankingsystem.exceptions.InvalidPincodeException;
import com.miniprojectbankingsystem.beans.Account;
import com.miniprojectbankingsystem.beans.Customer;
import com.miniprojectbankingsystem.beans.Transaction;

public interface BankServices {
	int acceptCustomerDetails(String custName, String HomeAddressCity,
			String HomeAddressState, int HomeAddressPincode,
			String LocalAddressCity, String LocalAddressState,
			int LocalAddressPincode) throws InvalidPincodeException;

	int openAccount(int custId, int balance, String accType)
			throws InvalidCustomerIdException, CustomerNotFoundException,
			InvalidAmountException, InvalidAccountTypeException;

	int getAccountBalance(int custId, int accNo)
			throws InvalidCustomerIdException, CustomerNotFoundException,
			InvalidAccountNoException, AccountNotFoundException;

	int withdraw(int custId, int accNo, int amt)
			throws InvalidCustomerIdException, CustomerNotFoundException,
			InvalidAccountNoException, AccountNotFoundException,
			InvalidAmountException, InsufficientBalanceException;

	boolean fundTransfer(int custIdFrom, int accNoFrom, int custIdTo,
			int accNoTo, int amt) throws InvalidCustomerIdException,
			CustomerNotFoundException, InvalidAccountNoException,
			AccountNotFoundException, InvalidAmountException,
			InsufficientBalanceException;

	int deposit(int custId, int accNo, int amt)
			throws InvalidCustomerIdException, CustomerNotFoundException,
			InvalidAccountNoException, AccountNotFoundException,
			InvalidAmountException, InsufficientBalanceException;

	Customer getCustomerDetails(int custId) throws InvalidCustomerIdException,
			CustomerNotFoundException;

	Account getAccountDetails(int custId, int accNo)
			throws InvalidCustomerIdException, CustomerNotFoundException,
			InvalidAccountNoException, AccountNotFoundException;

	ArrayList<Account> getAllAccountsDetails(int custId)
			throws InvalidCustomerIdException, CustomerNotFoundException;

	ArrayList<Transaction> getAllTransactionDetails(int custId, int accNo)
			throws InvalidCustomerIdException, CustomerNotFoundException,
			InvalidAccountNoException, AccountNotFoundException;
}