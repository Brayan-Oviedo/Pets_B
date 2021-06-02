package co.pets.auth.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.pets.auth.domain.model.rol.Rol;
import co.pets.auth.domain.model.rol.Role;
import co.pets.auth.domain.ports.primary.RolService;
import co.pets.auth.domain.ports.secundary.RolRepository;


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
