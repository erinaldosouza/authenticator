package br.com.tcc.authenticator.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.tcc.authenticator.to.UserAuthorityWrapper;

@Component
public class RequestHelper {
	
	private final RestTemplate restTemplate;
	
	@Value("${app.access.management.user}")
	private String appAccessManagerUser;
	
	@Value("${app.access.management.user}")
	private String appAccessManagerPassword;
	
	@Autowired
	public RequestHelper (RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	private HttpEntity<String> getDefaultJsonHeaders() {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		httpHeaders.setBasicAuth(appAccessManagerUser, appAccessManagerPassword);		
		HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
		
		return entity;
	}
	
	private ResponseEntity<UserAuthorityWrapper> doRequestDefault(String url, HttpMethod method, Object... body) {
		HttpEntity<String> entity = this.getDefaultJsonHeaders();				
		return restTemplate.exchange(url, method, entity, new ParameterizedTypeReference<UserAuthorityWrapper>(){});
		
	}
	
	public ResponseEntity<UserAuthorityWrapper> doGet(String url) {
		return this.doRequestDefault(url, HttpMethod.GET);		
	}
	
	public ResponseEntity<UserAuthorityWrapper> doPost(String url, Object body) {
		return this.doRequestDefault(url, HttpMethod.POST, body);
	}
	
	public ResponseEntity<UserAuthorityWrapper>  doPut(String url, Object body) {
		return this.doRequestDefault(url, HttpMethod.PUT, body);
	}
		
	public ResponseEntity<UserAuthorityWrapper> doDelete(String url) {
		return this.doRequestDefault(url, HttpMethod.DELETE);
	}
}
