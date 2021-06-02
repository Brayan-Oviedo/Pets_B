package co.pets.auth.infrastructure.out_ports.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.pets.auth.domain.model.rol.Rol;
import co.pets.auth.domain.model.rol.Role;


@Repository
public interface RolRepositoryDB extends JpaRepository<Rol, Long> {
	
	Optional<Rol> findByRole(Role role);
}
