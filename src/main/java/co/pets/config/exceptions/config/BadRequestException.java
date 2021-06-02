package co.pets.config.exceptions.config;

public class BadRequestException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String DESCRIPTION = "Bad Request Exceptions (400)";
	
	public BadRequestException(String detail) {
		super(DESCRIPTION + ", "+ detail);
	}
	
	
}
