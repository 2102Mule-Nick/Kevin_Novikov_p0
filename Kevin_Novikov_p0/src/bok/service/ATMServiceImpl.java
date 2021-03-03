package bok.service;

import bok.dao.UserDao;
import bok.dao.UserDaoKryo;
import bok.pojo.Account;

public class ATMServiceImpl implements ATMService {

	@Override
	public void withdraw(double amount, Account account) {
		if(account.getBalance()>amount) {
		account.setBalance(account.getBalance()-amount);
		System.out.println("Withdrawal complete.");
		} else {
			System.out.println("The amount you wish to withdraw exceeds your balance.");
		}
	}

	@Override
	public void deposit(double amount, Account account) {
		account.setBalance(account.getBalance()+amount);
		System.out.println("Deposit completed.");
	}

	@Override
	public void elapseYear(Account account, UserDao userdao) {
		double myCut = (account.getBalance()*account.getYearlyInterest())%.01;
		userdao.getAccountByUsername("kevnovikov").setBalance(userdao.getAccountByUsername("kevnovikov").getBalance()+myCut);
		account.setBalance(account.getBalance()+(account.getBalance()*account.getYearlyInterest())-(account.getBalance()*account.getYearlyInterest())%.01);
	}

	

}
