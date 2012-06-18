package de.goldmann.texter.common.exception;

/**
 * Exception when no user is found in db.
 * 
 * @author goldmannm
 * 
 */
public class NoUserFoundException extends Exception {

	private static final long serialVersionUID = 799319386475847287L;

	public NoUserFoundException() {
	};

	public NoUserFoundException(String message) {
		super(message);
	}

}
