package co.pets.domain.exceptions.config;

public class ConflictException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String DESCRIPTION = "Conflict Exception (409)";
	
	public ConflictException(String detail) {
		super(DESCRIPTION + ", "+ detail);
	}
	
}
