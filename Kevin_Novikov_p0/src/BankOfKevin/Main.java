package BankOfKevin;

import java.util.Scanner;

import bok.dao.UserDao;
import bok.dao.UserDaoKryo;
import bok.service.AuthService;
import bok.service.AuthServiceImpl;
import bok.ui.LoginMenu;
import bok.ui.MainMenu;
import bok.ui.Menu;
import bok.ui.RegistrationMenu;

public class Main {
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		UserDao userDao = new UserDaoKryo();
		
		AuthService authService = new AuthServiceImpl(userDao);
		
		Menu register = new RegistrationMenu();
		
		Menu login = new LoginMenu(authService);
		
		Menu mainMenu = new MainMenu(login, register);
		
		((RegistrationMenu)register).setMainMenu(mainMenu);
		
		((RegistrationMenu)register).setAuthService(authService);
		
		login.setScanner(scan);
		
		register.setScanner(scan);
		
		mainMenu.setScanner(scan);
		
		Menu nextMenu = mainMenu;
		
		do {
			nextMenu.options();
			
			nextMenu = nextMenu.nextMenu();
			
		} while (nextMenu != null);
	}
}
