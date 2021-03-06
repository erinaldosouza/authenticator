package br.com.tcc.authenticator.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.tcc.authenticator.to.UserAuthorityTO;

public class UserRestSecurity implements UserDetails {

	private static final long serialVersionUID = 2359745374924951377L;
	
	private Long id;
	private String username;
	private String password;
	private String name;
	private String lastName;
	private Collection<? extends GrantedAuthority> authorities;


	
	public UserRestSecurity(UserAuthorityTO user) {
		super();
		this.id = user.getId();
		this.username = user.getName();
		this.password = user.getPassword();
		this.name = user.getName();
		this.lastName = user.getLastName();
		this.authorities = user.getRoutes().stream()
							   .map(r -> new SimpleGrantedAuthority(r.getAppName() + r.getLink().replace("$", "[0-9A-Za-z]{0,}/{0,1}") + r.getAuthorization()))
							   .collect(Collectors.toSet());	
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
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

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
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
}
