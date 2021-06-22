package co.pets.application.domain.exceptions.handler;

public class ErrorMessage {
	
	private String exception;
	private String message;
	private String path;
	
	public ErrorMessage(Exception exception, String path) {
		super();
		this.exception = exception.getClass().getSimpleName();;
		this.message = exception.getMessage();
		this.path = path;
	}
	
	
	public String getException() {
		return exception;
	}
	public String getMessage() {
		return message;
	}
	public String getPath() {
		return path;
	}


	@Override
	public String toString() {
		return "ErrorMessage [exception=" + exception + ", message=" + message + ", path=" + path + "]";
	}
	
}
