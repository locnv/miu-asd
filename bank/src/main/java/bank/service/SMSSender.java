package bank.service;

import bank.domain.Account;

public class SMSSender implements AccountObserver {

	@Override
	public void accountChange(Account account, AccountChangeType type) {
		if(type == AccountChangeType.ValueChanged) {
			System.out.println("[sms.sender] sending email about account value change."
				+ "\nAccount number: " + account.getAccountnumber()
				+ "\nNew balance: " + account.getBalance());
		}
	}
}
