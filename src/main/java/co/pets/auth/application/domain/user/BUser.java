package co.pets.auth.application.domain.user;


public class BUser {

	private String userName;
	private String password;
	
	
	public BUser() {
		super();
	}


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
