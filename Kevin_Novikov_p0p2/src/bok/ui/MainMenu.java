package bok.ui;

import java.util.Scanner;

public class MainMenu implements Menu {

	private Scanner scanner;

	private Menu loginMenu;

	private Menu registrationMenu;

	private Menu nextMenu;

	@Override
	public void options() {
		System.out.println("Welcome to the Bank of Kevin, would you like to register, login, or quit?");
		String answer = scanner.next();
		switch (answer) {
		case "register":
			nextMenu = registrationMenu;
			break;
		case "login":
			nextMenu = loginMenu;
			break;
		case "quit":
			nextMenu = null;
		default: 
			System.out.println("Let's try this again...");
			nextMenu = this;
		}
	}

	@Override
	public Scanner getScanner() {
		return scanner;
	}

	public MainMenu(Menu loginMenu, Menu registrationMenu) {
		super();
		this.loginMenu = loginMenu;
		this.registrationMenu = registrationMenu;
	}

	@Override
	public void setScanner(Scanner scan) {
		this.scanner = scan;
	}

	@Override
	public Menu nextMenu() {
		return nextMenu;
	}

}
