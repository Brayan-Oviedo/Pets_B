package co.pets.application.domain.exceptions.handler;

public class FieldAlreadyExistException extends ConflictException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String DESCRIPTION = "File already exists";

	public FieldAlreadyExistException(String detail) {
		super(DESCRIPTION + ", " + detail);
	}
}
