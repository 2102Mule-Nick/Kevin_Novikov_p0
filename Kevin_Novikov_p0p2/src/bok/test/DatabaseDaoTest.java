package bok.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.quality.Strictness;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import bok.dao.DatabaseDao;
import bok.dao.UserDao;
import bok.pojo.Account;
import bok.util.ConnectionFactory;

import org.mockito.Mock;
import org.mockito.Mockito;

@ExtendWith(MockitoExtension.class)
class DatabaseDaoTest {

	@Mock
	private Connection connection;

	DatabaseDao databaseDao;
	
	@BeforeEach
	public void setup() throws Exception {
		
		databaseDao = new DatabaseDao();

		PreparedStatement pstmt = ConnectionFactory.getConnection()
				.prepareStatement("delete from accounts;");

		pstmt.executeUpdate();

	}

	// ConnectionTest
	@Test
	void testConnection() {
		assertNotNull(ConnectionFactory.getConnection());
	}

	@Test
	public void testGenerateAccount() throws SQLException {

		String sql = "insert into accounts (username, pass_word, balance) values (?, ?, ?);";

		Connection conn = ConnectionFactory.getConnection();

		System.out.println(conn);

		PreparedStatement stmt = conn.prepareStatement(sql);

		PreparedStatement spy = Mockito.spy(stmt);

		System.out.println("Spy: " + spy);

		System.out.println("Conn Mock: " + connection);

		when(connection.prepareStatement(sql)).thenReturn(spy);

		databaseDao.setConnection(connection);

		Account account = new Account("kevnovikov", "1234");

		databaseDao.generateAccount(account);

		verify(spy).setString(1, account.getUsername());

		verify(spy).setString(2, account.getPassword());

		verify(spy).setFloat(3, account.getBalance());
		
		verify(spy).executeUpdate();

		PreparedStatement checkStmt = ConnectionFactory.getConnection()
				.prepareStatement("select * from accounts where username = 'kevnovikov';");

		ResultSet rs = checkStmt.executeQuery();

		assertTrue(rs.next());

	}

	@Test
	public void testGetAccountByUsername() throws SQLException {

		String sql = "select * from accounts where username = ?;";
		
		connection = ConnectionFactory.getConnection();

		System.out.println(connection);

		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.setString(1, "kevnovikov");

		System.out.println("Conn Mock: " + connection);
		
		databaseDao.setConnection(connection);

		Account account = new Account("kevnovikov", "1234");

		databaseDao.generateAccount(account);

		assertEquals(account,databaseDao.getAccountByUsername("kevnovikov"));

	}

	@Test
	public void testGetAllClients() throws SQLException{
		connection = ConnectionFactory.getConnection();
		databaseDao.setConnection(connection);
		List<Account> list = new ArrayList<Account>();
		Account account = new Account("kevnovikov", "1234");
		Account account2 = new Account("victim1", "1234");
		Account account3 = new Account("victim2", "1234");
		databaseDao.generateAccount(account);
		databaseDao.generateAccount(account2);
		databaseDao.generateAccount(account3);
		list.add(account);
		list.add(account2);
		list.add(account3);
		assertEquals(list,databaseDao.getAllClients());
	}

	@Test
	public void testUpdateAccount() throws SQLException{
		connection = ConnectionFactory.getConnection();
		databaseDao.setConnection(connection);
		databaseDao.generateAccount(new Account("kevnovikov","1234",0.0f));
		Account account = databaseDao.getAccountByUsername("kevnovikov");
		System.out.println(account.getBalance());
		account.setBalance(200.00f);
		databaseDao.updateAccount(account);
		Account accounttest = databaseDao.getAccountByUsername("kevnovikov");
		System.out.println(account.getBalance());
		assertEquals(accounttest.getBalance(),200.00f,.01f);
	}

	@Test
	public void testCloseAccount() throws SQLException{
		connection = ConnectionFactory.getConnection();
		databaseDao.setConnection(connection);
		databaseDao.generateAccount(new Account("kevnovikov","1234",0.0f));
		databaseDao.closeAccount(databaseDao.getAccountByUsername("kevnovikov"));
		assertNull(databaseDao.getAccountByUsername("kevnovikov"));
	}
}
