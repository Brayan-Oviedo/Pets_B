package co.pets.auth.infrastructure.port.input.user;

import co.pets.auth.application.domain.user.User;
import co.pets.auth.infrastructure.port.CRUD;

public interface UserService extends CRUD<User, Long> {

	User getByUserName(String userName);
	boolean existsByUserName(String userName);
	void save(User user) throws Exception;
}
