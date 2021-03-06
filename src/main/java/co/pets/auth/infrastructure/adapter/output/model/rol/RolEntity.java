package co.pets.auth.infrastructure.adapter.output.model.rol;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import co.pets.auth.application.domain.rol.Role;


@Entity
@Table(name = "rol")
public class RolEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "rol")
	@Enumerated(EnumType.STRING)
	private Role role;

	public RolEntity() {
		super();
	}

	@Autowired
	public RolEntity(Role role) {
		super();
		this.role = role;
	}

	public long getId() {
		return id;
	}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	
}
