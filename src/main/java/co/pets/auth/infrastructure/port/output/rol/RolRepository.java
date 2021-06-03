package co.pets.auth.infrastructure.port.output.rol;


import org.springframework.stereotype.Repository;

import co.pets.auth.application.domain.rol.Rol;
import co.pets.auth.application.domain.rol.Role;
import co.pets.auth.infrastructure.port.CRUD;

@Repository
public interface RolRepository extends CRUD<Rol, Long> {

	Rol findByRole(Role role);
}
