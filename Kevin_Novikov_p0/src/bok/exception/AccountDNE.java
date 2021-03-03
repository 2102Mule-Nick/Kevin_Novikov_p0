package bok.exception;

public class AccountDNE extends RuntimeException{

	public AccountDNE() {
		super();
	}

	public AccountDNE(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AccountDNE(String message, Throwable cause) {
		super(message, cause);
	}

	public AccountDNE(String message) {
		super(message);
	}

	public AccountDNE(Throwable cause) {
		super(cause);
	}

}
