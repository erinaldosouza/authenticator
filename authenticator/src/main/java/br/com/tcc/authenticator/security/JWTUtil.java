package br.com.tcc.authenticator.security;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	@Value("${jwt.token.authorities.key}")
	private String tokenAuthoritiesKey;
	
	public String generateToken(String username, Collection<? extends GrantedAuthority> autorities) {
		
		return Jwts.builder()
				   .setSubject(username)
				   .claim(tokenAuthoritiesKey, autorities)
				   .setExpiration(new Date(System.currentTimeMillis() + this.expiration))
				   .signWith(SignatureAlgorithm.HS512, this.secret.getBytes())
				   .compact();				
	}

}
