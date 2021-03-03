package bok.ui;

import java.util.Scanner;

public interface Menu {

	public Menu nextMenu();
	
	public void options();
	
	public Menu lastMenu();
	
	public Scanner getScanner();
	
	public void setScanner(Scanner scan);
}
