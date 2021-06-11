package co.pets.auth.application.service.init;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import co.pets.auth.infrastructure.port.input.rol.RolService;

@RunWith(MockitoJUnitRunner.class)
public class CreateRolsTest {

	@Mock
	private RolService service;
	
	@InjectMocks
	private CreateRols creator;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void testRun() throws Exception {
		creator.run("");
		assertNotNull(creator);
	}
}
