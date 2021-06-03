package co.pets.auth.application.service.rol;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.pets.auth.application.domain.rol.Rol;
import co.pets.auth.application.domain.rol.Role;
import co.pets.auth.infrastructure.port.input.rol.RolService;
import co.pets.auth.infrastructure.port.output.rol.RolRepository;

@Service
@Transactional
public class RolServiceImpl implements RolService {

	@Autowired
	private RolRepository rolRepository;

	@Override
	public Rol getByRole(Role role) {
		return rolRepository.findByRole(role);
	}

	@Override
	public void save(Rol rol) throws Exception {
		rolRepository.save(rol);
	}

	@Override
	public Rol findById(Long id) throws Exception {
		return rolRepository.findById(id);
	}

	@Override
	public void update(Rol rol) throws Exception {
		rolRepository.update(rol);
	}

	@Override
	public void deleteById(Long id) {
		rolRepository.deleteById(id);
	}

	@Override
	public List<Rol> getAll() {
		return rolRepository.getAll();
	}
	
	
}
