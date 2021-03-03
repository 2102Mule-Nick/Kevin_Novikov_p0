package bok.service;

import bok.dao.UserDao;
import bok.pojo.Account;

public interface ATMService {
	
	public void withdraw(double amount, Account account);

	public void deposit(double amount, Account account);

	public void elapseYear(Account account, UserDao userdao);
}
