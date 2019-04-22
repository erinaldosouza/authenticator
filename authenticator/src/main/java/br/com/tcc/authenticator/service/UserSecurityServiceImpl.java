package br.com.tcc.authenticator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.tcc.authenticator.security.UserCorporation;
import br.com.tcc.authenticator.security.UserSecurity;

@Service
public class UserSecurityServiceImpl implements UserDetailsService {
	
	private final RestTemplate restTemplate;
	
	@Autowired
	public UserSecurityServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return new UserCorporation("username", "pass", new UserSecurity().getAuthorities());
	}

}
