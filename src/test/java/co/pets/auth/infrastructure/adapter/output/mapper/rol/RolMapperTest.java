package co.pets.auth.infrastructure.adapter.output.mapper.rol;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import co.pets.auth.application.domain.rol.Rol;
import co.pets.auth.application.domain.rol.Role;
import co.pets.auth.infrastructure.adapter.output.model.rol.RolEntity;

public class RolMapperTest {

	private RolMapper mapper;
	private Rol r;
	private RolEntity rEntity;
	
	
	@BeforeEach
	public void init() {
		mapper = new RolMapper();
		r = new Rol(Role.ADMIN);
		rEntity = new RolEntity(Role.ADMIN);
	}
	
	@Test
	public void toDomain() {
		Rol rol = mapper.toDomain(rEntity);
		
		assertEquals(r.getRole(), rol.getRole());
	}
	
	@Test
	public void toEntity() {
		RolEntity rolEntity = mapper.toEntity(r);
		
		assertEquals(rEntity.getRole(), rolEntity.getRole());
	}
}
