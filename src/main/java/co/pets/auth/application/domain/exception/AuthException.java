package co.pets.auth.application.domain.exception;


public class AuthException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AuthException(String mssg) {
		super(mssg);
	}

}
