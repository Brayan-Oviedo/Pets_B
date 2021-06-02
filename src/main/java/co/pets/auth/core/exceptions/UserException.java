package co.pets.auth.core.exceptions;


public class UserException extends Exception{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserException(String mssg) {
		super(mssg);
	}
	
	public UserException(String detail, String mssg) {
		super(detail + ", " + mssg);
	}
	
}
