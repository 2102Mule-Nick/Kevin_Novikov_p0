package bok.service;

import com.esotericsoftware.minlog.Log;

import bok.dao.UserDao;
import bok.exception.AccountDNE;
import bok.exception.InvalidPassword;
import bok.pojo.Account;

public class AuthServiceImpl implements AuthService{
	
	private UserDao userDao;

	public UserDao getUserdao() {
		return userDao;
	}

	public void setUserdao(UserDao userdao) {
		this.userDao = userdao;
	}

	@Override
	public boolean accountExists(Account account) {
		try {
			if (userDao.getAccountByUsername(account.getUsername()) != null) {
				return true;
			}
		} catch (AccountDNE dne){
			Log.info("Account does not exist!");
			return false;
		}
		return false;
	}

	@Override
	public Account authenticateAccount(Account account) throws AccountDNE, InvalidPassword {
		Account user = userDao.getAccountByUsername(account.getUsername());
		if(accountExists(user)) {
			if(user.getPassword().equals(account.getPassword())) {
				return user;
			}else if(account.getPassword() !=user.getPassword()){
				throw new InvalidPassword("Incorrect password.");
			}
		}else {
			throw new AccountDNE("The account does not exist...");
		}
		return user;
	}

	@Override
	public void registerAccount(Account account) {
		userDao.generateAccount(account);
	}

	public AuthServiceImpl(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	public AuthServiceImpl() {
		super();
	}
}
