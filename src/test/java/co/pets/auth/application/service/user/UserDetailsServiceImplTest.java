package co.pets.auth.application.service.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;

import co.pets.auth.application.domain.rol.Rol;
import co.pets.auth.application.domain.user.User;
import co.pets.auth.infrastructure.port.input.user.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceImplTest {

	@Mock
	private UserService userService;
	
	@InjectMocks
	private UserDetailsServiceImpl service;
	
	private User u;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		
		u = new User("Fan", "123", "", "", 19, new HashSet<Rol>());
		
	}
	
	@Test
	public void testLoadUserByUserNameWithoutException() {
		when(userService.getByUserName(u.getUserName())).thenReturn(u);
		
		UserDetails userAuth = service.loadUserByUsername(u.getUserName());
		
		verify(userService).getByUserName(u.getUserName());
		assertEquals(u.getUserName(), userAuth.getUsername());
	}
	
	
}
