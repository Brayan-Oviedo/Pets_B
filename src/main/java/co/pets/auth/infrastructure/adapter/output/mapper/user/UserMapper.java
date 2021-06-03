package co.pets.auth.infrastructure.adapter.output.mapper.user;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import co.pets.auth.application.domain.user.User;
import co.pets.auth.infrastructure.adapter.output.model.user.UserEntity;

@Scope("singleton")
@Component
public class UserMapper {
	
	public User toDomain(UserEntity userEntity) {
		User user = new User();
		BeanUtils.copyProperties(userEntity, user);
		return user;
	}
	
	public UserEntity toEntity(User user) {
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		return userEntity;
	}

}
