package co.pets.application.domain.exceptions.config;

public class FieldInvalidException extends BadRequestException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String DESCRIPTION = "The file is invalid";

	public FieldInvalidException(String detail) {
		super(DESCRIPTION + ", " + detail);
	}
}
