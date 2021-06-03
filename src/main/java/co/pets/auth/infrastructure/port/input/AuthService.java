package co.pets.auth.infrastructure.port.input;

import co.pets.auth.application.domain.jwt.JwtDto;
import co.pets.auth.application.domain.user.BUser;
import co.pets.auth.application.domain.user.NewUser;

public interface AuthService {
	
	void register(NewUser newUser) throws Exception;
	
	JwtDto login(BUser loginUser) throws Exception;

	JwtDto refreshToken(JwtDto jwtDto) throws Exception;
	
}
