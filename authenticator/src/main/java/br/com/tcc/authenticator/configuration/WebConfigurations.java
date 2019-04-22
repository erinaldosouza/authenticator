package br.com.tcc.authenticator.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WebConfigurations {
	
	@Bean
	protected RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
