package co.pets.auth.core.exceptions;


public class AuthException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AuthException(String mssg) {
		super(mssg);
	}

	public AuthException(String detail, String mssg) {
		super(detail + ", " + mssg);
	}


}
