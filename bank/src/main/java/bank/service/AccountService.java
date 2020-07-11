package bank.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import bank.dao.AccountDAO;
import bank.dao.IAccountDAO;
import bank.domain.Account;
import bank.domain.Customer;

public class AccountService implements IAccountService {
	private IAccountDAO accountDAO;
	private List<AccountObserver> accountObservers;

	public AccountService() {
		accountDAO = new AccountDAO();
		accountObservers = new ArrayList<>();
		
		accountObservers.add(new Logger());
		accountObservers.add(new SMSSender());
		accountObservers.add(new EmailSender());
	}

	public Account createAccount(long accountNumber, String customerName) {
		Account account = new Account(accountNumber);
		Customer customer = new Customer(customerName);
		account.setCustomer(customer);
		accountDAO.saveAccount(account);
		notifyAccountCreated(account);
		
		return account;
	}

	public void deposit(long accountNumber, double amount) {
		Account account = accountDAO.loadAccount(accountNumber);
		account.deposit(amount);
		accountDAO.updateAccount(account);
		notifyAccountChange(account);
	}
	
	private void notifyAccountCreated(Account account) {
		accountObservers.forEach(ob -> ob.accountChange(account, AccountChangeType.Created));
	}
	
	private void notifyAccountChange(Account account) {
		accountObservers.forEach(ob -> ob.accountChange(account, AccountChangeType.ValueChanged));
	}

	public Account getAccount(long accountNumber) {
		Account account = accountDAO.loadAccount(accountNumber);
		return account;
	}

	public Collection<Account> getAllAccounts() {
		return accountDAO.getAccounts();
	}

	public void withdraw(long accountNumber, double amount) {
		Account account = accountDAO.loadAccount(accountNumber);
		account.withdraw(amount);
		accountDAO.updateAccount(account);
	}

	public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description) {
		Account fromAccount = accountDAO.loadAccount(fromAccountNumber);
		Account toAccount = accountDAO.loadAccount(toAccountNumber);
		fromAccount.transferFunds(toAccount, amount, description);
		accountDAO.updateAccount(fromAccount);
		accountDAO.updateAccount(toAccount);
	}
}
