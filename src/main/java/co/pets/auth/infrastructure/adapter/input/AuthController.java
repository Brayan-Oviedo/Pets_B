package co.pets.auth.infrastructure.adapter.input;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.pets.application.domain.exceptions.ResException;
import co.pets.application.domain.exceptions.config.BadRequestException;
import co.pets.auth.application.domain.jwt.JwtDto;
import co.pets.auth.application.domain.user.BUser;
import co.pets.auth.application.domain.user.NewUser;
import co.pets.auth.infrastructure.port.input.AuthService;


@RestController
@RequestMapping("/auth")
@CrossOrigin("localhost:4200")
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody NewUser newUser) {
		try {
			
			authService.register(newUser);
			return new ResponseEntity<>(HttpStatus.OK);
		
		} catch (Exception e) {
			
			BadRequestException exception = new BadRequestException(e.getMessage());
			ResException res = new ResException(e.getMessage(), exception);
				
			return new ResponseEntity<ResException>(res, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody BUser loginUser) {
		try {
			
			return new ResponseEntity<>(authService.login(loginUser), HttpStatus.OK);
		
		} catch (Exception e) {
			
			BadRequestException exception = new BadRequestException(e.getMessage());
			ResException res = new ResException(e.getMessage(), exception);
				
			return new ResponseEntity<ResException>(res, HttpStatus.BAD_REQUEST);
		}	
	}
	
	@PostMapping("/refreshtoken")
	public ResponseEntity<?> refreshToken(@RequestBody JwtDto jwtDto) {
		try {
			
			return new ResponseEntity<>(authService.refreshToken(jwtDto), HttpStatus.OK);
		
		} catch (Exception e) {
			
			BadRequestException exception = new BadRequestException(e.getMessage());
			ResException res = new ResException(e.getMessage(), exception);
				
			return new ResponseEntity<ResException>(res, HttpStatus.BAD_REQUEST);
		}
	}
}
