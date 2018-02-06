package com.zensar.bankingsystem.services;

import java.util.ArrayList;

import javax.security.auth.login.AccountNotFoundException;

import com.zensar.bankingsystem.beans.Account;
import com.zensar.bankingsystem.beans.Address;
import com.zensar.bankingsystem.beans.Customer;
import com.zensar.bankingsystem.beans.Transaction;
import com.zensar.bankingsystem.dao.BankingRepositoryServices;
import com.zensar.bankingsystem.exceptions.CustomerNotFoundException;
import com.zensar.bankingsystem.exceptions.InsufficientBalanceException;
import com.zensar.bankingsystem.exceptions.InvalidAccountNoException;
import com.zensar.bankingsystem.exceptions.InvalidAccountTypeException;
import com.zensar.bankingsystem.exceptions.InvalidAmountException;
import com.zensar.bankingsystem.exceptions.InvalidCustomerIdException;
import com.zensar.bankingsystem.exceptions.InvalidPincodeException;
import com.zensar.bankingsystem.repoprovider.RepositoryProvider;

public class ZensarBankingServiceImpl implements BankServices {

	BankingRepositoryServices zensarBankingSystemArray;

	public ZensarBankingServiceImpl() {
		zensarBankingSystemArray = RepositoryProvider.getProvider();

	}

	@Override
	public int acceptCustomerDetails(String custName, String HomeAddressCity,
			String HomeAddressState, int HomeAddressPincode,
			String LocalAddressCity, String LocalAddressState,
			int LocalAddressPincode) throws InvalidPincodeException {

		if (Integer.toString(LocalAddressPincode).length() != 5
				|| Integer.toString(HomeAddressPincode).length() != 5) {
			throw new InvalidPincodeException();
		} else {
			return zensarBankingSystemArray.insertCustomer(new Customer(
					custName, new Address(LocalAddressCity, LocalAddressState,
							LocalAddressPincode), new Address(HomeAddressCity,
							HomeAddressState, HomeAddressPincode)));
		}

	}

	@Override
	public int openAccount(int custId, int balance, String accType)
			throws InvalidCustomerIdException, CustomerNotFoundException,
			InvalidAmountException, InvalidAccountTypeException {

		int accountId = 0;

		if (custId < 99) {
			throw new InvalidCustomerIdException();
		} else if (!(accType.equalsIgnoreCase("savings")
				|| accType.equalsIgnoreCase("current") || accType
					.equalsIgnoreCase("salary"))) {
			throw new InvalidAccountTypeException();
		} else if (balance < 0) {
			throw new InvalidAmountException();
		} else {
			Customer customer = zensarBankingSystemArray.getCustomer(custId);

			if (customer == null) {
				throw new CustomerNotFoundException();
			} else {
				Account account = new Account(accType, balance);
				accountId = zensarBankingSystemArray.insertAccount(account,
						custId);
			}
		}

		return accountId;

	}

	@Override
	public int getAccountBalance(int custId, int accNo)
			throws InvalidCustomerIdException, CustomerNotFoundException,
			InvalidAccountNoException, AccountNotFoundException {

		if (custId < 99) {
			throw new InvalidCustomerIdException();
		} else if (accNo < 50) {
			throw new InvalidAccountNoException();
		} else {
			Customer customer = zensarBankingSystemArray.getCustomer(custId);
			if (customer == null) {
				throw new CustomerNotFoundException();
			} else if (zensarBankingSystemArray.getAccount(custId, accNo) == null) {
				throw new AccountNotFoundException("Account Not found");
			} else {
				return zensarBankingSystemArray.getAccount(custId, accNo)
						.getBalance();
			}
		}

	}

