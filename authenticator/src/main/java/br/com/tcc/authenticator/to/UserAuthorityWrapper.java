package br.com.tcc.authenticator.to;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAuthorityWrapper implements Serializable {

	private static final long serialVersionUID = 5649657443475055122L;
	
	private UserAuthorityTO user;
	private List<UserAuthorityTO> users;


	public List<UserAuthorityTO> getUsers() {
		return users;
	}

	public void setUsers(List<UserAuthorityTO> users) {
		this.users = users;
	}

	public UserAuthorityTO getUser() {
		return user;
	}

	public void setUser(UserAuthorityTO user) {
		this.user = user;
	}

}
