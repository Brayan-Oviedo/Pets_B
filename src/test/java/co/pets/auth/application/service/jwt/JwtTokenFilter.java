package co.pets.auth.application.service.jwt;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public class JwtTokenFilter {
	
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

	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		
	}
	
	@Test
	public void testDoFilterInternal() {
		when(null)
	}
}
