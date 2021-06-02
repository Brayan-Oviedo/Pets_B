package co.pets.auth.infrastructure.out_ports.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.pets.auth.domain.model.user.User;



@Repository
public interface UserRepositoryDB extends JpaRepository<User, Long> {
	
	Optional<User> findByUserName(String userName);
	boolean existsByUserName(String userName);
	void deleteByUserName(String userName);
}
