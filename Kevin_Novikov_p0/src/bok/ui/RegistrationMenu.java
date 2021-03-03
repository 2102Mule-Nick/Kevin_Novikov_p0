package bok.ui;

import java.util.Scanner;

import bok.pojo.Account;
import bok.service.AuthService;

public class RegistrationMenu implements Menu{

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
	public void options() {
		Account acc = new Account();
		System.out.println("Enter desired username: ");
		acc.setUsername(scan.nextLine());
		System.out.println("Enter desired password: ");
		acc.setPassword(scan.nextLine());
		if(!authService.accountExists(acc)) {
				authService.registerAccount(acc);
				nextMenu = mainMenu;
		}else {
			System.out.println("Account already exists...");
			nextMenu = mainMenu;
		}
	}

	@Override
	public Menu lastMenu() {
		return mainMenu;
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

}
