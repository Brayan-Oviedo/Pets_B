package co.pets.auth.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.pets.application.domain.exceptions.Messages;
import co.pets.auth.application.domain.exception.AuthException;
import co.pets.auth.application.domain.jwt.JwtDto;
import co.pets.auth.application.domain.rol.Role;
import co.pets.auth.application.domain.user.BUser;
import co.pets.auth.application.domain.user.NewUser;
import co.pets.auth.application.domain.user.User;
import co.pets.auth.application.service.jwt.JwtProvider;
import co.pets.auth.infrastructure.port.input.AuthService;
import co.pets.auth.infrastructure.port.input.rol.RolService;
import co.pets.auth.infrastructure.port.input.user.UserService;

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
		if(newUser.getRols().contains(Role.ADMIN.toString())) 
			user.getRols().add(rolService.getByRole(Role.ADMIN));
		
		userService.save(user);
	}

	@Override
	public JwtDto login(BUser loginUser) throws Exception {
		
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
