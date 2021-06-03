package co.pets.auth.infrastructure.adapter.output.model.user;

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

import co.pets.auth.infrastructure.adapter.output.model.rol.RolEntity;

@Entity
@Table(name = "user")
public class UserEntity extends PersonEntity {

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
	private Set<RolEntity> rols = new HashSet<>();
	
	
	public UserEntity() {
		super();
	}

	@Autowired
	public UserEntity(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	
	@Autowired
	public UserEntity(String userName, String password, String name, String lastName, int age) {
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


	public Set<RolEntity> getRols() {
		return rols;
	}


	public void setRols(Set<RolEntity> rols) {
		this.rols = rols;
	}
	
	
	

}
