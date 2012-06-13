package de.goldmann.texter.exception;

/**
 * Exception for incorrect password.
 * 
 * @author goldmannm
 *
 */
public class IncorrectPasswordException extends Exception {

	private static final long serialVersionUID = 1378775679251050807L;

	public IncorrectPasswordException() {
	};

	public IncorrectPasswordException(String s) {
		super(s);
	}
}
