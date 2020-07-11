package bank.service;

import bank.domain.Account;

public class Logger implements AccountObserver {

	@Override
	public void accountChange(Account account, AccountChangeType type) {
		System.out.println("[logger] Account change: "
				+ "\nChange type: " + type
				+ "\nAccount number: " + account.getAccountnumber()
				+ "\nAccount balance: " + account.getBalance());
		
	}
}
