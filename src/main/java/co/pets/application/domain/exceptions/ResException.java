package co.pets.application.domain.exceptions;

import co.pets.application.domain.exceptions.handler.BadRequestException;

public class ResException {
	
	private String mssg;
	private Exception exception;
	
	public ResException() {
		
	}
	
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

	public static ResException getBadException(String mssg) {
		BadRequestException exception = new BadRequestException(mssg);
		ResException res = new ResException(mssg, exception);
		return res;
	}
	
}