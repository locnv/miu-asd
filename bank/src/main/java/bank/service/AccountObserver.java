package bank.service;

import bank.domain.Account;

public interface AccountObserver {

	void accountChange(Account account, AccountChangeType type);
}

enum AccountChangeType {
	Created (1),
	ValueChanged (2);

	int type;
	
	AccountChangeType(int v) {
		type = v;
	}
	
	
}