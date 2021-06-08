package co.pets.auth.infrastructure.adapter.output.adapter.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import co.pets.application.domain.exceptions.Messages;
import co.pets.auth.application.domain.user.User;
import co.pets.auth.infrastructure.adapter.output.mapper.user.UserMapper;
import co.pets.auth.infrastructure.adapter.output.model.user.UserEntity;
import co.pets.auth.infrastructure.adapter.output.repository.user.UserRepositoryDB;


@RunWith(MockitoJUnitRunner.class)
public class UserRepositoryImplTest {

	@Mock
	private UserRepositoryDB dataBase;
	
	@Mock
	private UserMapper mapper;
	
	@InjectMocks
	private UserRepositoryImpl repo;
	
	private Optional<UserEntity> userEntityOptional;
	private UserEntity userEntity;
	private User u;
	
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		
		this.userEntityOptional= Optional.of(new UserEntity("Fan", "123", "Fan", "Ti", 19));
		this.userEntity = userEntityOptional.get();
		this.u = new User(
				userEntity.getUserName(), userEntity.getPassword(),
				userEntity.getName(), userEntity.getLastName(), 
				userEntity.getAge() );
	}
	
	
	@Test
	public void testFindUserById() {

		when(dataBase.findById(userEntity.getId())).thenReturn(userEntityOptional);
		when(mapper.toDomain(userEntityOptional.get())).thenReturn(u);
		
		User user = repo.findById(userEntity.getId());
		
		verify(dataBase).findById(userEntity.getId());
		verify(mapper).toDomain(userEntity);
		assertEquals(userEntityOptional.get().getUserName(), user.getUserName());
		
	}
	
	
	@Test
	public void testFindUserByUserName() {

		when(dataBase.findByUserName(userEntity.getUserName())).thenReturn(userEntityOptional);
		when(mapper.toDomain(userEntityOptional.get())).thenReturn(u);
		
		User user = repo.findByUserName(userEntity.getUserName());
		
		verify(dataBase).findByUserName(userEntity.getUserName());
		verify(mapper).toDomain(userEntity);
		assertEquals(userEntityOptional.get().getUserName(), user.getUserName());
		
	}
	
	
	@Test
	public void testSaveUserWithUserNoExisting() throws Exception {

		when(dataBase.existsByUserName(u.getUserName())).thenReturn(false);
		when(mapper.toEntity(u)).thenReturn(userEntity);
		when(dataBase.findByUserName(u.getUserName())).thenReturn(userEntityOptional);
		
		repo.save(u);
		
		verify(dataBase).existsByUserName(u.getUserName());
		verify(mapper).toEntity(u);
		
		
		assertTrue(true);
		
	}
	
	
	@Test
	public void testSaveUserWithUserExisting() {
		
		Exception exception = null;

		when(dataBase.existsByUserName(u.getUserName())).thenReturn(true);
		
		try {
			repo.save(u);	
		} catch (Exception e) {
			exception = e;
		}
		
		verify(dataBase).existsByUserName(u.getUserName());
		assertEquals(Messages.MESSAGE_USER_EXISTING, exception.getMessage());
		
	}
	
	
	@Test
	public void testUpdateUserWithUserExisting() throws Exception {

		when(dataBase.existsByUserName(u.getUserName())).thenReturn(true);
		when(mapper.toEntity(u)).thenReturn(userEntity);
		when(dataBase.findByUserName(u.getUserName())).thenReturn(userEntityOptional);
		
		repo.update(u);
		
		verify(dataBase).existsByUserName(u.getUserName());
		verify(mapper).toEntity(u);
		
		
		assertTrue(true);
		
	}
	
	
	@Test
	public void testUpdateUserWithUserNoExisting() {
		
		Exception exception = null;

		when(dataBase.existsByUserName(u.getUserName())).thenReturn(false);
		
		try {
			repo.update(u);	
		} catch (Exception e) {
			exception = e;
		}
		
		verify(dataBase).existsByUserName(u.getUserName());
		assertEquals(Messages.MESSAGE_USER_NO_EXISTING, exception.getMessage());
		
	}
	
	
	@Test
	public void testDeleteUserById() {
		
		repo.deleteById(userEntity.getId());
	
		assertTrue(true);
	}
	
	
	@Test
	public void testDeleteUserByUserName() {
		
		repo.deleteByUserName(userEntity.getUserName());
	
		assertTrue(true);
	}
	
	
	@Test
	public void testGetAllUsers() {
		
		List<UserEntity> list = new ArrayList<UserEntity>();
		list.add(userEntity);
		when(dataBase.findAll()).thenReturn(list);
		when(mapper.toDomain(userEntity)).thenReturn(u);
		
		List<User> users = repo.getAll();
		
		verify(dataBase).findAll();
		verify(mapper).toDomain(userEntity);
		assertEquals(list.get(0).getUserName(), users.get(0).getUserName());
	}
}
