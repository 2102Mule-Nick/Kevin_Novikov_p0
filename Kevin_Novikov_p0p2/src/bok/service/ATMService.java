package bok.service;

import java.sql.SQLException;

import bok.dao.UserDao;
import bok.pojo.Account;

public interface ATMService {
	
	public void withdraw(float amount, Account account) throws SQLException;

	public void deposit(float amount, Account account) throws SQLException;

	public void salamiEmbezzle(Account account) throws SQLException;
	
	public void getBalance(Account account) throws SQLException;
	
	public void changePassword(String newpass, Account account) throws SQLException;
}
