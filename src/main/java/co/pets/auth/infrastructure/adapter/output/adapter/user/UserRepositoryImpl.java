package co.pets.auth.infrastructure.adapter.output.adapter.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.pets.application.domain.exceptions.Messages;
import co.pets.auth.application.domain.exception.UserException;
import co.pets.auth.application.domain.user.User;
import co.pets.auth.infrastructure.adapter.output.mapper.user.UserMapper;
import co.pets.auth.infrastructure.adapter.output.repository.user.UserRepositoryDB;
import co.pets.auth.infrastructure.port.output.user.UserRepository;



@Service
@Transactional
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	private UserRepositoryDB repo;
	
	@Autowired
	private UserMapper mapper;

	@Override
	public User findById(Long id) throws Exception {
	
		Optional<User> user = repo.findById(id).map(userEntity -> mapper.toDomain(userEntity));
		return user.orElse(null);
	}

	@Override
	public void save(User user) throws Exception {
		
		if(!existsByUserName(user.getUserName())) {
			repo.save(mapper.toEntity(user));
		}else throw new UserException(Messages.MESSAGE_USER_EXISTING);
	}

	@Override
	public void update(User user) throws Exception {
		
		if(existsByUserName(user.getUserName())) {
			repo.save(mapper.toEntity(user));
		}else throw new UserException(Messages.MESSAGE_USER_NO_EXISTING);
	}

	@Override
	public void deleteById(Long id) {
		
		repo.deleteById(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll() {
		List<User> list = (List<User>) repo.findAll().stream().map(userEntity -> mapper.toDomain(userEntity));
		return list;
	}

	@Override
	public User findByUserName(String userName) {
		Optional<User> user = repo.findByUserName(userName).map(userEntity -> mapper.toDomain(userEntity));
		return user.orElse(null);
	}

	@Override
	public boolean existsByUserName(String userName) {
		
		return repo.existsByUserName(userName);
	}

	@Override
	public void deleteByUserName(String userName) {
		
		repo.deleteByUserName(userName);
	}
	
		
	
}
