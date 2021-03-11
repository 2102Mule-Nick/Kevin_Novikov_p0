package BankOfKevin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import bok.dao.DatabaseDao;
import bok.dao.UserDao;
import bok.service.ATMService;
import bok.service.ATMServiceImpl;
import bok.service.AuthService;
import bok.service.AuthServiceImpl;
import bok.ui.ATMMenu;
import bok.ui.LoginMenu;
import bok.ui.MainMenu;
import bok.ui.Menu;
import bok.ui.RegistrationMenu;
import bok.util.ConnectionFactory;

public class Main {
	
	public static void main(String[] args) throws SQLException {
		
		Connection conn = ConnectionFactory.getConnection();
		
		Scanner scan = new Scanner(System.in);
		
		UserDao userDao = new DatabaseDao();
		
		((DatabaseDao) userDao).setConnection(conn);
		
		AuthService authService = new AuthServiceImpl(userDao);
		
		ATMService atmService = new ATMServiceImpl(userDao);
		
		Menu register = new RegistrationMenu();
		
		Menu login = new LoginMenu(authService);
		
		Menu mainMenu = new MainMenu(login, register);
		
		Menu ATM = new ATMMenu(mainMenu,(ATMServiceImpl) atmService, userDao);
		
		((LoginMenu) login).setATM(ATM);
		
		((RegistrationMenu)register).setMainMenu(mainMenu);
		
		((RegistrationMenu)register).setAuthService(authService);
		
		((LoginMenu) login).setATMMenu(ATM);
		
		login.setScanner(scan);
		
		register.setScanner(scan);
		
		mainMenu.setScanner(scan);
		
		ATM.setScanner(scan);
		
		Menu nextMenu = mainMenu;
		
		do {
			nextMenu.options();
			
			nextMenu = nextMenu.nextMenu();
			
		} while (nextMenu != null);
	}
}
