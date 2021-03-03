package bok.dao;

import java.util.List;

import bok.pojo.Account;

public interface UserDao {

	public void generateAccount(Account account);
	
	public Account getAccountByUsername(String username);
	
	public List<Account> getAllClients();
	
	public void updateAccount(Account account);
	
	public void closeAccount(Account account);
}
