package co.pets.auth.domain.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.pets.auth.config.exceptions.AuthException;
import co.pets.auth.domain.jwt.JwtProvider;
import co.pets.auth.domain.model.rol.Role;
import co.pets.auth.domain.model.user.User;
import co.pets.auth.domain.ports.primary.AuthService;
import co.pets.auth.domain.ports.primary.RolService;
import co.pets.auth.domain.ports.primary.UserService;
import co.pets.auth.infrastructure.in_ports.api.model.JwtDto;
import co.pets.auth.infrastructure.in_ports.api.model.LoginUser;
import co.pets.auth.infrastructure.in_ports.api.model.NewUser;
import co.pets.config.exceptions.Messages;


@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RolService rolService;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
	public void register(NewUser newUser) throws Exception {
		
		User user = new User(
				newUser.getUserName(), 
				passwordEncoder.encode(newUser.getPassword()),
				newUser.getName(), newUser.getLastName(),
				newUser.getAge());
		
		user.getRols().add(rolService.getByRole(Role.CUSTOMER));
		if(newUser.getRols().contains("ADMIN")) 
			user.getRols().add(rolService.getByRole(Role.ADMIN));
		
		userService.save(user);
	}

	@Override
	public JwtDto login(LoginUser loginUser) throws Exception {
		
		try {
			UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(loginUser.getUserName(), loginUser.getPassword());
			Authentication authentication = authenticationManager.authenticate(user);

			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtProvider.generateToken(authentication);
			JwtDto jwtDto = new JwtDto(jwt);
			return jwtDto;
		} catch (Exception e) {
			throw new AuthException(Messages.MESSAGE_LOGIN_FAILED);
		}
		
	}

	@Override
	public JwtDto refreshToken(JwtDto jwtDto) throws Exception {
		
		try {
			String token = jwtProvider.refreshToken(jwtDto);
			JwtDto newJwtDto = new JwtDto(token);
			return newJwtDto;
		} catch (Exception e) {
			throw new AuthException(Messages.MESSAGE_REFRESH_TOKEN_FAILED);
		}
		
	}	
	

}
