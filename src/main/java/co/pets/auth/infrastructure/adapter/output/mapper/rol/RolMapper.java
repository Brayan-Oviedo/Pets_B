package co.pets.auth.infrastructure.adapter.output.mapper.rol;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import co.pets.auth.application.domain.rol.Rol;
import co.pets.auth.infrastructure.adapter.output.model.rol.RolEntity;

@Scope("singleton")
@Component
public class RolMapper {
	
	public Rol toDomain(RolEntity rolEntity) {
		Rol rol = new Rol();
		BeanUtils.copyProperties(rolEntity, rol);
		return rol;
	}
	
	public RolEntity toEntity(Rol rol) {
		RolEntity rolEntity = new RolEntity();
		BeanUtils.copyProperties(rol, rolEntity);
		return rolEntity;
	}

}
