package co.pets.auth.infrastructure.out_ports.adapter;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.pets.auth.core.exceptions.UserException;
import co.pets.auth.domain.model.user.User;
import co.pets.auth.domain.ports.secundary.UserRepository;
import co.pets.auth.infrastructure.out_ports.repository.UserRepositoryDB;
import co.pets.core.exceptions.Messages;


@Service
@Transactional
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	private UserRepositoryDB repo;

	@Override
	public User findById(Long id) throws Exception {
	
		return repo.findById(id).orElse(null);
	}

	@Override
	public void save(User user) throws Exception {
		
		if(!existsByUserName(user.getUserName())) {
			repo.save(user);
		}else throw new UserException(Messages.MESSAGE_USER_EXISTING);
	}

	@Override
	public void update(User user) throws Exception {
		
		if(existsByUserName(user.getUserName())) {
			repo.save(user);
		}else throw new UserException(Messages.MESSAGE_USER_NO_EXISTING);
	}

	@Override
	public void deleteById(Long id) {
		
		repo.deleteById(id);
	}

	@Override
	public List<User> getAll() {
		
		return repo.findAll();
	}

	@Override
	public User findByUserName(String userName) {
		
		return repo.findByUserName(userName).orElse(null);
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
