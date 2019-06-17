package br.com.tcc.authenticator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.netflix.discovery.EurekaClient;

import br.com.tcc.authenticator.helper.RequestHelper;
import br.com.tcc.authenticator.security.UserSecurity;

@Service("userSecurityDaoService")
public class UserSecurityDaoServiceImpl implements UserDetailsService {


	
	@Autowired
	public UserSecurityDaoServiceImpl(RequestHelper requestHelper, EurekaClient eurekaClient) {

	}

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		
		if(login == null) {
			throw new UsernameNotFoundException("Incorrect user or password");
		}

		return new UserSecurity(null);
	}

}
