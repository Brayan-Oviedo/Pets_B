package co.pets.auth.application.domain.rol;

public class Rol {
	
	private Role role;
	
	
	public Rol() {
		super();
	}

	public Rol(Role role) {
		super();
		this.role = role;
	}

	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}	
	
}
