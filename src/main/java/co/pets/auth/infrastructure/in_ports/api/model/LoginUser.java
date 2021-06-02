package co.pets.auth.infrastructure.in_ports.api.model;

import co.pets.auth.domain.model.user.BUser;

public class LoginUser extends BUser{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public LoginUser() {}
	
	public LoginUser(String userName, String password) {
		super(userName, password);
	}
}
