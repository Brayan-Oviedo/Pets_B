package co.pets.auth.application.domain.user;

import java.util.HashSet;
import java.util.Set;

public class NewUser extends BUser{

	private String name;
	private String lastName;
	private int age;
	private Set<String> rols = new HashSet<>();
	
	
	public NewUser() {
		super();
	}


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


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public Set<String> getRols() {
		return rols;
	}


	public void setRols(Set<String> rols) {
		this.rols = rols;
	}
	
	
	
	
}
