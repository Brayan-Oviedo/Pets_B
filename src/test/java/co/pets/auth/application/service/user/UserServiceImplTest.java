package co.pets.auth.application.service.user;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import co.pets.auth.application.domain.rol.Rol;
import co.pets.auth.application.domain.user.User;
import co.pets.auth.infrastructure.port.output.user.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

	
	@Mock
	private UserRepository repo;
	
	@InjectMocks
	private UserServiceImpl service;
	
	private User u;
	
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		
		u = new User();
		u.setUserName("Fan");
		u.setPassword("123");
		u.setName("Fan");
		u.setLastName("Ti");
		u.setAge(19);
		u.setRols(new HashSet<Rol>());
	}
	
	@Test
	public void testGetUserByUserName() {
		when(repo.findByUserName(u.getUserName())).thenReturn(u);
		
		User user = service.getByUserName(u.getUserName());
		
		verify(repo).findByUserName(u.getUserName());
		assertEquals(u.getUserName(), user.getUserName());
	}
	
	@Test
	public void testFindUserById() throws Exception{
		when(repo.findById(1L)).thenReturn(u);
		
		User user = service.findById(1L);
		
		verify(repo).findById(1L);
		assertEquals(u.getUserName(), user.getUserName());
	}
	
	@Test
	public void testExistsUserByUserName() { 
		when(repo.existsByUserName(u.getUserName())).thenReturn(true);
		
		boolean exists = service.existsByUserName(u.getUserName());
		
		verify(repo).existsByUserName(u.getUserName());
		assertEquals(true, exists);
	}
	
	@Test
	public void testSaveUserWithoutException() throws Exception { 	
		
		service.save(u);	
		
		assertTrue(true);;
	}
	
	@Test
	public void testUpdateUserWithoutException() throws Exception { 	
		
		service.update(u);	
		
		assertTrue(true);;
	}
	
	@Test
	public void testDeleteById() {
		service.deleteById(1L);
		
		assertTrue(true);
	}
	
	@Test
	public void testDeleteByUserName() {
		service.deleteByUserName(u.getUserName());
		
		assertTrue(true);
	}
	
	@Test
	public void testGetAllUsers() {
		
		List<User> list = new ArrayList<>();
		list.add(u);
		when(repo.getAll()).thenReturn(list);
		
		List<User> users = service.getAll();
		
		verify(repo).getAll();
		assertEquals(list.size(), users.size());
	}
}
