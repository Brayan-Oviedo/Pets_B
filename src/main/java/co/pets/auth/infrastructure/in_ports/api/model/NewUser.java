package co.pets.auth.infrastructure.in_ports.api.model;

import java.util.HashSet;
import java.util.Set;

import co.pets.auth.domain.model.user.BUser;



public class NewUser extends BUser {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String lastName;
	private int age;
	private Set<String> rols = new HashSet<>();
	
	public NewUser() {}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Set<String> getRols() {
		return rols;
	}
	public void setRols(Set<String> rols) {
		this.rols = rols;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	

	
}
