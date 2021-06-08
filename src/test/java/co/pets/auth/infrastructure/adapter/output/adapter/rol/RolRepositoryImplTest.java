package co.pets.auth.infrastructure.adapter.output.adapter.rol;

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

import co.pets.auth.application.domain.rol.Rol;
import co.pets.auth.application.domain.rol.Role;
import co.pets.auth.infrastructure.adapter.output.mapper.rol.RolMapper;
import co.pets.auth.infrastructure.adapter.output.model.rol.RolEntity;
import co.pets.auth.infrastructure.adapter.output.repository.rol.RolRepositoryDB;

@RunWith(MockitoJUnitRunner.class)
public class RolRepositoryImplTest {
	
	@Mock
	private RolRepositoryDB dataBase;
	
	@Mock
	private RolMapper mapper;
	
	@InjectMocks
	private RolRepositoryImpl repo;
	
	private Optional<RolEntity> rolEntityOptional;
	private RolEntity rolEntity;
	private Rol r;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		
		this.rolEntityOptional = Optional.of(new RolEntity(Role.ADMIN));
		this.rolEntity = rolEntityOptional.get();
		this.r = new Rol(Role.ADMIN);
	}

	
	@Test
	public void testFindRolById() {
		
		when(dataBase.findById(rolEntity.getId())).thenReturn(rolEntityOptional);
		when(mapper.toDomain(rolEntity)).thenReturn(r);
		
		Rol rol = repo.findById(rolEntity.getId());
		
		assertEquals(rolEntity.getRole(), rol.getRole());
		
	}
	
	
	@Test
	public void testFindUserByRole() {

		when(dataBase.findByRole(rolEntity.getRole())).thenReturn(rolEntityOptional);
		when(mapper.toDomain(rolEntityOptional.get())).thenReturn(r);
		
		Rol rol = repo.findByRole(rolEntity.getRole());
		
		verify(dataBase).findByRole(rolEntity.getRole());
		verify(mapper).toDomain(rolEntity);
		assertEquals(rolEntityOptional.get().getRole(), rol.getRole());
		
	}
	
	
	@Test
	public void testSaveRolWithRolNoExisting() {

		when(dataBase.findByRole(r.getRole())).thenReturn(null);
		when(mapper.toEntity(r)).thenReturn(rolEntity);
		
		repo.save(r);
		
		verify(dataBase).findByRole(r.getRole());
		verify(mapper).toEntity(r);
		
		
		assertTrue(true);
		
	}
	
	@Test
	public void testSaveRolWithRolExisting() {

		when(dataBase.findByRole(r.getRole())).thenReturn(rolEntityOptional);
		
		repo.save(r);
		
		verify(dataBase).findByRole(r.getRole());		
		
		
		assertTrue(true);
		
	}
	
	
	@Test
	public void testUpdateRolWithRolExisting() {
		
		when(dataBase.findByRole(r.getRole())).thenReturn(rolEntityOptional);
		when(mapper.toEntity(r)).thenReturn(rolEntity);
		
		repo.update(r);
		
		verify(dataBase).findByRole(r.getRole());
		verify(mapper).toEntity(r);
		
		
		assertTrue(true);
		
	}
	
	
	@Test
	public void testUpdateRolWithRolNoExisting() {
		
		when(dataBase.findByRole(r.getRole())).thenReturn(null);
		
		repo.update(r);
		
		verify(dataBase).findByRole(r.getRole());		
		assertTrue(true);
		
	}
	
	
	@Test
	public void testDeleteRolById() {
		
		repo.deleteById(rolEntity.getId());
	
		assertTrue(true);
	}
	
	
	@Test
	public void testGetAllRols() {
		
		List<RolEntity> list = new ArrayList<RolEntity>();
		list.add(rolEntity);
		when(dataBase.findAll()).thenReturn(list);
		when(mapper.toDomain(rolEntity)).thenReturn(r);
		
		List<Rol> rols = repo.getAll();
		
		verify(dataBase).findAll();
		verify(mapper).toDomain(rolEntity);
		assertEquals(list.get(0).getRole(), rols.get(0).getRole());
	}
}
