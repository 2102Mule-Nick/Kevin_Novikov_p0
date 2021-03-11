package bok.service;

import java.sql.SQLException;

import bok.exception.AccountDNE;
import bok.exception.InvalidPassword;
import bok.pojo.Account;

public interface AuthService {

	public boolean accountExists(Account account);
	
	public Account authenticateAccount(Account account) throws AccountDNE, InvalidPassword, SQLException;
	
	public void registerAccount(Account account) throws SQLException;
}
