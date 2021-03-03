package bok.pojo;

public class Account {
	
	private String username;
	
	private String password;
	
	private double balance;
	
	private double yearlyInterest;

	public Account(String username, String password, double balance, double interest) {
		super();
		this.username = username;
		this.password = password;
		this.balance = balance;
		this.yearlyInterest = interest;
	}
	
	public Account(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		balance = 0.0;
		yearlyInterest = 1.25;
	}

	public Account() {
		super();
	}

	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		if(balance > 0) {
			this.balance = balance;
		}else {
			this.balance = 0;
		}
	}
	
	public double getYearlyInterest() {
		return yearlyInterest;
	}
	
	public void setYearlyInterest(double yearlyInterest) {
		if(yearlyInterest>0) {
			this.yearlyInterest = yearlyInterest;
		}else {
			this.yearlyInterest = yearlyInterest;
		}
	}
	
	public String toString() {
		 return "User: "+((String) username)+" Balance: $"+String.format("%.2f", balance)+" Yearly Interest: %"+
				 String.format("%.2f", yearlyInterest);
	}
	
	public boolean equals(Object o) {
		if(o instanceof Account) {
			return true;
		}
		return false;
	}
}
