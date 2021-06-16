package co.pets.auth.application.service.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import co.pets.auth.application.domain.rol.Rol;
import co.pets.auth.application.domain.rol.Role;
import co.pets.auth.infrastructure.port.input.rol.RolService;


@Component
public class CreateRols implements CommandLineRunner{

	@Autowired
	private RolService rolService;
	
	@Override
	public void run(String... args) throws Exception {
		Rol rolAdmin = new Rol(Role.ADMIN);
		Rol rolCustomer = new Rol(Role.CUSTOMER);
		Rol rolManager = new Rol(Role.MANAGER);
		Rol rolOperator = new Rol(Role.OPERATOR);
		
		
/** ----------ESTO SOLO SE DEBE EJEUTAR UNA VEZ---------- **/
		
        rolService.save(rolAdmin);
		rolService.save(rolCustomer);
		rolService.save(rolManager);
		rolService.save(rolOperator); 
	}

}
