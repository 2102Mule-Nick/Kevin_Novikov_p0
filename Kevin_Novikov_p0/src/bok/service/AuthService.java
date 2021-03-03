package bok.service;

import bok.exception.AccountDNE;
import bok.exception.InvalidPassword;
import bok.pojo.Account;

public interface AuthService {

	public boolean accountExists(Account account);
	
	public Account authenticateAccount(Account account) throws AccountDNE, InvalidPassword;
	
	public void registerAccount(Account account);
}
