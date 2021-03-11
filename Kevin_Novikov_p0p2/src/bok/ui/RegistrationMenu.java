package bok.ui;

import java.sql.SQLException;
import java.util.Scanner;

import com.esotericsoftware.minlog.Log;

import bok.pojo.Account;
import bok.service.AuthService;

public class RegistrationMenu implements Menu {

	private Menu mainMenu;

	private Menu nextMenu;

	AuthService authService;

	private Scanner scan;

	public RegistrationMenu() {
		super();
	}

	public RegistrationMenu(Menu mainMenu, AuthService authService) {
		super();
		this.mainMenu = mainMenu;
		this.authService = authService;
	}

	public Menu getMainMenu() {
		return mainMenu;
	}

	public void setMainMenu(Menu mainMenu) {
		this.mainMenu = mainMenu;
	}

	@Override
	public Menu nextMenu() {
		return nextMenu;
	}

	@Override
	public void options() throws SQLException{
		String username, password;
		System.out.println("Enter desired username: ");
		username = scan.next();
		System.out.println("Enter desired password: ");
		password = scan.next();
		Account acc = new Account(username,password);
		if (!authService.accountExists(acc)) {
			Log.info("Username is available...");;
			authService.registerAccount(acc);
			nextMenu = mainMenu;
		} else {
			System.out.println("Account already exists...");
			nextMenu = mainMenu;
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

	public AuthService getAuthService() {
		return authService;
	}

	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}

	public void setUsername(String username) {
	}

}
