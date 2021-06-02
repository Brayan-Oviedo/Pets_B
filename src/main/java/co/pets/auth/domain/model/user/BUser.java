package co.pets.auth.domain.model.user;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class BUser implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotBlank
	private String userName;
	@NotBlank
	private String password;
	
	public BUser() {}

	public BUser(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


}
