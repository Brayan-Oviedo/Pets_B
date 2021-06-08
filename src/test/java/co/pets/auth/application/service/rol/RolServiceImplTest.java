package co.pets.auth.application.service.rol;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import co.pets.auth.application.domain.rol.Rol;
import co.pets.auth.application.domain.rol.Role;
import co.pets.auth.infrastructure.port.output.rol.RolRepository;

@RunWith(MockitoJUnitRunner.class)
public class RolServiceImplTest {

	@Mock
	private RolRepository repo;
	
	@InjectMocks
	private RolServiceImpl service;
	
	private Rol r;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		
		r = new Rol(Role.ADMIN);
	}
	
	@Test
	public void testFindRolByIdWithoutException() throws Exception {
		when(repo.findById(1L)).thenReturn(r);
		
		Rol rol = service.findById(1L);
		
		verify(repo).findById(1L);
		assertEquals(r.getRole(), rol.getRole());
	}
	
	@Test
	public void testFindRolByRoleWithoutException() {
		when(repo.findByRole(r.getRole())).thenReturn(r);
		
		Rol rol = service.getByRole(r.getRole());
		
		verify(repo).findByRole(r.getRole());
		assertEquals(r.getRole(), rol.getRole());
	}
	
	@Test
	public void testSaveRolWithoutException() throws Exception {
		service.save(r);
		
		assertTrue(true);
	}
	
	@Test
	public void testUpdateRolWithoutException() throws Exception {
		service.update(r);
		
		assertTrue(true);
	}
	
	@Test
	public void testDeleteRolById() {
		service.deleteById(1L);
		
		assertTrue(true);
	}
	
	@Test
	public void testGetAllRols() {
		List<Rol> list = new ArrayList<>();
		list.add(r);
		when(repo.getAll()).thenReturn(list);
		
		List<Rol> rols = service.getAll();
		
		verify(repo).getAll();
		assertEquals(list.size(), rols.size());
	}
}
