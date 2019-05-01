package br.com.tcc.authenticator.to;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAuthorityTO implements Serializable {

	private static final long serialVersionUID = 1692304025121890683L;
	
	private Long id;
	
	private String login;
	
	private String password;

	private String name;
	
	@JsonProperty("last_name")
	private String lastName;
	
	@JsonProperty("fl_active")
	private Boolean flActive;
	
	@JsonProperty("group")
	private List<GroupTO> groups;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Boolean getFlActive() {
		return flActive;
	}

	public void setFlActive(Boolean flActive) {
		this.flActive = flActive;
	}

	public List<GroupTO> getGroups() {
		return groups;
	}

	public void setGroups(List<GroupTO> groups) {
		this.groups = groups;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public List<RouteTO> getRoutes() {
		List<RouteTO> routes = new LinkedList<RouteTO>();
		if(groups != null) {
			
			this.groups.forEach(g -> 
				routes.addAll(g.getRoutes())
			);

		}
		
		return routes;
	}
}
