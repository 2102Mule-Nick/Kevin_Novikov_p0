package bok.exception;

public class InvalidPassword extends RuntimeException{

	public InvalidPassword() {
		super();
	}

	public InvalidPassword(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidPassword(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidPassword(String message) {
		super(message);
	}

	public InvalidPassword(Throwable cause) {
		super(cause);
	}

}
