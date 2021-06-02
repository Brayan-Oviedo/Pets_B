package co.pets.auth.domain.ports.primary;

import co.pets.auth.domain.model.rol.Rol;
import co.pets.auth.domain.model.rol.Role;
import co.pets.domain.ports.secundary.CRUD;

public interface RolService extends CRUD<Rol, Long> {
	
	Rol getByRole(Role role);
	
}
