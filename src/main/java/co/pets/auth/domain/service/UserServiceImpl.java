package co.pets.auth.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.pets.auth.domain.model.user.User;
import co.pets.auth.domain.ports.primary.UserService;
import co.pets.auth.domain.ports.secundary.UserRepository;


@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User getByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	@Override
	public boolean existsByUserName(String userName) {
		return userRepository.existsByUserName(userName);
	}

	@Override
	public void save(User user) throws Exception {
		userRepository.save(user);
	}

	@Override
	public User findById(Long id) throws Exception {
		return userRepository.findById(id);
	}

	@Override
	public void update(User user) throws Exception {
		userRepository.update(user);
	}

	@Override
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public List<User> getAll() {
		return userRepository.getAll();
	}
	
	
}
