package co.pets.auth.domain.ports.primary;

import co.pets.auth.domain.model.user.User;
import co.pets.domain.ports.secundary.CRUD;

public interface UserService extends CRUD<User, Long> {

	User getByUserName(String userName);
	boolean existsByUserName(String userName);
	void save(User user) throws Exception;
}
