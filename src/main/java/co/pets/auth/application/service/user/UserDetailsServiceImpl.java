package co.pets.auth.application.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.pets.auth.application.domain.user.User;
import co.pets.auth.application.domain.user.UserAuth;
import co.pets.auth.infrastructure.port.input.user.UserService;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.getByUserName(username);
		return UserAuth.build(user);
	}

}
