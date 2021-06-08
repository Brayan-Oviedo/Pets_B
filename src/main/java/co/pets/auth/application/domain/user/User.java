package co.pets.auth.application.domain.user;

import java.util.HashSet;
import java.util.Set;

import co.pets.auth.application.domain.rol.Rol;


public class User extends BUser{

	private String name;
	private String lastName;
	private int age;
	private Set<Rol> rols = new HashSet<>();
	
	
	public User() {
		super();
	}
	

	public User( String userName, String password,  String name, String lastName, int age) {
		super(userName, password);
		this.name = name;
		this.lastName = lastName;
		this.age = age;
	}

	public User(String userName, String password, String name, String lastName, int age, Set<Rol> rols) {
		super(userName, password);
		this.name = name;
		this.lastName = lastName;
		this.age = age;
		this.rols = rols;
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


	public Set<Rol> getRols() {
		return rols;
	}


	public void setRols(Set<Rol> rols) {
		this.rols = rols;
	}
		
	
	
	
}
