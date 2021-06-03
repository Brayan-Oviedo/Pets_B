package co.pets.auth.infrastructure.port.output.user;

import org.springframework.stereotype.Repository;

import co.pets.auth.application.domain.user.User;
import co.pets.auth.infrastructure.port.CRUD;


@Repository
public interface UserRepository extends CRUD<User, Long> {

	User findByUserName(String userName);
	boolean existsByUserName(String userName);
	void deleteByUserName(String userName);
}
