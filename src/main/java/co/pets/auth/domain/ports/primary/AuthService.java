package co.pets.auth.domain.ports.primary;

import co.pets.auth.infrastructure.in_ports.api.model.JwtDto;
import co.pets.auth.infrastructure.in_ports.api.model.LoginUser;
import co.pets.auth.infrastructure.in_ports.api.model.NewUser;

public interface AuthService {
	
	void register(NewUser newUser) throws Exception;
	
	JwtDto login(LoginUser loginUser) throws Exception;

	JwtDto refreshToken(JwtDto jwtDto) throws Exception;
	
}
