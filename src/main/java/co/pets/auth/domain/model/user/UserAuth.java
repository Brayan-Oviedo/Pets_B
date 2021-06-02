package co.pets.auth.domain.model.user;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserAuth implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String name;
	private String lastName;
	private String userName;
	private String password;
	private int age;
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserAuth(String name, String lastName, String userName, String password, int age,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.age = age;
		this.authorities = authorities;
	}
	
	public static UserAuth build(User user) {
		
		List<GrantedAuthority> authorities = user.getRols().stream()
				.map(rol -> new SimpleGrantedAuthority(rol.getRole().name())).collect(Collectors.toList());
		return new UserAuth(user.getName(), user.getLastName(), user.getUserName(), user.getPassword(), user.getAge(), authorities);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public String getUsername() {
		return userName;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

}
