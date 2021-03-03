package bok.dao;

import java.util.ArrayList;
import java.util.List;

import bok.pojo.Account;

public class DatabaseDao {

	private static final List<Account> ACCOUNTS = new ArrayList<Account>();

	public DatabaseDao() {
		super();
	}

	//Returns List of all accounts
	public static List<Account> getAccountList() {
		return ACCOUNTS;
	}
	
	//Returns account with user name matching parameter
	public static Account getAccountByUsername(String username) {
		for(Account account: ACCOUNTS) {
			if(account.getUsername()==username) {
				return account;
			}
		}
		return null;
	}
	
	//Adds User to ACCOUNTS List
	public static void addUser(Account account) {
		ACCOUNTS.add(account);
	}
	
	//Removes User from ACCOUNTS List
	public static void removeUser(Account account) {
		ACCOUNTS.remove(account);
	}
	
}
