package bok.ui;

import java.util.Scanner;

import com.esotericsoftware.minlog.Log;

import bok.exception.AccountDNE;
import bok.exception.InvalidPassword;
import bok.pojo.Account;
import bok.service.AuthService;
import bok.service.AuthServiceImpl;

public class LoginMenu implements Menu {

	private AuthService authservice;

	private Scanner scan;

	private Menu nextMenu;

	private Menu atmMenu;

	
	
	public Menu getATMMenu() {
		return atmMenu;
	}

	public void setATMMenu(Menu ATMMenu) {
		atmMenu = ATMMenu;
	}

	public AuthService getService() {
		return authservice;
	}

	public LoginMenu(AuthService authservice) {
		super();
		this.authservice = authservice;
	}

	public LoginMenu() {
		super();
	}

	public void setService(AuthService authservice) {
		this.authservice = authservice;
	}

	@Override
	public Menu nextMenu() {
		return nextMenu;
	}

	// login algorithm
	@Override
	public void options() {
		System.out.println("Enter username: ");
		String username = scan.next();
		System.out.println("Enter password: ");
		String password = scan.next();
		Account account = new Account(username, password);

		try {
			Log.info("Authenticating...");
			((ATMMenu) atmMenu).setUser(((AuthServiceImpl) authservice).authenticateAccount(account));
			Log.info("Access Granted.");
			nextMenu = atmMenu;
		} catch (AccountDNE e) {
			System.out.println("Account does not exist.");
			nextMenu = this;
		} catch (InvalidPassword e) {
			System.out.println("Password doesn't match.");
			nextMenu = this;
		} catch (Exception e) {
			System.out.println("Something went wrong....");
			e.printStackTrace();
			nextMenu = this;
		} finally {
			System.out.println("Service finished.");
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

	public void setUser(Account user) {
	}
	
	public void setATM(Menu atm) {
		atmMenu = atm;
	}
}
