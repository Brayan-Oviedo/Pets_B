package co.pets.auth.application.service.jwt;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;

@RunWith(MockitoJUnitRunner.class)
public class JwtEntryPointTest {

	@Mock
	private MockHttpServletRequest request;
	
	@Mock
	private MockHttpServletResponse response;
	
	@InjectMocks
	private JwtEntryPoint entryPoint;
	
	private AuthenticationException exception;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		
		exception = new AuthenticationServiceException("");
	}
	
	@Test
	public void testCommenceWithoutException() throws IOException, ServletException {
		entryPoint.commence(request, response, exception);
		
		assertTrue(true);
	}
}
