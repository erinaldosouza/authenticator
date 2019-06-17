package br.com.tcc.authenticator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.tcc.authenticator.model.UserApp;
import br.com.tcc.authenticator.repository.UserAppRepository;
import br.com.tcc.authenticator.security.UserDatabaseSecurity;

@Service("userSecurityDatabaseService")
public class UserSecurityDatabaseServiceImpl implements UserDetailsService {
	
	private UserAppRepository repository;
	
	@Autowired
	public UserSecurityDatabaseServiceImpl(UserAppRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

		UserApp user = repository.findByLogin(login);
		
		if(user == null) {
			throw new UsernameNotFoundException("Incorrect user or password");
		}

		return new UserDatabaseSecurity(user);
	}

}
