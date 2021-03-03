package bok.ui;

import java.util.Scanner;

public class ATMMenu implements Menu {

	Scanner scan;

	@Override
	public Menu nextMenu() {
		return null;
	}

	@Override
	public void options() {
		System.out.println("Would you like to withdraw or deposit?\n");
		String answer = scan.nextLine();
		switch(answer) {
		case "withdraw":
			System.out.println("Enter amount to withdraw:");
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
