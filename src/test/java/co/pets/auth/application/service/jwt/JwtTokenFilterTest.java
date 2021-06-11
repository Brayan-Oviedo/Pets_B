package co.pets.auth.application.service.jwt;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

import co.pets.auth.application.domain.user.User;
import co.pets.auth.application.domain.user.UserAuth;


@RunWith(MockitoJUnitRunner.class)
public class JwtTokenFilterTest {
	
	@Mock
	private MockHttpServletRequest request;
	
	@Mock
	private MockHttpServletResponse response;
	
	@Mock
	private MockFilterChain filterChain;
	
	@Mock
	private JwtProvider provider;
	
	@Mock
	private UserDetailsService service;
	
	@Mock private Authentication auth;
	
	@InjectMocks
	private JwtTokenFilter filter;

	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		
	}
	
	@Test
	public void testGetToken() {
		String token = filter.doGetToken(request);
		
		assertNotEquals("", token);
	}
	

	@Test
	public void testDoFilterInternalWithoutExceptionAndTokenNoNullAndValid() throws ServletException, IOException {
		
		User user = new User();
		user.setUserName("Fan");
		UserAuth uAuth = UserAuth.build(user);
		
		when(request.getHeader("Authorization")).thenReturn("BearerAuthorization");
		String token = filter.doGetToken(request);
		when(provider.validateToken(token)).thenReturn(true);
		when(provider.getUserNameFromToken(token)).thenReturn("Fan");
		when(service.loadUserByUsername(user.getUserName())).thenReturn(uAuth);
		
		filter.doFilterInternal(request, response, filterChain);
		
		verify(provider).validateToken(token);
		verify(provider).getUserNameFromToken(token);
		verify(service).loadUserByUsername(user.getUserName());
		assertNotNull(token);
	}
	
	@Test
	public void testDoFilterInternalWithoutExceptionAndTokenNoNull() throws ServletException, IOException {
		when(request.getHeader("Authorization")).thenReturn("BearerAuthorization");
		String token = filter.doGetToken(request);
		when(provider.validateToken(token)).thenReturn(false);
		
		filter.doFilterInternal(request, response, filterChain);
		
		verify(request, times(2)).getHeader("Authorization");
		verify(provider).validateToken(token);
		assertNotNull(token);
	}
	
	@Test
	public void testDoFilterInternalWithoutExceptionAndTokenValid() throws ServletException, IOException {
		when(request.getHeader("Authorization")).thenReturn("Authorization");
		String token = filter.doGetToken(request);
		when(provider.validateToken(token)).thenReturn(true);
		
		filter.doFilterInternal(request, response, filterChain);
		
		verify(request, times(2)).getHeader("Authorization");
		//verify(provider).validateToken(token);
		assertNull(token);
	}
	
	@Test
	public void testDoFilterInternalWithException() throws ServletException, IOException {
		filter.doFilterInternal(null, null, filterChain);
		
		assertNotNull(filter);
	}
	
	
	@Test
	public void testGetTokenWithHeaderNull() {
		when(request.getHeader("Authorization")).thenReturn(null);
		
		String token = filter.doGetToken(request);
		
		verify(request).getHeader("Authorization");
		assertNull(token);
	}
	
	@Test
	public void testGetTokenWithOnlyHeader() {
		when(request.getHeader("Authorization")).thenReturn("Authorization");
		
		String token = filter.doGetToken(request);
		
		verify(request).getHeader("Authorization");
		assertNull(token);
	}
	
	
	@Test
	public void testGetTokenWithHeaderAndBearer() {
		when(request.getHeader("Authorization")).thenReturn("BearerAuthorization");
		
		String token = filter.doGetToken(request);
		
		verify(request).getHeader("Authorization");
		assertNotEquals("", token);
	}
}
