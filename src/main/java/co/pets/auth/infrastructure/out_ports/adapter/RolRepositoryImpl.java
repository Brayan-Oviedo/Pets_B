package co.pets.auth.infrastructure.out_ports.adapter;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.pets.auth.domain.model.rol.Rol;
import co.pets.auth.domain.model.rol.Role;
import co.pets.auth.domain.ports.secundary.RolRepository;
import co.pets.auth.infrastructure.out_ports.repository.RolRepositoryDB;

@Service
@Transactional
public class RolRepositoryImpl implements RolRepository {
	
	@Autowired
	private RolRepositoryDB repo;

	@Override
	public Rol findById(Long id) {
		
		return repo.findById(id).orElse(null);
	}

	@Override
	public void save(Rol rol) {
		
		if(findByRole(rol.getRole()) != null) repo.save(rol);
	}

	@Override
	public void update(Rol rol) throws Exception {

		if(findByRole(rol.getRole()) == null) repo.save(rol);
	}

	@Override
	public void deleteById(Long id) {

		repo.deleteById(id);
	}

	@Override
	public List<Rol> getAll() {
		
		return repo.findAll();
	}

	@Override
	public Rol findByRole(Role role) {
		
		return repo.findByRole(role).orElse(null);
	}


	
}
