package br.com.tcc.authenticator.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
		
		System.out.println(getClass().getSimpleName());
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

		return new UserSecurity(1l, "admin", "$2a$10$6g2RuSrtZ25GRPrK82GBUu0.mbTYh2scuGLTu0d3TbGtGbZmLPjjW", list);
	}

}
