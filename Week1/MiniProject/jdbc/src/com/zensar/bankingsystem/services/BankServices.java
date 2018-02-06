package com.zensar.bankingsystem.services;

import java.util.ArrayList;

import javax.security.auth.login.AccountNotFoundException;

import com.zensar.bankingsystem.beans.Account;
import com.zensar.bankingsystem.beans.Customer;
import com.zensar.bankingsystem.beans.Transaction;
import com.zensar.bankingsystem.exceptions.CustomerNotFoundException;
import com.zensar.bankingsystem.exceptions.InsufficientBalanceException;
import com.zensar.bankingsystem.exceptions.InvalidAccountNoException;
import com.zensar.bankingsystem.exceptions.InvalidAccountTypeException;
import com.zensar.bankingsystem.exceptions.InvalidAmountException;
import com.zensar.bankingsystem.exceptions.InvalidCustomerIdException;
import com.zensar.bankingsystem.exceptions.InvalidPincodeException;

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