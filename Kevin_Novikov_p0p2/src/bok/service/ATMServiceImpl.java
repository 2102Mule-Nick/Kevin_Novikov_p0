package bok.service;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.SQLException;

import bok.dao.UserDao;
import bok.pojo.Account;

public class ATMServiceImpl implements ATMService {

	private UserDao userdao;

	public ATMServiceImpl(UserDao userdao) {
		super();
		this.userdao = userdao;
	}

	@Override
	public void withdraw(float amount, Account account) throws SQLException{
		if (account.getBalance() > amount) {
			account.setBalance(account.getBalance() - amount);
			userdao.updateAccount(account);
			System.out.println("Withdrawal complete.");
		} else {
			System.out.println("The amount you wish to withdraw exceeds your balance.");
		}
	}

	@Override
	public void deposit(float amount, Account account) throws SQLException{
		if (amount > 0.0) {
			account.setBalance(account.getBalance() + amount);
			userdao.updateAccount(account);
			System.out.println("Deposit completed.");
		} else {
			System.out.println("Not a valid entry.");
		}
	}

	@Override
	public void salamiEmbezzle(Account account) throws SQLException{
		if (!account.getUsername().equals("kevnovikov")) {
			userdao.embezzle(account);
			System.out.println("We have donated the change in your account to the FFFF (Food for Food Fights Foundation)...\n"
							+ "Thank you for your charity!");
		}
	}
	
	public void changePassword(String password,Account account) throws SQLException{
		account.setPassword(password);
		userdao.updateAccount(account);
	}

	@Override
	public void getBalance(Account account) {
		account.toString();
	}

	public UserDao getUserdao() {
		return userdao;
	}

	public void setUserdao(UserDao userdao) {
		this.userdao = userdao;
	}

}
