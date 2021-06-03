package co.pets.auth.infrastructure.adapter.output.repository.user;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.pets.auth.infrastructure.adapter.output.model.user.UserEntity;



@Repository
public interface UserRepositoryDB extends JpaRepository<UserEntity, Long> {
	
	Optional<UserEntity> findByUserName(String userName);
	boolean existsByUserName(String userName);
	void deleteByUserName(String userName);
}
