package bok.pojo;

public class Account {
	
	private String username;
	
	private String password;
	
	private float balance;

	public Account(String username, String password, float balance) {
		super();
		this.username = username;
		this.password = password;
		this.balance = balance;
	}
	
	public Account(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.balance = 0;
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
	
	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		if(balance > 0) {
			this.balance = balance;
		}else {
			this.balance = 0;
		}
	}
	
	public String toString() {
		System.out.println("User: "+((String) username)+" Balance: $"+String.format("%.2f", balance));
		return null;
	}
	
	public boolean equals(Object o) {
		if(o instanceof Account) {
			return true;
		}
		return false;
	}
}
