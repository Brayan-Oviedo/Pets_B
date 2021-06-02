package co.pets.auth.domain.ports.secundary;

import org.springframework.stereotype.Repository;

import co.pets.auth.domain.model.user.User;
import co.pets.domain.ports.secundary.CRUD;


@Repository
public interface UserRepository extends CRUD<User, Long> {

	User findByUserName(String userName);
	boolean existsByUserName(String userName);
	void deleteByUserName(String userName);
}
