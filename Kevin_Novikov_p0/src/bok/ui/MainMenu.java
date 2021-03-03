package bok.ui;

import java.util.Scanner;

public class MainMenu implements Menu {

	private Scanner scanner;

	private Menu loginMenu;

	private Menu registrationMenu;

	private Menu nextMenu;

	@Override
	public Menu nextMenu() {
		return nextMenu;
	}

	@Override
	public void options() {
		System.out.println("Welcome to the Bank of Kevin, would you like to register or login?");
		String answer = scanner.nextLine();
		switch (answer) {
		case "register":
			nextMenu = registrationMenu;
			break;
		case "login":
			nextMenu = loginMenu;
			break;
		default: 
			System.out.println("Let's try this again...");
			nextMenu = this;
		}
	}

	@Override
	public Menu lastMenu() {
		return null;
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

}
