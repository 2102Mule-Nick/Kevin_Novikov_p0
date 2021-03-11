package bok.ui;

import java.sql.SQLException;
import java.util.Scanner;

import bok.pojo.Account;

public interface Menu {

	public Menu nextMenu();
	
	public void options() throws SQLException;
	
	public Scanner getScanner();
	
	public void setScanner(Scanner scan);

}
