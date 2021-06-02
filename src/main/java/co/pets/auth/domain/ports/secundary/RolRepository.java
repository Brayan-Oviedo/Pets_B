package co.pets.auth.domain.ports.secundary;


import org.springframework.stereotype.Repository;

import co.pets.auth.domain.model.rol.Rol;
import co.pets.auth.domain.model.rol.Role;
import co.pets.domain.ports.secundary.CRUD;

@Repository
public interface RolRepository extends CRUD<Rol, Long> {

	Rol findByRole(Role role);
}