	@Override
	public int withdraw(int custId, int accNo, int amt)
			throws InvalidCustomerIdException, CustomerNotFoundException,
			InvalidAccountNoException, AccountNotFoundException,
			InvalidAmountException, InsufficientBalanceException {
		int balance = 0;
		if (custId < 99) {
			throw new InvalidCustomerIdException();
		} else if (accNo < 50) {
			throw new InvalidAccountNoException();
		} else if (amt < 0) {
			throw new InvalidAmountException();
		} else {
			Customer customer = zensarBankingSystemArray.getCustomer(custId);
			if (customer == null) {
				throw new CustomerNotFoundException();
			} else {
				Account account = zensarBankingSystemArray.getAccount(custId,
						accNo);
				if (account == null) {
					throw new AccountNotFoundException("Account Not found");
				} else {

					System.out.println(account);
					if ((account.getBalance() - amt) > 0) {
						account.setBalance(account.getBalance() - amt);
						balance = account.getBalance();
						zensarBankingSystemArray.updateAccount(custId, account);

						// transaction
						Transaction transaction = new Transaction();
						transaction.setTransactionType("withdraw");
						transaction.setTransactionAmount(amt);
						zensarBankingSystemArray.updateTransaction(custId,
								accNo, transaction);
					} else {
						throw new InsufficientBalanceException();
					}
				}
			}
		}
		return balance;
	}

	@Override
	public boolean fundTransfer(int custIdFrom, int accNoFrom, int custIdTo,
			int accNoTo, int amt) throws AccountNotFoundException,
			InvalidCustomerIdException, CustomerNotFoundException,
			InvalidAccountNoException, InvalidAmountException,
			InsufficientBalanceException {

		if (withdraw(custIdFrom, accNoFrom, amt) > 0) {
			deposit(custIdTo, accNoTo, amt);
			return true;
		}
		return false;
	}

	@Override
	public int deposit(int custId, int accNo, int amt)
			throws InvalidCustomerIdException, CustomerNotFoundException,
			InvalidAccountNoException, AccountNotFoundException,
			InvalidAmountException, InsufficientBalanceException {

		if (custId < 99) {
			throw new InvalidCustomerIdException();
		} else if (accNo < 50) {
			throw new InvalidAccountNoException();
		} else if (amt < 0) {
			throw new InvalidAmountException();
		} else {

			Customer customer = zensarBankingSystemArray.getCustomer(custId);
			if (customer == null) {
				throw new CustomerNotFoundException();
			} else {
				Account account = zensarBankingSystemArray.getAccount(custId,
						accNo);
				if (account == null) {
					throw new AccountNotFoundException("Account Not found");
				} else {
					System.out.println(account);
					account.setBalance(account.getBalance() + amt);
					zensarBankingSystemArray.updateAccount(custId, account);

					// transaction
					Transaction transaction = new Transaction();
					transaction.setTransactionType("deposit");
					transaction.setTransactionAmount(amt);
					zensarBankingSystemArray.updateTransaction(custId, accNo,
							transaction);

					return zensarBankingSystemArray.getAccount(custId, accNo)
							.getBalance();
				}
			}
		}
	}

	@Override
	public Customer getCustomerDetails(int custId) {
		Customer customer = zensarBankingSystemArray.getCustomer(custId);
		if (customer != null) {
			return customer;
		}
		return null;
	}

	@Override
	public Account getAccountDetails(int custId, int accNo)
			throws AccountNotFoundException, CustomerNotFoundException {

		if (getCustomerDetails(custId) == null) {
			throw new CustomerNotFoundException();
		} else {
			Account account = zensarBankingSystemArray
					.getAccount(custId, accNo);
			if (account != null) {
				return account;
			} else {
				throw new AccountNotFoundException("Account Not Found");
			}
		}

	}

	@Override
	public ArrayList<Account> getAllAccountsDetails(int custId) {
		ArrayList<Account> account = zensarBankingSystemArray
				.getAccountList(custId);
		if (account != null) {
			return account;
		}
		return null;
	}

	@Override
	public ArrayList<Transaction> getAllTransactionDetails(int custId, int accNo) {
		return zensarBankingSystemArray.getTransactions(custId, accNo);

	}

}