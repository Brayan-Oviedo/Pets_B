package co.pets.auth.infrastructure.adapter.output.adapter.rol;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.pets.auth.application.domain.rol.Rol;
import co.pets.auth.application.domain.rol.Role;
import co.pets.auth.infrastructure.adapter.output.mapper.rol.RolMapper;
import co.pets.auth.infrastructure.adapter.output.repository.rol.RolRepositoryDB;
import co.pets.auth.infrastructure.port.output.rol.RolRepository;

@Service
@Transactional
public class RolRepositoryImpl implements RolRepository {
	
	@Autowired
	private RolRepositoryDB repo;
	
	@Autowired
	private RolMapper mapper;
	
	

	@Override
	public Rol findById(Long id) {
		Optional<Rol> rolE = repo.findById(id).map(rolEntity -> mapper.toDomain(rolEntity));
		return rolE.orElse(null);
	}

	@Override
	public void save(Rol rol) {
		
		if(repo.findByRole(rol.getRole()) == null) repo.save(mapper.toEntity(rol));
	}

	@Override
	public void update(Rol rol) {

		if(repo.findByRole(rol.getRole()) != null) repo.save(mapper.toEntity(rol));
	}

	@Override
	public void deleteById(Long id) {

		repo.deleteById(id);
	}

	@Override
	public List<Rol> getAll() {
		Stream<Rol> stream = repo.findAll().stream().map(mapper::toDomain);
		List<Rol> rols = stream.collect(Collectors.toList());
		return rols;
	}

	@Override
	public Rol findByRole(Role role) {
		Optional<Rol> rol = repo.findByRole(role).map(rolEntity -> mapper.toDomain(rolEntity));
		return rol.orElse(null);
	}


	
}
