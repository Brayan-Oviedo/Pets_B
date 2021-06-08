package co.pets.auth.application.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.pets.auth.application.domain.user.User;
import co.pets.auth.infrastructure.port.input.user.UserService;
import co.pets.auth.infrastructure.port.output.user.UserRepository;


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

	@Override
	public void deleteByUserName(String userName) {
		userRepository.deleteByUserName(userName);
	}
	
	
}
