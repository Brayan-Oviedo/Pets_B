package co.pets.auth.infrastructure.port.input.rol;

import co.pets.auth.application.domain.rol.Rol;
import co.pets.auth.application.domain.rol.Role;
import co.pets.auth.infrastructure.port.CRUD;

public interface RolService extends CRUD<Rol, Long> {
	
	Rol getByRole(Role role);
	
}
