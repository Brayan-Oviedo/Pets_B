package co.pets.config.exceptions;


public class ResException {
	
	private String mssg;
	private Exception exception;
	
	public ResException(String mssg, Exception exception) {
		this.setMssg(mssg);
		this.setException(exception);
	}

	public String getMssg() {
		return mssg;
	}

	public void setMssg(String mssg) {
		this.mssg = mssg;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	
	
}