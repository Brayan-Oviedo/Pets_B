package co.pets.auth.domain.model.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import co.pets.auth.domain.model.rol.Rol;
import co.pets.domain.model.Person;

@Entity
@Table(name = "user")
public class User extends Person {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "userName", unique = true)
	private String userName;
	@Column(name = "password")
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "user_rol", 
		joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
	private Set<Rol> rols = new HashSet<>();
	
	
	public User() {
		super();
	}

	@Autowired
	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	
	@Autowired
	public User(String userName, String password, String name, String lastName, int age) {
		super(name, lastName, age);
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


	public Set<Rol> getRols() {
		return rols;
	}


	public void setRols(Set<Rol> rols) {
		this.rols = rols;
	}
	
	
	

}
