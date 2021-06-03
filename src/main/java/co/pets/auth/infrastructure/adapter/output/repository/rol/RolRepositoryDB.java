package co.pets.auth.infrastructure.adapter.output.repository.rol;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.pets.auth.application.domain.rol.Role;
import co.pets.auth.infrastructure.adapter.output.model.rol.RolEntity;


@Repository
public interface RolRepositoryDB extends JpaRepository<RolEntity, Long> {
	
	Optional<RolEntity> findByRole(Role role);
}
