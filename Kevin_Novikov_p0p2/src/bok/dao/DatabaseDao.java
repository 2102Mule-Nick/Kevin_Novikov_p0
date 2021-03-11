package bok.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import bok.pojo.Account;

public class DatabaseDao implements UserDao {

	Logger log = Logger.getRootLogger();
	
	Connection connection;

	// Returns List of all accounts
	@Override
	public List<Account> getAllClients() throws SQLException{
		List<Account> allClients = new ArrayList<Account>();
		String sql = "select * from accounts;";
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			String username = rs.getString("username");
			String password = rs.getString("pass_word");
			Float balance = rs.getFloat("balance");
			Account user = new Account(username,password,balance);
			allClients.add(user);
		}
		return allClients;
	}

	// Returns account with user name matching parameter
	@Override
	public Account getAccountByUsername(String username) throws SQLException{
		Account user;
		String sql = "select * from accounts where username = ?;";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, username);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			String password = rs.getString("pass_word");
			Float balance = rs.getFloat("balance");
			user = new Account(username,password,balance);
			return user;
		}
		return null;
	}

	// Adds User to ACCOUNTS List
	@Override
	public void generateAccount(Account account) throws SQLException{
		String sql = "insert into accounts (username, pass_word, balance) values (?, ?, ?);";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, account.getUsername());
		stmt.setString(2, account.getPassword());
		stmt.setFloat(3, account.getBalance());
		stmt.executeUpdate();
	}

	// Removes User from ACCOUNTS List
	@Override
	public void closeAccount(Account account) throws SQLException{
		String sql = "delete from accounts where username = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, account.getUsername());
		stmt.executeUpdate();
	}

	@Override
	public void updateAccount(Account account) throws SQLException{
		String sql = "update accounts set pass_word = ?, balance = ? where username = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, account.getPassword());
		stmt.setFloat(2, account.getBalance());
		stmt.setString(3, account.getUsername());
		stmt.executeUpdate();
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public void embezzle(Account account) throws SQLException{
		CallableStatement stmt = connection.prepareCall("{call salami_embezzle(?)}");
		stmt.setString(1, account.getUsername());
		stmt.executeQuery();
	}
}
