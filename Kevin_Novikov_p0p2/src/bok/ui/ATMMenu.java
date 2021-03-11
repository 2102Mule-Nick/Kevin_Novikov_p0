package bok.ui;

import java.sql.SQLException;
import java.util.Scanner;

import com.esotericsoftware.minlog.Log;

import bok.dao.UserDao;
import bok.pojo.Account;
import bok.service.ATMServiceImpl;

public class ATMMenu implements Menu {

	Scanner scan;
	
	UserDao userDao;
	
	Account user;
	
	Menu mainMenu;
	
	Menu nextMenu;
	
	ATMServiceImpl atm;

	public ATMMenu(Menu mainMenu, ATMServiceImpl atm, UserDao userDao) {
		super();
		this.mainMenu = mainMenu;
		this.atm = atm;
		this.userDao = userDao;
	}

	//ATM functionality
	@Override
	public void options() throws SQLException{
		float amt;
		System.out.println("Would you like to withdraw or deposit? Or, type in \"balance\" to check your balance, \"password\" to change"
				+ " your password, and \"quit\" to log out.\n");
		String answer = scan.next();
		switch(answer) {
		case "withdraw":
			System.out.println("Enter amount to withdraw:");
			amt = scan.nextFloat();
			atm.withdraw(amt, user);
			nextMenu = this;
			break;
		case "deposit":
			System.out.println("Enter amount to deposit: ");
			amt = scan.nextFloat();
			atm.deposit(amt, user);
			nextMenu = this;
			break;
		case "balance":
			atm.getBalance(user);
			nextMenu = this;
			break;
		case "password":
			String pass;
			System.out.println("Enter desired password: ");
			pass = scan.nextLine();
			user.setPassword(pass);
		case "quit":
			setUser(null);
			nextMenu = mainMenu;
			break;
		default:
			System.out.println("Let's try this again...");
			nextMenu = this;
			break;
		}
	}

	@Override
	public Scanner getScanner() {
		return scan;
	}

	@Override
	public void setScanner(Scanner scan) {
		this.scan = scan;
		}

	public Account getUsername() {
		return user;
	}

	public void setUser(Account user) {
		this.user = user;
	}

	@Override
	public Menu nextMenu() {
		return nextMenu;
	}

	
}
