package co.pets.config.exceptions.config;

public class NotFoundException extends RuntimeException{
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
	private static final String DESCRIPCION = "Not Found Exception (404)";
	
	public NotFoundException(String detail) {
		super(DESCRIPCION + ", " + detail);
	}
}
