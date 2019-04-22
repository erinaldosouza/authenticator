package br.com.tcc.authenticator.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserCorporation extends User {

	private static final long serialVersionUID = -4207051877681602159L;

	public UserCorporation(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		
	}

}
