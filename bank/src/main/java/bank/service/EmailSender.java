package bank.service;

import bank.domain.Account;

public class EmailSender implements AccountObserver {

	@Override
	public void accountChange(Account account, AccountChangeType type) {
		if(type == AccountChangeType.Created) {
			System.out.println("[sms.sender] sending email about account created."
				+ "\nAccount number: " + account.getAccountnumber());
		}
	}
}
