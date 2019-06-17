package br.com.tcc.authenticator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import br.com.tcc.authenticator.helper.RequestHelper;
import br.com.tcc.authenticator.security.UserSecurity;
import br.com.tcc.authenticator.to.UserAuthorityWrapper;

@Service
public class UserSecurityRestServiceImpl implements UserDetailsService {
	
	@Value("${app.access.management.name}")
	private String appAccessManagementName;
	
	private final EurekaClient eurekaClient;
	private final RequestHelper requestHelper;
	
	@Autowired
	public UserSecurityRestServiceImpl(RequestHelper requestHelper, EurekaClient eurekaClient) {
		this.requestHelper = requestHelper;
		this.eurekaClient  = eurekaClient;
	}

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka(appAccessManagementName, Boolean.FALSE);
		ResponseEntity<UserAuthorityWrapper> responseEntity = requestHelper.doGet(instanceInfo.getHomePageUrl() + "api/user/"+login);
		UserAuthorityWrapper usersAuthoritiesWrapper = responseEntity.getBody();
		
		if(usersAuthoritiesWrapper.getUser() == null) {
			throw new UsernameNotFoundException("Incorrect user or password");
		}

		return new UserSecurity(usersAuthoritiesWrapper.getUser());
	}

}
