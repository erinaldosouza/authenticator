package br.com.tcc.authenticator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.tcc.authenticator.model.UserApp;

@Repository
public interface UserAppRepository extends JpaRepository<UserApp, Long> {
	
	@Query(name="findByLogin")
	UserApp findByLogin(String login);

}
