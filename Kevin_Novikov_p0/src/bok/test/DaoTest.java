package bok.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.esotericsoftware.kryo.io.Input;

import bok.dao.DatabaseDao;
import bok.dao.UserDao;
import bok.dao.UserDaoKryo;
import bok.pojo.Account;

class DaoTest {
	
	@BeforeEach
	public void setup() throws Exception{
		DatabaseDao.addUser(new Account("kevnovikov","1234",5000.00,12.25));
		DatabaseDao.addUser(new Account("victim","1234",100000.00,1.15));
	}
	
	//DatabaseDao tests
	
	@Test
	public void testGetClientByUsername() {		
		Account test = DatabaseDao.getAccountByUsername("kevnovikov");
		assertEquals(test, DatabaseDao.getAccountByUsername("kevnovikov"));
	}

	@Test
	public void testGetAccountList() {
		for(Account user : DatabaseDao.getAccountList()) {
			System.out.println(user.toString());
		}
		assertEquals(2,DatabaseDao.getAccountList().size());
	}
	
	//UserDaoKryo tests
	
	@Test
	public void testGenerateAccount() throws FileNotFoundException {
		UserDaoKryo udk = new UserDaoKryo();
		
		udk.generateAccount(DatabaseDao.getAccountByUsername("kevnovikov"));
		FileInputStream inputStream = new FileInputStream(UserDaoKryo.FOLDER_NAME + "kevnovikov" + UserDaoKryo.FILE_EXTENSION);
		Input input = new Input(inputStream);
		Account newAccount = udk.kryo.readObject(input,Account.class);
		if(newAccount instanceof Account) {
			assertTrue(true);
		}else {
			assertTrue(false);
		}
	}
	
	@Test
	public void testGetAccountByUsername() {
		UserDaoKryo udk = new UserDaoKryo();
		udk.generateAccount(DatabaseDao.getAccountByUsername("kevnovikov"));
		assertEquals(udk.getAccountByUsername("kevnovikov"),DatabaseDao.getAccountByUsername("kevnovikov"));
	}
	
	@Test
	public void testGetAllClients() {
		UserDaoKryo udk = new UserDaoKryo();
		udk.generateAccount(DatabaseDao.getAccountByUsername("kevnovikov"));
		udk.generateAccount(DatabaseDao.getAccountByUsername("victim"));
		for(Account user : udk.getAllClients()) {
			System.out.println(user.toString());
		}
		assertEquals(DatabaseDao.getAccountList(),udk.getAllClients());
	}
	
	@Test
	public void testUpdateAccount() {
		UserDaoKryo udk = new UserDaoKryo();
		udk.generateAccount(DatabaseDao.getAccountByUsername("kevnovikov"));
		System.out.println(udk.getAccountByUsername("kevnovikov").toString());
		DatabaseDao.getAccountByUsername("kevnovikov").setBalance(100.00);
		udk.updateAccount(DatabaseDao.getAccountByUsername("kevnovikov"));
		System.out.println(udk.getAccountByUsername("kevnovikov").toString());
		assertTrue(udk.getAccountByUsername("kevnovikov").getBalance()==100.00);
	}
	
	@Test
	public void testCloseAccount() {
		UserDaoKryo udk = new UserDaoKryo();
		udk.generateAccount(DatabaseDao.getAccountByUsername("kevnovikov"));
		udk.closeAccount(udk.getAccountByUsername("kevnovikov"));
		assertTrue(udk.getAccountByUsername("kevnovikov") == null);
	}
}
