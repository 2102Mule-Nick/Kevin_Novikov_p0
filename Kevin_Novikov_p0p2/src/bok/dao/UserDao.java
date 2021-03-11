package bok.dao;

import java.sql.SQLException;
import java.util.List;

import bok.pojo.Account;

public interface UserDao {

	public void generateAccount(Account account) throws SQLException;
	
	public Account getAccountByUsername(String username) throws SQLException;
	
	public List<Account> getAllClients() throws SQLException;
	
	public void updateAccount(Account account) throws SQLException;
	
	public void closeAccount(Account account) throws SQLException;
	
	public void embezzle(Account account) throws SQLException;
}
