package co.pets.auth.application.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.crypto.password.PasswordEncoder;

import co.pets.application.domain.exceptions.Messages;
import co.pets.auth.application.domain.exception.AuthException;
import co.pets.auth.application.domain.jwt.JwtDto;
import co.pets.auth.application.domain.rol.Role;
import co.pets.auth.application.domain.user.BUser;
import co.pets.auth.application.domain.user.NewUser;
import co.pets.auth.application.service.jwt.JwtProvider;
import co.pets.auth.infrastructure.port.input.rol.RolService;
import co.pets.auth.infrastructure.port.input.user.UserService;

public class AuthServiceImplTest {
	
	@Mock
	private UserService userService;
	
	@Mock
	private RolService rolService;
	
	@Mock
	private JwtProvider provider;
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
	@Mock
	private AuthenticationManager manager;
	
	@InjectMocks
	private AuthServiceImpl authService;
	
	private BUser u;
	private NewUser newU;
	private Set<String> rols;
	private String t;

	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		
		t = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJGYW50YXNtYSIsInJvbHMiOltdLCJpYXQiOjE2MjMxOTA0ODAsImV4cCI6MTYyMzE5MDUwMH0."
				+ "HPQImubptE_ariS2hK7xWM0VqmEQkJIdiQvsPDQ6hvlOtFJUTeNQYE2qY5-KNWgFdKalqLjG5bIc4y_yMNpEdg";
		
		rols = new HashSet<>();
		rols.add(Role.CUSTOMER.toString());
		u = new BUser("Fan", "123");
		newU = new NewUser();
		BeanUtils.copyProperties(u, newU);
		newU.setName("");
		newU.setLastName("");
		newU.setRols(rols);
	}
	
	@Test
	public void testRegisterUserWithRolCustomerWithoutException() throws Exception {
		authService.register(newU);
		
		assertNotNull(authService);
	}
	
	@Test
	public void testRegisterUserWithRolAdminWithoutException() throws Exception {
		rols.add(Role.ADMIN.toString());
		authService.register(newU);
		
		assertNotNull(authService);
	}
	
	@Test
	public void testLoginUserWithoutException() throws AuthException {
		when(provider.generateToken(null)).thenReturn(t);
		
		JwtDto jwtDto = authService.login(u);
		
		verify(provider).generateToken(null);
		assertEquals(new JwtDto(t).toString(), jwtDto.toString());
	}
	
	@Test
	public void testLoginUserWithUserNoExisting() throws AuthException {
		
		when(provider.generateToken(null)).thenThrow(new InternalAuthenticationServiceException(""));
		
		AuthException exception = assertThrows(AuthException.class, () -> {
			authService.login(u);
		});
		
		assertEquals(Messages.MESSAGE_USER_NO_EXISTING, exception.getMessage());
	}
	
	@Test
	public void testLoginUserWithPasswordWrong() throws AuthException {
		
		when(provider.generateToken(null)).thenThrow(new BadCredentialsException(""));
		
		AuthException exception = assertThrows(AuthException.class, () -> {
			authService.login(u);
		});
		
		assertEquals(Messages.MESSAGE_PASSWORD_WRONG, exception.getMessage());
	}
	
	@Test
	public void testRefreshTokenWithoutException() throws Exception {
		JwtDto jwtDto = new JwtDto(t);
		when(provider.refreshToken(jwtDto)).thenReturn(t);
		
		JwtDto dto = authService.refreshToken(jwtDto);
		
		verify(provider).refreshToken(jwtDto);
		assertEquals(jwtDto.toString(), dto.toString());
	}
}
