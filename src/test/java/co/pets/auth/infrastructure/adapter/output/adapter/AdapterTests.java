package co.pets.auth.infrastructure.adapter.output.adapter;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

import co.pets.auth.infrastructure.adapter.output.adapter.rol.RolRepositoryImplTest;
import co.pets.auth.infrastructure.adapter.output.adapter.user.UserRepositoryImplTest;

@RunWith(JUnitPlatform.class)
@SelectClasses({
	RolRepositoryImplTest.class,
	UserRepositoryImplTest.class
})
public class AdapterTests {

}
