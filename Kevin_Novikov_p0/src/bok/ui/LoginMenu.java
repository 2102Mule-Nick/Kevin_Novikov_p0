package bok.ui;

import java.util.Scanner;

import bok.exception.AccountDNE;
import bok.exception.InvalidPassword;
import bok.pojo.Account;
import bok.service.AuthService;

public class LoginMenu implements Menu{
	
	private AuthService authservice;
	
	private Scanner scan;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void options() {
		System.out.println("Enter username: ");
		String username = scan.nextLine();
		System.out.println("Enter password: ");
		String password = scan.nextLine();
		Account account = new Account(username,password);
		
		try {
			authservice.authenticateAccount(account);
		}catch (AccountDNE e){
			System.out.println("Account does not exist.");
		}catch(InvalidPassword e) {
			System.out.println("Password doesn't match.");
		}catch (Exception e) {
			System.out.println("Something went wrong....");
			e.printStackTrace();
		} finally {
			System.out.println("Service finished.");
		}
		
	}

	@Override
	public Menu lastMenu() {
		return null;
	}

	@Override
	public Scanner getScanner() {
		return scan;
	}

	@Override
	public void setScanner(Scanner scan) {
		this.scan = scan;
	}
}
