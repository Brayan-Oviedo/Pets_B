package co.pets.auth.infrastructure.adapter.output.mapper.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import co.pets.auth.application.domain.user.User;
import co.pets.auth.infrastructure.adapter.output.model.user.UserEntity;

public class UserMapperTest {

	private UserMapper mapper;
	private User u;
	private UserEntity uEntity;
	
	
	@BeforeEach
	public void init() {
		mapper = new UserMapper();
		u = new User("Fan", "123", "Fan", "", 19);
		uEntity = new UserEntity("Fan", "123", "Fan", "", 19);
	}
	
	@Test
	public void toDomain() {
		User user = mapper.toDomain(uEntity);
		
		assertEquals(u.getUserName(), user.getUserName());
	}
	
	@Test
	public void toEntity() {
		UserEntity userEntity = mapper.toEntity(u);
		
		assertEquals(uEntity.getUserName(), userEntity.getUserName());
	}
}
