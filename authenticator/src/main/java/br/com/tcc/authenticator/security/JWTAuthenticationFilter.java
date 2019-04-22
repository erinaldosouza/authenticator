package br.com.tcc.authenticator.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.tcc.authenticator.to.CredentialTO;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private final JWTUtil jwtUtil;
	private final AuthenticationManager authenticationManager;
	
	@Autowired
	public JWTAuthenticationFilter(JWTUtil jwtUtil, AuthenticationManager authenticationManager) {
		this.jwtUtil = jwtUtil;
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	public Authentication attemptAuthentication
			(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
		
		try {
			CredentialTO credential  = new ObjectMapper()
					                       .readValue(req.getInputStream(), CredentialTO.class);
			
			UsernamePasswordAuthenticationToken token = 
					   new UsernamePasswordAuthenticationToken(credential.getUsername(), credential.getPassword(), new ArrayList<>());
			
			return authenticationManager.authenticate(token);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}		
	}
	
	@Override
	protected void successfulAuthentication
			(HttpServletRequest req, HttpServletResponse res, 
			 FilterChain chain, Authentication authResult) throws IOException, ServletException {
		
		String username  = ((UserSecurity) authResult.getPrincipal()).getUsername();
		String token = jwtUtil.generateToken(username);
		res.addHeader("Authentication", "Bearer " + token);
	}

}
