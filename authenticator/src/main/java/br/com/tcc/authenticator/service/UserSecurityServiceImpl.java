package br.com.tcc.authenticator.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
public class UserSecurityServiceImpl implements UserDetailsService {
	
	private final RequestHelper requestHelper;
	
	@Value("${app.access.management.name}")
	private String appAccessManagementName;
	
	private final EurekaClient eurekaClient;
	
	@Autowired
	public UserSecurityServiceImpl(RequestHelper requestHelper, EurekaClient eurekaClient) {
		this.requestHelper = requestHelper;
		this.eurekaClient  = eurekaClient;
	}

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka(appAccessManagementName, Boolean.FALSE);
		ResponseEntity<UserAuthorityWrapper> responseEntity = requestHelper.doGet(instanceInfo.getHomePageUrl() + "api/users?login="+login);
		// System.err.println(responseEntity.getBody());
		
		UserAuthorityWrapper usersAuthoritiesWrapper = responseEntity.getBody();
		
		System.out.println(usersAuthoritiesWrapper.getUsers());
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

		return new UserSecurity(1l, "admin", "$2a$10$6g2RuSrtZ25GRPrK82GBUu0.mbTYh2scuGLTu0d3TbGtGbZmLPjjW", list);
	}

}
